package br.com.aluguelcarros.api.service;

import br.com.aluguelcarros.api.domain.Banco;
import br.com.aluguelcarros.api.domain.ContratoCredito;
import br.com.aluguelcarros.api.repository.BancoRepository;
import br.com.aluguelcarros.api.repository.ContratoCreditoRepository;
import br.com.aluguelcarros.api.rest.dto.BancoResponse;
import br.com.aluguelcarros.api.rest.dto.ContratoCreditoRequest;
import br.com.aluguelcarros.api.rest.dto.ContratoCreditoResponse;
import br.com.aluguelcarros.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoCreditoService {

    private final ContratoCreditoRepository repo;
    private final BancoRepository bancoRepository;

    public ContratoCreditoService(ContratoCreditoRepository repo, BancoRepository bancoRepository) {
        this.repo = repo;
        this.bancoRepository = bancoRepository;
    }

    public ContratoCreditoResponse criar(ContratoCreditoRequest req) {
        Banco banco = bancoRepository.findById(req.idBanco())
                .orElseThrow(() -> new NotFoundException("Banco não encontrado"));

        ContratoCredito contrato = new ContratoCredito();
        contrato.setNumero(req.numero());
        contrato.setValorAprovado(req.valorAprovado());
        contrato.setDataAprovacao(req.dataAprovacao());
        contrato.setBanco(banco);

        return toResponse(repo.save(contrato));
    }

    public List<ContratoCreditoResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }
    
    public ContratoCreditoResponse buscarPorId(Long id) {
        return repo.findById(id).map(this::toResponse)
            .orElseThrow(() -> new NotFoundException("Contrato de Crédito não encontrado"));
    }

    // MÉTODO CORRIGIDO
    private ContratoCreditoResponse toResponse(ContratoCredito contrato) {
        // **CORREÇÃO APLICADA AQUI**
        // Fazemos um "cast" explícito para garantir que estamos lidando com um objeto Banco.
        Banco banco = (Banco) contrato.getBanco(); 
        
        BancoResponse bancoResp = new BancoResponse(
                banco.getId(),
                banco.getNome(),
                banco.getCnpj() // Agora o acesso ao getCnpj() é seguro.
        );
        
        return new ContratoCreditoResponse(
                contrato.getId(),
                contrato.getNumero(),
                contrato.getValorAprovado(),
                contrato.getDataAprovacao(),
                bancoResp
        );
    }
}