package com.tarefa.aula.dtos.venda;

public class VendaSelecaoItemDTO {
    private int id;
    private String produtoNome;
    private int quantidade;
    private Double valorTotal;

    public VendaSelecaoItemDTO(int id, String produtoNome, int quantidade, Double valorTotal) {
        this.id = id;
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
