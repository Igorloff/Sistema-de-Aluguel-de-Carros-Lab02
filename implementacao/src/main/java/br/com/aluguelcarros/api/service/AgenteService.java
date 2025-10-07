package br.com.aluguelcarros.api.service;

import br.com.aluguelcarros.api.domain.Agente;
import br.com.aluguelcarros.api.repository.AgenteRepository;
import br.com.aluguelcarros.api.rest.dto.AgenteRequest;
import br.com.aluguelcarros.api.rest.dto.AgenteResponse;
import br.com.aluguelcarros.api.service.exception.ConflictException;
import br.com.aluguelcarros.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenteService {

    private final AgenteRepository repo;

    public AgenteService(AgenteRepository repo) {
        this.repo = repo;
    }

    public AgenteResponse criar(AgenteRequest req) {
        if (repo.existsByCnpj(req.cnpj())) {
            throw new ConflictException("CNPJ já cadastrado");
        }
        Agente agente = new Agente();
        agente.setNome(req.nome());
        agente.setCnpj(req.cnpj());
        return toResponse(repo.save(agente));
    }

    public AgenteResponse buscarPorId(Long id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Agente não encontrado"));
    }

    public List<AgenteResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public AgenteResponse atualizar(Long id, AgenteRequest req) {
        Agente agente = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Agente não encontrado"));

        if (!agente.getCnpj().equals(req.cnpj()) && repo.existsByCnpj(req.cnpj())) {
            throw new ConflictException("CNPJ já cadastrado para outro agente");
        }

        agente.setNome(req.nome());
        agente.setCnpj(req.cnpj());
        return toResponse(repo.save(agente));
    }

    public void remover(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Agente não encontrado");
        }
        repo.deleteById(id);
    }

    private AgenteResponse toResponse(Agente agente) {
        return new AgenteResponse(agente.getId(), agente.getNome(), agente.getCnpj());
    }
}