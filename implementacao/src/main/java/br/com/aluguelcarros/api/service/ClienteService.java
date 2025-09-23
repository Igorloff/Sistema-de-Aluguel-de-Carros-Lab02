package br.com.aluguelcarros.api.service;

import br.com.aluguelcarros.api.domain.Cliente;
import br.com.aluguelcarros.api.repository.ClienteRepository;
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

    @Transactional
    public Cliente criar(Cliente cliente) {
        if (repo.existsByCpf(cliente.getCpf())) {
            throw new ConflictException("CPF já cadastrado");
        }
        return repo.save(cliente);
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return repo.findAll();
    }

    @Transactional
    public Cliente atualizar(Long id, Cliente novo) {
        Cliente cliente = buscarPorId(id);

        // se CPF mudou, verificar unicidade
        if (!cliente.getCpf().equals(novo.getCpf()) && repo.existsByCpf(novo.getCpf())) {
            throw new ConflictException("CPF já cadastrado");
        }

        cliente.setCpf(novo.getCpf());
        cliente.setRg(novo.getRg());
        cliente.setNome(novo.getNome());
        cliente.setEndereco(novo.getEndereco());
        cliente.setProfissao(novo.getProfissao());

        return repo.save(cliente);
    }

    @Transactional
    public void remover(Long id) {
        Cliente cliente = buscarPorId(id);
        repo.delete(cliente);
    }
}
