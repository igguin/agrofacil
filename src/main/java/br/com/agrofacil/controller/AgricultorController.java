
package br.com.agrofacil.controller;

import br.com.agrofacil.model.Agricultor;
import br.com.agrofacil.dto.AgricultorRequestDTO;
import br.com.agrofacil.dto.AgricultorResponseDTO;
import br.com.agrofacil.service.AgricultorService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agricultores")
public class AgricultorController {

    private final AgricultorService service;

    public AgricultorController(AgricultorService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgricultorResponseDTO cadastrar(
            @RequestBody AgricultorRequestDTO dados) {
        return service.cadastrar(dados);
    }

    @GetMapping
    public List<AgricultorResponseDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public AgricultorResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarDTOporId(id);
    }

    @PutMapping("/{id}")
    public AgricultorResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody AgricultorRequestDTO dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
