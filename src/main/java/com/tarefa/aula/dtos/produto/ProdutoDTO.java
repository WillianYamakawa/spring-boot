package com.tarefa.aula.dtos.produto;

import com.tarefa.aula.exceptions.ValidacaoException;

public class ProdutoDTO {
    private int id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer estoque;

    // Construtor
    public ProdutoDTO(int id, String nome, String descricao, Double preco, Integer estoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public void validar(){
        if(nome == null || nome.isEmpty())
            throw new ValidacaoException("Campo 'nome' vazio");
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

