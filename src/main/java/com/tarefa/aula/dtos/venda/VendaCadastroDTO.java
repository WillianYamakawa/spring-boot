package com.tarefa.aula.dtos.venda;

import com.tarefa.aula.exceptions.ValidacaoException;

import java.util.List;

public class VendaCadastroDTO {
    private int idCliente;
    private List<VendaCadastroItemDTO> items;

    public VendaCadastroDTO(int idCliente, List<VendaCadastroItemDTO> items) {
        this.idCliente = idCliente;
        this.items = items;
    }

    public void validar(){
        for(var item : items){
            if(item.getQuantidade() < 1)
                throw new ValidacaoException("Quantidade inválida!");

            if(item.getPrecoUnitario() < 0)
                throw new ValidacaoException("Preço inválido!");
        }
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<VendaCadastroItemDTO> getItems() {
        return items;
    }

    public void setItems(List<VendaCadastroItemDTO> items) {
        this.items = items;
    }
}
