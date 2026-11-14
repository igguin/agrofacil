
package br.com.agrofacil;

import br.com.agrofacil.dto.ColheitaRequestDTO;
import br.com.agrofacil.repository.ColheitaRepository;
import br.com.agrofacil.repository.PlantacaoRepository;
import br.com.agrofacil.service.ColheitaService;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ColheitaServiceTest {

    @Test
    void naoDeveCadastrarColheitaComQuantidadeZero() {

        ColheitaRepository colheitaRepository = mock(ColheitaRepository.class);

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        ColheitaService service = new ColheitaService(
                colheitaRepository,
                plantacaoRepository);

        ColheitaRequestDTO dados = new ColheitaRequestDTO(
                1L,
                BigDecimal.ZERO,
                LocalDate.of(2027, 3, 15));

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

        verify(colheitaRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarColheitaComQuantidadeNegativa() {

        ColheitaRepository colheitaRepository = mock(ColheitaRepository.class);

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        ColheitaService service = new ColheitaService(
                colheitaRepository,
                plantacaoRepository);

        ColheitaRequestDTO dados = new ColheitaRequestDTO(
                1L,
                new BigDecimal("-100.00"),
                LocalDate.of(2027, 3, 15));

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

        verify(colheitaRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarColheitaAntesDoPlantio() {

        ColheitaRepository colheitaRepository = mock(ColheitaRepository.class);

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        ColheitaService service = new ColheitaService(
                colheitaRepository,
                plantacaoRepository);

        ColheitaRequestDTO dados = new ColheitaRequestDTO(
                1L,
                new BigDecimal("1500.00"),
                LocalDate.of(2026, 10, 1));

        br.com.agrofacil.model.Plantacao plantacao = new br.com.agrofacil.model.Plantacao();

        plantacao.setDataPlantio(LocalDate.of(2026, 11, 14));

        when(plantacaoRepository.findById(1L))
                .thenReturn(java.util.Optional.of(plantacao));

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

        verify(colheitaRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarColheitaSemData() {

        ColheitaRepository colheitaRepository = mock(ColheitaRepository.class);

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        ColheitaService service = new ColheitaService(
                colheitaRepository,
                plantacaoRepository);

        ColheitaRequestDTO dados = new ColheitaRequestDTO(
                1L,
                new BigDecimal("1500.00"),
                null);

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

        verify(colheitaRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarColheitaSemPlantacao() {

        ColheitaRepository colheitaRepository = mock(ColheitaRepository.class);

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        ColheitaService service = new ColheitaService(
                colheitaRepository,
                plantacaoRepository);

        ColheitaRequestDTO dados = new ColheitaRequestDTO(
                null,
                new BigDecimal("1500.00"),
                LocalDate.of(2027, 3, 15));

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

        verify(colheitaRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarColheitaComPlantacaoInexistente() {

        ColheitaRepository colheitaRepository = mock(ColheitaRepository.class);

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        ColheitaService service = new ColheitaService(
                colheitaRepository,
                plantacaoRepository);

        ColheitaRequestDTO dados = new ColheitaRequestDTO(
                99999L,
                new BigDecimal("1500.00"),
                LocalDate.of(2027, 3, 15));

        when(plantacaoRepository.findById(99999L))
                .thenReturn(java.util.Optional.empty());

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.NOT_FOUND, erro.getStatusCode());

        verify(colheitaRepository, never()).save(any());
    }

}
