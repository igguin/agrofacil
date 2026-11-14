
package br.com.agrofacil.service;

import br.com.agrofacil.dto.PlantacaoRequestDTO;
import br.com.agrofacil.dto.PlantacaoResponseDTO;
import br.com.agrofacil.model.Agricultor;
import br.com.agrofacil.model.Plantacao;
import br.com.agrofacil.repository.AgricultorRepository;
import br.com.agrofacil.repository.PlantacaoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantacaoService {

    private final PlantacaoRepository plantacaoRepository;
    private final AgricultorRepository agricultorRepository;

    public PlantacaoService(
            PlantacaoRepository plantacaoRepository,
            AgricultorRepository agricultorRepository) {

        this.plantacaoRepository = plantacaoRepository;
        this.agricultorRepository = agricultorRepository;
    }

    private void validar(PlantacaoRequestDTO dados) {

        if (dados.cultura() == null || dados.cultura().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A cultura é obrigatória.");
        }

        if (dados.areaHectares() == null ||
                dados.areaHectares().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A área deve ser maior que zero.");
        }

        if (dados.dataPlantio() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A data do plantio é obrigatória.");
        }

        if (dados.agricultorId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O agricultor é obrigatório.");
        }
    }

    private PlantacaoResponseDTO converterParaDTO(Plantacao plantacao) {
        return new PlantacaoResponseDTO(
                plantacao.getId(),
                plantacao.getAgricultor().getId(),
                plantacao.getCultura(),
                plantacao.getAreaHectares(),
                plantacao.getDataPlantio());
    }

    public PlantacaoResponseDTO cadastrar(PlantacaoRequestDTO dados) {

        validar(dados);

        Agricultor agricultor = agricultorRepository
                .findById(dados.agricultorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agricultor não encontrado."));

        Plantacao plantacao = new Plantacao();

        plantacao.setAgricultor(agricultor);
        plantacao.setCultura(dados.cultura());
        plantacao.setAreaHectares(dados.areaHectares());
        plantacao.setDataPlantio(dados.dataPlantio());

        return converterParaDTO(plantacaoRepository.save(plantacao));
    }

    public List<PlantacaoResponseDTO> listarTodos() {
        return plantacaoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public List<PlantacaoResponseDTO> listarPorAgricultor(Long agricultorId) {

        if (!agricultorRepository.existsById(agricultorId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Agricultor não encontrado.");
        }

        return plantacaoRepository.findByAgricultorId(agricultorId)
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public PlantacaoResponseDTO buscarPorId(Long id) {
        Plantacao plantacao = plantacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Plantação não encontrada."));

        return converterParaDTO(plantacao);
    }

    public PlantacaoResponseDTO atualizar(Long id, PlantacaoRequestDTO dados) {

        validar(dados);

        Plantacao plantacao = plantacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Plantação não encontrada."));

        Agricultor agricultor = agricultorRepository
                .findById(dados.agricultorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agricultor não encontrado."));

        plantacao.setAgricultor(agricultor);
        plantacao.setCultura(dados.cultura());
        plantacao.setAreaHectares(dados.areaHectares());
        plantacao.setDataPlantio(dados.dataPlantio());

        return converterParaDTO(plantacaoRepository.save(plantacao));
    }

    public void excluir(Long id) {
        Plantacao plantacao = plantacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Plantação não encontrada."));

        plantacaoRepository.delete(plantacao);
    }

}
