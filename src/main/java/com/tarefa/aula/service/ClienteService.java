package com.tarefa.aula.service;

import com.tarefa.aula.dtos.cliente.ClienteDTO;
import com.tarefa.aula.exceptions.ValidacaoException;
import com.tarefa.aula.model.Cliente;
import com.tarefa.aula.repository.ClienteRepository;
import com.tarefa.aula.util.DocumentoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> listar(){
        var clientes = clienteRepository.findAllByAtivoTrue();

        return clientes
                .stream()
                .map(e -> new ClienteDTO(
                        e.getId(),
                        e.getNome(),
                        e.getDocumento(),
                        e.getTelefone(),
                        e.getEmail(),
                        e.getEndereco()
                ))
                .toList();
    }

    public int cadastrar(ClienteDTO model) {
        model.validar();

        var entity = new Cliente();

        loadEntity(entity, model);

        clienteRepository.save(entity);

        return entity.getId();
    }

    public int editar(ClienteDTO model){
        model.validar();

        var entity = clienteRepository.findById(model.getId())
                .orElseThrow(() -> new ValidacaoException("Cliente não encontrado"));

        loadEntity(entity, model);

        clienteRepository.save(entity);

        return entity.getId();
    }

    public void inativar(int id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Cliente não encontrado"));

        cliente.setAtivo(false);

        clienteRepository.save(cliente);
    }

    private void loadEntity(Cliente entity, ClienteDTO model){
        entity.setNome(model.getNome());
        entity.setDocumento(DocumentoUtil.retirarFormatacao(model.getDocumento()));
        entity.setTelefone(model.getTelefone());
        entity.setEmail(model.getEmail());
        entity.setEndereco(model.getEndereco());
    }
}
