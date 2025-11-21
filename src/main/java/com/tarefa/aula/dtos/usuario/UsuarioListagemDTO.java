package com.tarefa.aula.dtos.usuario;

public class UsuarioListagemDTO {
    private int id;
    private String usuario;

    public UsuarioListagemDTO(int id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }
}
