package br.com.aluguelcarros.api.service;

import br.com.aluguelcarros.api.domain.Banco;
import br.com.aluguelcarros.api.repository.BancoRepository;
import br.com.aluguelcarros.api.rest.dto.BancoRequest;
import br.com.aluguelcarros.api.rest.dto.BancoResponse;
import br.com.aluguelcarros.api.service.exception.ConflictException;
import br.com.aluguelcarros.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoService {

    private final BancoRepository repo;

    public BancoService(BancoRepository repo) {
        this.repo = repo;
    }

    public BancoResponse criar(BancoRequest req) {
        if (repo.existsByCnpj(req.cnpj())) {
            throw new ConflictException("CNPJ já cadastrado");
        }
        Banco banco = new Banco();
        banco.setNome(req.nome());
        banco.setCnpj(req.cnpj());
        return toResponse(repo.save(banco));
    }

    public BancoResponse buscarPorId(Long id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Banco não encontrado"));
    }

    public List<BancoResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public BancoResponse atualizar(Long id, BancoRequest req) {
        Banco banco = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Banco não encontrado"));

        if (!banco.getCnpj().equals(req.cnpj()) && repo.existsByCnpj(req.cnpj())) {
            throw new ConflictException("CNPJ já cadastrado para outro banco");
        }

        banco.setNome(req.nome());
        banco.setCnpj(req.cnpj());
        return toResponse(repo.save(banco));
    }

    public void remover(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Banco não encontrado");
        }
        repo.deleteById(id);
    }

    private BancoResponse toResponse(Banco banco) {
        return new BancoResponse(banco.getId(), banco.getNome(), banco.getCnpj());
    }
}