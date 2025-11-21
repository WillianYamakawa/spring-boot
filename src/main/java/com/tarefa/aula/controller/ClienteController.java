package com.tarefa.aula.controller;


import com.tarefa.aula.dtos.cliente.ClienteDTO;
import com.tarefa.aula.dtos.http.ApiResult;
import com.tarefa.aula.dtos.produto.ProdutoDTO;
import com.tarefa.aula.dtos.usuario.UsuarioDTO;
import com.tarefa.aula.dtos.usuario.UsuarioListagemDTO;
import com.tarefa.aula.service.ClienteService;
import com.tarefa.aula.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService clienteService) {
        this.service = clienteService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ApiResult<Integer>> cadastrar(@RequestBody ClienteDTO body) {
        int id = service.cadastrar(body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResult.fromSuccess(id));
    }

    @PostMapping("/editar")
    public ResponseEntity<ApiResult<Integer>> editar(@RequestBody ClienteDTO body) {
        int id = service.editar(body);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.fromSuccess(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResult<List<ClienteDTO>>> listar() {
        var result = service.listar();
        return ResponseEntity.ok(ApiResult.fromSuccess(result));
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<ApiResult<String>> inativar(@PathVariable int id) {
        service.inativar(id);
        return ResponseEntity.ok(ApiResult.fromSuccess("Inativado com sucesso"));
    }
}
