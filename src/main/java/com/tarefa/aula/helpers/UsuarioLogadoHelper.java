package com.tarefa.aula.helpers;

import com.tarefa.aula.dtos.auth.UsuarioLogado;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioLogadoHelper {

    public static UsuarioLogado getUsuarioLogado() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return null;
        return (UsuarioLogado) auth.getPrincipal();
    }
}