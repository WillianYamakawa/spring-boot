package com.tarefa.aula.repository;

import com.tarefa.aula.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findAllByAtivoTrue();
}
