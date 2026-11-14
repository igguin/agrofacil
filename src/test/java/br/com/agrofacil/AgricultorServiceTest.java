
package br.com.agrofacil;

import br.com.agrofacil.dto.AgricultorRequestDTO;
import br.com.agrofacil.repository.AgricultorRepository;
import br.com.agrofacil.service.AgricultorService;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AgricultorServiceTest {

        @Test
        void naoDeveCadastrarAgricultorSemNome() {

                AgricultorRepository repository = mock(AgricultorRepository.class);

                AgricultorService service = new AgricultorService(repository);

                AgricultorRequestDTO dados = new AgricultorRequestDTO(
                                "",
                                "11999999999",
                                "Campinas",
                                null);

                ResponseStatusException erro = assertThrows(
                                ResponseStatusException.class,
                                () -> service.cadastrar(dados));

                assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

                verify(repository, never()).save(any());
        }

        @Test
        void naoDeveCadastrarAgricultorSemCidade() {

                AgricultorRepository repository = mock(AgricultorRepository.class);

                AgricultorService service = new AgricultorService(repository);

                AgricultorRequestDTO dados = new AgricultorRequestDTO(
                                "Carlos Ferreira",
                                "11999999999",
                                "",
                                null);

                ResponseStatusException erro = assertThrows(
                                ResponseStatusException.class,
                                () -> service.cadastrar(dados));

                assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());

                verify(repository, never()).save(any());
        }

        @Test
        void deveCadastrarAgricultorComDadosValidos() {

                AgricultorRepository repository = mock(AgricultorRepository.class);

                AgricultorService service = new AgricultorService(repository);

                AgricultorRequestDTO dados = new AgricultorRequestDTO(
                                "Pedro Silva",
                                "11988887777",
                                "Campinas",
                                "pedro@exemplo.com");

                br.com.agrofacil.model.Agricultor agricultorSalvo = new br.com.agrofacil.model.Agricultor();

                agricultorSalvo.setId(10L);
                agricultorSalvo.setNome("Pedro Silva");
                agricultorSalvo.setTelefone("11988887777");
                agricultorSalvo.setCidade("Campinas");
                agricultorSalvo.setEmail("pedro@exemplo.com");

                when(repository.save(any())).thenReturn(agricultorSalvo);

                var resultado = service.cadastrar(dados);

                assertEquals(10L, resultado.id());
                assertEquals("Pedro Silva", resultado.nome());
                assertEquals("Campinas", resultado.cidade());
                assertEquals("pedro@exemplo.com", resultado.email());

                verify(repository, times(1)).save(any());
        }

}
