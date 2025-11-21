package com.tarefa.aula.dtos.cliente;

import com.tarefa.aula.exceptions.ValidacaoException;

public class ClienteDTO {
    private int id;
    private String nome;
    protected String documento;
    private String telefone;
    private String email;
    private String endereco;

    public ClienteDTO(int id, String nome, String documento, String telefone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public void validar() {
        if (nome == null || nome.isBlank()) {
            throw new ValidacaoException("Campo 'nome' vazio");
        }

        if (documento == null || documento.isBlank()) {
            throw new ValidacaoException("Campo 'documento' vazio");
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
