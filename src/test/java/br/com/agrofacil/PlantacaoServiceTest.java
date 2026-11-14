
package br.com.agrofacil;

import br.com.agrofacil.dto.PlantacaoRequestDTO;
import br.com.agrofacil.repository.AgricultorRepository;
import br.com.agrofacil.repository.PlantacaoRepository;
import br.com.agrofacil.service.PlantacaoService;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PlantacaoServiceTest {

    @Test
    void naoDeveCadastrarPlantacaoComAreaZero() {

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        AgricultorRepository agricultorRepository = mock(AgricultorRepository.class);

        PlantacaoService service = new PlantacaoService(
                plantacaoRepository,
                agricultorRepository);

        PlantacaoRequestDTO dados = new PlantacaoRequestDTO(
                1L,
                "Milho",
                BigDecimal.ZERO,
                LocalDate.of(2026, 11, 14));

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

        verify(plantacaoRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarPlantacaoComAreaNegativa() {

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        AgricultorRepository agricultorRepository = mock(AgricultorRepository.class);

        PlantacaoService service = new PlantacaoService(
                plantacaoRepository,
                agricultorRepository);

        PlantacaoRequestDTO dados = new PlantacaoRequestDTO(
                1L,
                "Soja",
                new BigDecimal("-5.00"),
                LocalDate.of(2026, 11, 14));

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

        verify(plantacaoRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarPlantacaoSemCultura() {

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        AgricultorRepository agricultorRepository = mock(AgricultorRepository.class);

        PlantacaoService service = new PlantacaoService(
                plantacaoRepository,
                agricultorRepository);

        PlantacaoRequestDTO dados = new PlantacaoRequestDTO(
                1L,
                "",
                new BigDecimal("10.00"),
                LocalDate.of(2026, 11, 14));

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

        verify(plantacaoRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarPlantacaoSemData() {

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        AgricultorRepository agricultorRepository = mock(AgricultorRepository.class);

        PlantacaoService service = new PlantacaoService(
                plantacaoRepository,
                agricultorRepository);

        PlantacaoRequestDTO dados = new PlantacaoRequestDTO(
                1L,
                "Milho",
                new BigDecimal("10.00"),
                null);

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

        verify(plantacaoRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarPlantacaoSemAgricultor() {

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        AgricultorRepository agricultorRepository = mock(AgricultorRepository.class);

        PlantacaoService service = new PlantacaoService(
                plantacaoRepository,
                agricultorRepository);

        PlantacaoRequestDTO dados = new PlantacaoRequestDTO(
                null,
                "Milho",
                new BigDecimal("10.00"),
                LocalDate.of(2026, 11, 14));

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

        verify(plantacaoRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarPlantacaoComAgricultorInexistente() {

        PlantacaoRepository plantacaoRepository = mock(PlantacaoRepository.class);

        AgricultorRepository agricultorRepository = mock(AgricultorRepository.class);

        PlantacaoService service = new PlantacaoService(
                plantacaoRepository,
                agricultorRepository);

        PlantacaoRequestDTO dados = new PlantacaoRequestDTO(
                99999L,
                "Soja",
                new BigDecimal("10.00"),
                LocalDate.of(2026, 11, 14));

        when(agricultorRepository.findById(99999L))
                .thenReturn(java.util.Optional.empty());

        ResponseStatusException erro = assertThrows(
                ResponseStatusException.class,
                () -> service.cadastrar(dados));

        assertEquals(HttpStatus.NOT_FOUND, erro.getStatusCode());

        verify(plantacaoRepository, never()).save(any());
    }

}
