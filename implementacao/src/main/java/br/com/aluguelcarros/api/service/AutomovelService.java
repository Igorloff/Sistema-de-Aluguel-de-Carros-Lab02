package br.com.aluguelcarros.api.service;

import br.com.aluguelcarros.api.domain.Automovel;
import br.com.aluguelcarros.api.repository.AutomovelRepository;
import br.com.aluguelcarros.api.rest.dto.AutomovelRequest;
import br.com.aluguelcarros.api.rest.dto.AutomovelResponse;
import br.com.aluguelcarros.api.service.exception.ConflictException;
import br.com.aluguelcarros.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomovelService {

    private final AutomovelRepository repo;

    public AutomovelService(AutomovelRepository repo) {
        this.repo = repo;
    }

    public AutomovelResponse criar(AutomovelRequest req) {
        if (repo.existsByPlaca(req.placa())) {
            throw new ConflictException("Placa já cadastrada");
        }

        Automovel auto = new Automovel();
        auto.setMarca(req.marca());
        auto.setModelo(req.modelo());
        auto.setPlaca(req.placa());
        auto.setMatricula(req.matricula());
        auto.setAno(req.ano());
        auto.setDisponivel(true); // Um carro novo é sempre cadastrado como disponível

        return toResponse(repo.save(auto));
    }

    public AutomovelResponse buscarPorId(Long id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Automóvel não encontrado"));
    }

    public List<AutomovelResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public AutomovelResponse atualizar(Long id, AutomovelRequest req) {
        Automovel auto = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Automóvel não encontrado"));

        if (!auto.getPlaca().equalsIgnoreCase(req.placa()) && repo.existsByPlaca(req.placa())) {
            throw new ConflictException("Placa já cadastrada em outro veículo");
        }

        auto.setMarca(req.marca());
        auto.setModelo(req.modelo());
        auto.setPlaca(req.placa());
        auto.setMatricula(req.matricula());
        auto.setAno(req.ano());

        return toResponse(repo.save(auto));
    }

    public void remover(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Automóvel não encontrado");
        }
        repo.deleteById(id);
    }

    private AutomovelResponse toResponse(Automovel auto) {
        return new AutomovelResponse(
                auto.getId(),
                auto.getMarca(),
                auto.getModelo(),
                auto.getPlaca(),
                auto.getMatricula(),
                auto.getAno(),
                auto.isDisponivel()
        );
    }
}