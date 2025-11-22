package com.tarefa.aula.repository;

import com.tarefa.aula.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findAllByAtivoTrue();
}
