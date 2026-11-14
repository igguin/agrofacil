
package br.com.agrofacil.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import br.com.agrofacil.dto.ErroResponseDTO;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> tratarDadosInvalidos(
            IllegalArgumentException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroResponseDTO> tratarErroHttp(
            ResponseStatusException ex) {

        String mensagem = ex.getReason() != null
                ? ex.getReason()
                : "Erro na requisição.";

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new ErroResponseDTO(mensagem));
    }

}
