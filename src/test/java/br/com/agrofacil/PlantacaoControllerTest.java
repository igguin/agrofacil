
package br.com.agrofacil;

import br.com.agrofacil.controller.PlantacaoController;
import br.com.agrofacil.service.PlantacaoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlantacaoController.class)
class PlantacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PlantacaoService service;

    @Test
    void deveRetornar400QuandoAreaForZero() throws Exception {

        when(service.cadastrar(any()))
                .thenThrow(new IllegalArgumentException(
                        "A área deve ser maior que zero."));

        mockMvc.perform(post("/plantacoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "agricultorId": 4,
                          "cultura": "Milho",
                          "areaHectares": 0,
                          "dataPlantio": "2026-11-14"
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro")
                        .value("A área deve ser maior que zero."));

        verify(service).cadastrar(any());
    }
}
