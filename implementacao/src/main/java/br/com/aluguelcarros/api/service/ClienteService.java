package br.com.aluguelcarros.api.service;

import br.com.aluguelcarros.api.domain.Cliente;
import br.com.aluguelcarros.api.repository.ClienteRepository;
import br.com.aluguelcarros.api.rest.dto.ClienteRequest;
import br.com.aluguelcarros.api.rest.dto.ClienteResponse;
import br.com.aluguelcarros.api.service.exception.ConflictException;
import br.com.aluguelcarros.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public ClienteResponse criar(ClienteRequest req) {
        if (repo.existsByCpf(req.cpf()))
            throw new ConflictException("CPF já cadastrado");

        Cliente c = new Cliente();
        c.setCpf(req.cpf());
        c.setRg(req.rg());
        c.setNome(req.nome());
        c.setEndereco(req.endereco());
        c.setProfissao(req.profissao());

        return toResponse(repo.save(c));
    }

    public ClienteResponse buscarPorId(Long id) {
        Cliente c = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        return toResponse(c);
    }

    public List<ClienteResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public ClienteResponse atualizar(Long id, ClienteRequest req) {
        Cliente c = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        if (!c.getCpf().equals(req.cpf()) && repo.existsByCpf(req.cpf()))
            throw new ConflictException("CPF já cadastrado");

        c.setCpf(req.cpf());
        c.setRg(req.rg());
        c.setNome(req.nome());
        c.setEndereco(req.endereco());
        c.setProfissao(req.profissao());

        return toResponse(repo.save(c));
    }

    public void remover(Long id) {
        Cliente c = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        repo.delete(c);
    }

    private ClienteResponse toResponse(Cliente c) {
        return new ClienteResponse(
                c.getId(),
                c.getCpf(),
                c.getRg(),
                c.getNome(),
                c.getEndereco(),
                c.getProfissao()
        );
    }
}
