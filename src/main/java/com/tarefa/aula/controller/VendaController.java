package com.tarefa.aula.controller;

import com.tarefa.aula.dtos.http.ApiResult;
import com.tarefa.aula.dtos.produto.ProdutoDTO;
import com.tarefa.aula.dtos.venda.VendaCadastroDTO;
import com.tarefa.aula.dtos.venda.VendaListagemDTO;
import com.tarefa.aula.service.ProdutoService;
import com.tarefa.aula.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {
    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ApiResult<Integer>> cadastrar(@RequestBody VendaCadastroDTO body) {
        int id = service.cadastrar(body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResult.fromSuccess(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResult<List<VendaListagemDTO>>> listar() {
        var result = service.listar();
        return ResponseEntity.ok(ApiResult.fromSuccess(result));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResult<List<VendaListagemDTO>>> get(@PathVariable int id) {
//        var result = service.();
//        return ResponseEntity.ok(ApiResult.fromSuccess(result));
//    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<ApiResult<String>> inativar(@PathVariable int id) {
        service.inativar(id);
        return ResponseEntity.ok(ApiResult.fromSuccess("Inativado com sucesso"));
    }
}
