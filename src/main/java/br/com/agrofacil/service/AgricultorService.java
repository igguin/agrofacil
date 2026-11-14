
package br.com.agrofacil.service;

import br.com.agrofacil.model.Agricultor;
import br.com.agrofacil.dto.AgricultorRequestDTO;
import br.com.agrofacil.dto.AgricultorResponseDTO;
import br.com.agrofacil.repository.AgricultorRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AgricultorService {

    private final AgricultorRepository repository;

    public AgricultorService(AgricultorRepository repository) {
        this.repository = repository;
    }

    private void validar(Agricultor agricultor) {
        if (agricultor.getNome() == null ||
                agricultor.getNome().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O nome é obrigatório.");
        }

        if (agricultor.getCidade() == null ||
                agricultor.getCidade().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A cidade é obrigatória.");
        }
    }

    private AgricultorResponseDTO converterParaDTO(Agricultor agricultor) {
        return new AgricultorResponseDTO(
                agricultor.getId(),
                agricultor.getNome(),
                agricultor.getTelefone(),
                agricultor.getCidade());
    }

    public AgricultorResponseDTO cadastrar(AgricultorRequestDTO dados) {
        Agricultor agricultor = new Agricultor();

        agricultor.setNome(dados.nome());
        agricultor.setTelefone(dados.telefone());
        agricultor.setCidade(dados.cidade());

        validar(agricultor);

        return converterParaDTO(repository.save(agricultor));
    }

    public List<AgricultorResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public Agricultor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agricultor não encontrado."));
    }

    public AgricultorResponseDTO buscarDTOporId(Long id) {
        return converterParaDTO(buscarPorId(id));
    }

    public AgricultorResponseDTO atualizar(Long id, AgricultorRequestDTO dados) {
        Agricultor agricultor = buscarPorId(id);

        agricultor.setNome(dados.nome());
        agricultor.setTelefone(dados.telefone());
        agricultor.setCidade(dados.cidade());

        validar(agricultor);

        return converterParaDTO(repository.save(agricultor));
    }

    public void excluir(Long id) {
        Agricultor agricultor = buscarPorId(id);
        repository.delete(agricultor);
    }
}
