package br.com.aluguelcarros.api.service;

import br.com.aluguelcarros.api.domain.*;
import br.com.aluguelcarros.api.repository.*;
import br.com.aluguelcarros.api.rest.dto.ContratoAluguelRequest;
import br.com.aluguelcarros.api.rest.dto.ContratoAluguelResponse;
import br.com.aluguelcarros.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoAluguelService {

    private final ContratoAluguelRepository repo;
    private final ClienteRepository clienteRepo;
    private final AutomovelRepository automovelRepo;
    private final BancoRepository bancoRepo;

    public ContratoAluguelService(
            ContratoAluguelRepository repo,
            ClienteRepository clienteRepo,
            AutomovelRepository automovelRepo,
            BancoRepository bancoRepo
    ) {
        this.repo = repo;
        this.clienteRepo = clienteRepo;
        this.automovelRepo = automovelRepo;
        this.bancoRepo = bancoRepo;
    }

    public ContratoAluguelResponse criar(ContratoAluguelRequest req) {
        ContratoAluguel contrato = new ContratoAluguel();

        // Atribuições diretas
        contrato.setDataAssinatura(req.dataAssinatura());
        contrato.setValorTotal(req.valorTotal());
        contrato.getStatus();

        // Relacionamentos
        Cliente cliente = clienteRepo.findById(req.clienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        Automovel automovel = automovelRepo.findById(req.automovelId())
                .orElseThrow(() -> new NotFoundException("Automóvel não encontrado"));
        contrato.setCliente(cliente);
        contrato.setAutomovel(automovel);

        if (req.bancoId() != null) {
            Banco banco = bancoRepo.findById(req.bancoId())
                    .orElseThrow(() -> new NotFoundException("Banco não encontrado"));
            contrato.setBanco(banco);
        }

        // Salvar contrato
        ContratoAluguel salvo = repo.save(contrato);

        // Retornar resposta formatada
        return toResponse(salvo);
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
        contrato.getStatus(),
        contrato.getCliente().getId(),
        contrato.getAutomovel().getId(),
        contrato.getBanco() != null ? contrato.getBanco().getId() : null
    );
    }
}
