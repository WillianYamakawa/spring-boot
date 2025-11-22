package com.tarefa.aula.dtos.venda;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class VendaSelecaoDTO {
    private int id;
    private String clienteNome;
    private String clienteDocumento;
    private String usuarioNome;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataVenda;

    private Double valorTotal;
    private List<VendaSelecaoItemDTO> items;

    public VendaSelecaoDTO(int id, String clienteNome, String clienteDocumento, String usuarioNome, LocalDateTime dataVenda, Double valorTotal, List<VendaSelecaoItemDTO> items) {
        this.id = id;
        this.clienteNome = clienteNome;
        this.clienteDocumento = clienteDocumento;
        this.usuarioNome = usuarioNome;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteDocumento() {
        return clienteDocumento;
    }

    public void setClienteDocumento(String clienteDocumento) {
        this.clienteDocumento = clienteDocumento;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<VendaSelecaoItemDTO> getItems() {
        return items;
    }

    public void setItems(List<VendaSelecaoItemDTO> items) {
        this.items = items;
    }
}
