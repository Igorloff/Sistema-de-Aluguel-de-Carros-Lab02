package br.com.aluguelcarros.api.service;

import br.com.aluguelcarros.api.domain.*;
import br.com.aluguelcarros.api.repository.*;
import br.com.aluguelcarros.api.rest.dto.LoginRequest;
import br.com.aluguelcarros.api.rest.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final ClienteRepository clienteRepo;
    private final AgenteRepository agenteRepo;

    public LoginService(ClienteRepository clienteRepo, AgenteRepository agenteRepo) {
        this.clienteRepo = clienteRepo;
        this.agenteRepo = agenteRepo;
    }

    public LoginResponse autenticar(LoginRequest req) {
        // 1. Tenta como cliente (CPF)
        Cliente cliente = clienteRepo.findAll().stream()
                .filter(c -> c.getCpf().equals(req.documento()) && c.getNome().equalsIgnoreCase(req.nome()))
                .findFirst().orElse(null);

        if (cliente != null) {
            return new LoginResponse(
                    "CLIENTE",
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getCpf(),
                    "Login efetuado com sucesso!"
            );
        }

        // 2. Tenta como agente (CNPJ)
        Agente agente = agenteRepo.findAll().stream()
                .filter(a -> a.getCnpj().equals(req.documento()) && a.getNome().equalsIgnoreCase(req.nome()))
                .findFirst().orElse(null);

        if (agente != null) {
            return new LoginResponse(
                    "AGENTE",
                    agente.getId(),
                    agente.getNome(),
                    agente.getCnpj(),
                    "Login efetuado com sucesso!"
            );
        }

        // 3. Caso nenhum encontrado
        throw new RuntimeException("Usuário não encontrado ou credenciais inválidas.");
    }
}
