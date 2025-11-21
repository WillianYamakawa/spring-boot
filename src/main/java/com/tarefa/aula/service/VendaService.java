package com.tarefa.aula.service;

import com.tarefa.aula.dtos.produto.ProdutoDTO;
import com.tarefa.aula.exceptions.ValidacaoException;
import com.tarefa.aula.model.Produto;
import com.tarefa.aula.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {
    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoDTO> listar(){
        var produtos = repository.findAll();

        return produtos
                .stream()
                .map(e -> new ProdutoDTO(
                        e.getId(),
                        e.getNome(),
                        e.getDescricao(),
                        e.getPreco(),
                        e.getEstoque()
                ))
                .toList();
    }

    public int cadastrar(ProdutoDTO model) {
        model.validar();

        var entity = new Produto();

        loadEntity(entity, model);

        repository.save(entity);

        return entity.getId();
    }

    public void inativar(int id) {
        var produto = repository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Produto não encontrado"));

        produto.setAtivo(false);

        repository.save(produto);
    }

    private void loadEntity(Produto entity, ProdutoDTO model){
        entity.setNome(model.getNome());
        entity.setDescricao(model.getDescricao());
        entity.setPreco(model.getPreco());
        entity.setEstoque(model.getEstoque());
    }
}
