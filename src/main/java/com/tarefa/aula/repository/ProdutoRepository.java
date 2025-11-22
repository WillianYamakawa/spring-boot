package com.tarefa.aula.repository;

import com.tarefa.aula.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findAllByAtivoTrue();
}
