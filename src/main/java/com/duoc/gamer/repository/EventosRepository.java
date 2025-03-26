package com.duoc.gamer.repository;

import com.duoc.gamer.entities.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<EventoEntity, Long> {
}
