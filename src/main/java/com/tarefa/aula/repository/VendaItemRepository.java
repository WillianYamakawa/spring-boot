package com.tarefa.aula.repository;

import com.tarefa.aula.model.VendaItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaItemRepository extends JpaRepository<VendaItem, Integer> {
    List<VendaItem> findAllByVendaId(Integer vendaId);
}
