
package br.com.agrofacil.service;

import br.com.agrofacil.dto.ColheitaRequestDTO;
import br.com.agrofacil.dto.ColheitaResponseDTO;
import br.com.agrofacil.model.Colheita;
import br.com.agrofacil.model.Plantacao;
import br.com.agrofacil.repository.ColheitaRepository;
import br.com.agrofacil.repository.PlantacaoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ColheitaService {

    private final ColheitaRepository colheitaRepository;
    private final PlantacaoRepository plantacaoRepository;

    public ColheitaService(
            ColheitaRepository colheitaRepository,
            PlantacaoRepository plantacaoRepository) {

        this.colheitaRepository = colheitaRepository;
        this.plantacaoRepository = plantacaoRepository;
    }

    private void validar(ColheitaRequestDTO dados) {

        if (dados.plantacaoId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A plantação é obrigatória.");
        }

        if (dados.quantidadeKg() == null ||
                dados.quantidadeKg().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A quantidade colhida deve ser maior que zero.");
        }

        if (dados.dataColheita() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A data da colheita é obrigatória.");
        }
    }

    private ColheitaResponseDTO converterParaDTO(Colheita colheita) {
        return new ColheitaResponseDTO(
                colheita.getId(),
                colheita.getPlantacao().getId(),
                colheita.getQuantidadeKg(),
                colheita.getDataColheita());
    }

    public ColheitaResponseDTO cadastrar(ColheitaRequestDTO dados) {

        validar(dados);

        Plantacao plantacao = plantacaoRepository
                .findById(dados.plantacaoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Plantação não encontrada."));

        if (dados.dataColheita().isBefore(plantacao.getDataPlantio())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A colheita não pode acontecer antes do plantio.");
        }

        Colheita colheita = new Colheita();

        colheita.setPlantacao(plantacao);
        colheita.setQuantidadeKg(dados.quantidadeKg());
        colheita.setDataColheita(dados.dataColheita());

        return converterParaDTO(colheitaRepository.save(colheita));
    }

    public List<ColheitaResponseDTO> listarTodos() {
        return colheitaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public ColheitaResponseDTO buscarPorId(Long id) {
        Colheita colheita = colheitaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Colheita não encontrada."));

        return converterParaDTO(colheita);
    }

    public ColheitaResponseDTO atualizar(Long id, ColheitaRequestDTO dados) {

        validar(dados);

        Colheita colheita = colheitaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Colheita não encontrada."));

        Plantacao plantacao = plantacaoRepository
                .findById(dados.plantacaoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Plantação não encontrada."));

        if (dados.dataColheita().isBefore(plantacao.getDataPlantio())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A colheita não pode acontecer antes do plantio.");
        }

        colheita.setPlantacao(plantacao);
        colheita.setQuantidadeKg(dados.quantidadeKg());
        colheita.setDataColheita(dados.dataColheita());

        return converterParaDTO(colheitaRepository.save(colheita));
    }

    public void excluir(Long id) {
        Colheita colheita = colheitaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Colheita não encontrada."));

        colheitaRepository.delete(colheita);
    }

}
