package com.tarefa.aula.controller;

import com.tarefa.aula.dtos.http.ApiResult;
import com.tarefa.aula.dtos.usuario.UsuarioDTO;
import com.tarefa.aula.dtos.usuario.UsuarioListagemDTO;
import com.tarefa.aula.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResult<String>> login(@RequestBody UsuarioDTO usuarioDTO) {
        String token = usuarioService.login(usuarioDTO);
        return ResponseEntity.ok(ApiResult.fromSuccess(token));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ApiResult<Integer>> cadastrar(@RequestBody UsuarioDTO usuarioDTO) {
        int id = usuarioService.cadastrar(usuarioDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResult.fromSuccess(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResult<List<UsuarioListagemDTO>>> listar() {
        var result = usuarioService.listar();
        return ResponseEntity.ok(ApiResult.fromSuccess(result));
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<ApiResult<String>> inativar(@PathVariable int id) {
        usuarioService.inativar(id);
        return ResponseEntity.ok(ApiResult.fromSuccess("Inativado com sucesso"));
    }
}
