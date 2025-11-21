package com.tarefa.aula.controller;

import com.tarefa.aula.dtos.http.ApiResult;
import com.tarefa.aula.dtos.produto.ProdutoDTO;
import com.tarefa.aula.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ApiResult<Integer>> cadastrar(@RequestBody ProdutoDTO body) {
        int id = service.cadastrar(body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResult.fromSuccess(id));
    }

    @PostMapping("/editar")
    public ResponseEntity<ApiResult<Integer>> editar(@RequestBody ProdutoDTO body) {
        int id = service.editar(body);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.fromSuccess(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResult<List<ProdutoDTO>>> listar() {
        var result = service.listar();
        return ResponseEntity.ok(ApiResult.fromSuccess(result));
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<ApiResult<String>> inativar(@PathVariable int id) {
        service.inativar(id);
        return ResponseEntity.ok(ApiResult.fromSuccess("Inativado com sucesso"));
    }
}

