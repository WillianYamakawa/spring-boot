package com.tarefa.aula.service;

import com.tarefa.aula.dtos.produto.ProdutoDTO;
import com.tarefa.aula.dtos.venda.VendaCadastroDTO;
import com.tarefa.aula.dtos.venda.VendaListagemDTO;
import com.tarefa.aula.dtos.venda.VendaSelecaoDTO;
import com.tarefa.aula.dtos.venda.VendaSelecaoItemDTO;
import com.tarefa.aula.exceptions.ValidacaoException;
import com.tarefa.aula.helpers.UsuarioLogadoHelper;
import com.tarefa.aula.model.Produto;
import com.tarefa.aula.model.Venda;
import com.tarefa.aula.model.VendaItem;
import com.tarefa.aula.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VendaItemRepository vendaItemRepository;

    public List<VendaListagemDTO> listar(){
        var vendas = vendaRepository.findAllByAtivoTrue();

        return vendas
                .stream()
                .map(e -> {

                    var cliente = clienteRepository.findById(e.getClienteId()).get();

                    var usuario = usuarioRepository.findById(e.getUsuarioId()).get();

                    return new VendaListagemDTO(
                            e.getId(),
                            cliente.getNome(),
                            cliente.getDocumento(),
                            usuario.getUsuario(),
                            e.getDataVenda(),
                            e.getValorTotal()
                    );
                })
                .toList();
    }

    public VendaSelecaoDTO get(int id){
        var venda = vendaRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("Venda não encontrada"));

        var cliente = clienteRepository.findById(venda.getClienteId()).get();

        var usuario = usuarioRepository.findById(UsuarioLogadoHelper.getUsuarioLogado().getId()).get();

        List<VendaSelecaoItemDTO> items = new ArrayList<>();

        var entityItems = vendaItemRepository.findAllByVendaId(venda.getId());

        for(var entityItem : entityItems){
            var produto = produtoRepository.findById(entityItem.getProdutoId()).get();

            var itemModel = new VendaSelecaoItemDTO(
                entityItem.getId(),
                produto.getNome(),
                entityItem.getQuantidade(),
                entityItem.getTotal()
        );

            items.add(itemModel);
        }

        var model = new VendaSelecaoDTO(
            venda.getId(),
            cliente.getNome(),
            cliente.getDocumento(),
            usuario.getUsuario(),
            venda.getDataVenda(),
            venda.getValorTotal(),
            items
        );

        return model;
    }

    public int cadastrar(VendaCadastroDTO model) {
        model.validar();

        var entity = new Venda();
        entity.setClienteId(model.getIdCliente());
        entity.setUsuarioId(UsuarioLogadoHelper.getUsuarioLogado().getId());
        entity.setValorTotal(model
                .getItems()
                .stream()
                .mapToDouble(e -> e.getPrecoUnitario() * e.getQuantidade())
                .sum()
        );

        vendaRepository.save(entity);

        for(var item : model.getItems()){
            var produto = produtoRepository.findById(item.getIdProduto())
                    .orElseThrow(() -> new ValidacaoException("Produto não encontrado"));

            var itemEntity = new VendaItem();
            itemEntity.setVendaId(entity.getId());
            itemEntity.setProdutoId(item.getIdProduto());
            itemEntity.setQuantidade(item.getQuantidade());
            itemEntity.setPrecoUnitario(item.getPrecoUnitario());
            itemEntity.setTotal(item.getQuantidade() * item.getPrecoUnitario());

            vendaItemRepository.save(itemEntity);

            produto.setEstoque(produto.getEstoque() - item.getQuantidade());

            produtoRepository.save(produto);
        }

        return entity.getId();
    }

    public void inativar(int id) {
        var venda = vendaRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Venda não encontrada"));

        venda.setAtivo(false);

        var items = vendaItemRepository.findAllByVendaId(id);

        for(var item : items){
            var produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new ValidacaoException("Produto não encontrado"));

            produto.setEstoque(produto.getEstoque() + item.getQuantidade());
        }

        vendaRepository.save(venda);
    }
}
