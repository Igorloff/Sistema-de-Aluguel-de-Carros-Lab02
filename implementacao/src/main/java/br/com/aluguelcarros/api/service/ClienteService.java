package br.com.aluguelcarros.api.service;

import br.com.aluguelcarros.api.domain.Cliente;
import br.com.aluguelcarros.api.repository.ClienteRepository;
import br.com.aluguelcarros.api.rest.dto.ClienteRequest;
import br.com.aluguelcarros.api.rest.dto.ClienteResponse;
import br.com.aluguelcarros.api.service.exception.ConflictException;
import br.com.aluguelcarros.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

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
        // Campos atualizados
        c.setNome(req.nome());
        c.setCpf(req.cpf());
        c.setRg(req.rg());
        c.setEndereco(req.endereco());
        c.setProfissao(req.profissao());
        c.setRendimento(req.rendimento());

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
            throw new ConflictException("CPF já cadastrado para outro cliente");

        // Campos atualizados
        c.setNome(req.nome());
        c.setCpf(req.cpf());
        c.setRg(req.rg());
        c.setEndereco(req.endereco());
        c.setProfissao(req.profissao());
        c.setRendimento(req.rendimento());

        return toResponse(repo.save(c));
    }

    public void remover(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Cliente não encontrado");
        }
        repo.deleteById(id);
    }

    private ClienteResponse toResponse(Cliente c) {
        // Mapeamento atualizado para o DTO de resposta
        return new ClienteResponse(
                c.getId(),
                c.getNome(),
                c.getCpf(),
                c.getRg(),
                c.getEndereco(),
                c.getProfissao(),
                c.getRendimento()
        );
    }
}