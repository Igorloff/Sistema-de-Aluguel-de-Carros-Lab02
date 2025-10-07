package br.com.aluguelcarros.api.service;

import br.com.aluguelcarros.api.domain.ContratoAluguel;
import br.com.aluguelcarros.api.repository.ContratoAluguelRepository;
import br.com.aluguelcarros.api.rest.dto.ContratoAluguelRequest;
import br.com.aluguelcarros.api.rest.dto.ContratoAluguelResponse;
import br.com.aluguelcarros.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoAluguelService {

    private final ContratoAluguelRepository repo;

    public ContratoAluguelService(ContratoAluguelRepository repo) {
        this.repo = repo;
    }

    public ContratoAluguelResponse criar(ContratoAluguelRequest req) {
        ContratoAluguel contrato = new ContratoAluguel();
        contrato.setDataAssinatura(req.dataAssinatura());
        contrato.setValorTotal(req.valorTotal());
        contrato.setStatus(req.status());
        return toResponse(repo.save(contrato));
    }

    public List<ContratoAluguelResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }
    
    public ContratoAluguelResponse buscarPorId(Long id) {
        return repo.findById(id).map(this::toResponse)
            .orElseThrow(() -> new NotFoundException("Contrato de Aluguel não encontrado"));
    }

    private ContratoAluguelResponse toResponse(ContratoAluguel contrato) {
        return new ContratoAluguelResponse(
                contrato.getId(),
                contrato.getDataAssinatura(),
                contrato.getValorTotal(),
                contrato.getStatus()
        );
    }
}