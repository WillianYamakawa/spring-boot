package com.tarefa.aula.repository;

import com.tarefa.aula.model.VendaItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaItemRepository extends JpaRepository<VendaItem, Integer> {
}
