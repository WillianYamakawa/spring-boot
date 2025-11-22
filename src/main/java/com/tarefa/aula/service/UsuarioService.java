package com.tarefa.aula.service;

import com.tarefa.aula.dtos.usuario.UsuarioDTO;
import com.tarefa.aula.dtos.usuario.UsuarioListagemDTO;
import com.tarefa.aula.exceptions.ValidacaoException;
import com.tarefa.aula.jwt.JwtUtil;
import com.tarefa.aula.model.Usuario;
import com.tarefa.aula.repository.UsuarioRepository;
import com.tarefa.aula.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String login(UsuarioDTO user) throws ValidacaoException {

        var usuario = usuarioRepository.findByUsuario(user.getUsuario())
                .orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));

        if(usuario.getAtivo() == false)
            throw new ValidacaoException("Usuário inativo");

        var hash = HashUtil.sha256(user.getSenha());

        if (!usuario.getSenhaHash().equals(hash)) {
            throw new ValidacaoException("Senha inválida");
        }

        return JwtUtil.gerarToken(usuario.getId(), usuario.getUsuario());
    }

    public int cadastrar(UsuarioDTO user){

        if(user.getUsuario().isEmpty()){
            throw new ValidacaoException("Usuário nulo");
        }

        if(user.getSenha().isEmpty()){
            throw new ValidacaoException("Senha vazia");
        }

        var hash = HashUtil.sha256(user.getSenha());

        var entity = new Usuario();
        entity.setUsuario(user.getUsuario());
        entity.setSenhaHash(hash);

        usuarioRepository.save(entity);

        return entity.getId();
    }

    public List<UsuarioListagemDTO> listar() {
        var usuarios = usuarioRepository.findAllByAtivoTrue();

        return usuarios
                .stream()
                .map(e -> new UsuarioListagemDTO(e.getId(), e.getUsuario()))
                .toList();
    }

    public int atualizarUsuario(int id, UsuarioDTO usuarioAtualizado) {
        var usuario = usuarioRepository.findById(id)
            .orElseThrow(() ->
                    new ValidacaoException("Usuário não encontrado")
            );

        String senhaHash = HashUtil.sha256(usuarioAtualizado.getSenha());

        usuario.setUsuario(usuarioAtualizado.getUsuario());
        usuario.setSenhaHash(senhaHash);
        return usuarioRepository.save(usuario).getId();
    }

    public void inativar(int id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));

        usuario.setAtivo(false);

        usuarioRepository.save(usuario);
    }
}
