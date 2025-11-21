package com.tarefa.aula.dtos.auth;

public class UsuarioLogado {
    private String usuario;
    private int id;

    public UsuarioLogado(String usuario, int id) {
        this.usuario = usuario;
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getId() {
        return id;
    }
}
