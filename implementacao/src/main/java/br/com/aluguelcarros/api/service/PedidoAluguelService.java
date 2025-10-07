package br.com.aluguelcarros.api.service;

import br.com.aluguelcarros.api.domain.*;
import br.com.aluguelcarros.api.repository.*;
import br.com.aluguelcarros.api.rest.dto.*;
import br.com.aluguelcarros.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoAluguelService {

    private final PedidoAluguelRepository repo;
    private final ClienteRepository clienteRepository;
    private final AutomovelRepository automovelRepository;

    public PedidoAluguelService(PedidoAluguelRepository repo, ClienteRepository cRepo, AutomovelRepository aRepo) {
        this.repo = repo;
        this.clienteRepository = cRepo;
        this.automovelRepository = aRepo;
    }

    @Transactional // Garante que tudo dentro deste método tenha acesso à sessão do banco
    public PedidoAluguelResponse registrarPedido(PedidoAluguelRequest req) {
        Cliente cliente = clienteRepository.findById(req.idCliente())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        Automovel automovel = automovelRepository.findById(req.idAutomovel())
                .orElseThrow(() -> new NotFoundException("Automóvel não encontrado"));

        PedidoAluguel pedido = new PedidoAluguel();
        pedido.setCliente(cliente);
        pedido.setAutomovel(automovel);
        pedido.setDataInicio(req.dataInicio());
        pedido.setDataFim(req.dataFim());
        pedido.setStatus(PedidoAluguel.StatusPedido.SOLICITADO);

        PedidoAluguel pedidoSalvo = repo.save(pedido);
        
        // A conversão para DTO agora é feita aqui dentro, com a transação ainda ativa.
        return toResponse(pedidoSalvo);
    }
    
    @Transactional(readOnly = true) // readOnly = true é uma otimização para consultas
    public PedidoAluguelResponse buscarPorId(Long id) {
        return repo.findById(id)
            .map(this::toResponse) // O .map garante que toResponse seja chamado dentro da transação
            .orElseThrow(() -> new NotFoundException("Pedido de Aluguel não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<PedidoAluguelResponse> listar() {
        return repo.findAll().stream()
                .map(this::toResponse) // Mapeia cada item da lista dentro da transação
                .collect(Collectors.toList());
    }
    
    // MÉTODO CORRIGIDO E AGORA PRIVADO, USADO APENAS COMO HELPER
    private PedidoAluguelResponse toResponse(PedidoAluguel pedido) {
        // **CORREÇÃO 1: Acesso seguro aos dados do Cliente**
        Cliente cliente = pedido.getCliente();
        ClienteResponse clienteResp = new ClienteResponse(
            cliente.getId(), cliente.getNome(), cliente.getCpf(), 
            cliente.getRg(), cliente.getEndereco(), 
            cliente.getProfissao(), cliente.getRendimento()
        );

        // **CORREÇÃO 2: Acesso seguro aos dados do Automóvel**
        Automovel automovel = pedido.getAutomovel();
        AutomovelResponse automovelResp = new AutomovelResponse(
            automovel.getId(), automovel.getMarca(), automovel.getModelo(),
            automovel.getPlaca(), automovel.getMatricula(), 
            automovel.getAno(), automovel.isDisponivel()
        );

        ContratoCreditoResponse ccResp = null;
        if (pedido.getContratoCredito() != null) {
            // **CORREÇÃO 3: Cast explícito para Banco, como no outro service**
            Banco banco = (Banco) pedido.getContratoCredito().getBanco();
            ccResp = new ContratoCreditoResponse(
                pedido.getContratoCredito().getId(),
                pedido.getContratoCredito().getNumero(),
                pedido.getContratoCredito().getValorAprovado(),
                pedido.getContratoCredito().getDataAprovacao(),
                new BancoResponse(banco.getId(), banco.getNome(), banco.getCnpj())
            );
        }

        ContratoAluguelResponse caResp = null;
        if (pedido.getContratoAluguel() != null) {
            // **CORREÇÃO 4: Acesso seguro aos dados do Contrato de Aluguel**
            ContratoAluguel contratoAluguel = pedido.getContratoAluguel();
caResp = new ContratoAluguelResponse(
    contratoAluguel.getId(),
    contratoAluguel.getDataAssinatura(),
    contratoAluguel.getValorTotal(),
    contratoAluguel.getStatus(),
    contratoAluguel.getCliente() != null ? contratoAluguel.getCliente().getId() : null,
    contratoAluguel.getAutomovel() != null ? contratoAluguel.getAutomovel().getId() : null,
    contratoAluguel.getBanco() != null ? contratoAluguel.getBanco().getId() : null
);
        }

        return new PedidoAluguelResponse(
            pedido.getId(),
            pedido.getStatus(),
            pedido.getDataInicio(),
            pedido.getDataFim(),
            clienteResp,
            automovelResp,
            ccResp,
            caResp
        );
    }
}