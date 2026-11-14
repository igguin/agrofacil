
package br.com.agrofacil.controller;

import br.com.agrofacil.dto.ColheitaRequestDTO;
import br.com.agrofacil.dto.ColheitaResponseDTO;
import br.com.agrofacil.service.ColheitaService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colheitas")
public class ColheitaController {

    private final ColheitaService service;

    public ColheitaController(ColheitaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ColheitaResponseDTO cadastrar(
            @RequestBody ColheitaRequestDTO dados) {
        return service.cadastrar(dados);
    }

    @GetMapping
    public List<ColheitaResponseDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ColheitaResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ColheitaResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody ColheitaRequestDTO dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

}
