
package br.com.agrofacil.controller;

import br.com.agrofacil.dto.PlantacaoRequestDTO;
import br.com.agrofacil.dto.PlantacaoResponseDTO;
import br.com.agrofacil.service.PlantacaoService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantacoes")
public class PlantacaoController {

    private final PlantacaoService service;

    public PlantacaoController(PlantacaoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlantacaoResponseDTO cadastrar(
            @RequestBody PlantacaoRequestDTO dados) {
        return service.cadastrar(dados);
    }

    @GetMapping
    public List<PlantacaoResponseDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/agricultor/{agricultorId}")
    public List<PlantacaoResponseDTO> listarPorAgricultor(
            @PathVariable Long agricultorId) {
        return service.listarPorAgricultor(agricultorId);
    }

    @GetMapping("/{id}")
    public PlantacaoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PlantacaoResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody PlantacaoRequestDTO dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

}
