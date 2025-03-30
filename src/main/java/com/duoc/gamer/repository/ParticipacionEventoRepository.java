package com.duoc.gamer.repository;

import com.duoc.gamer.entities.ParticipacionEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipacionEventoRepository extends JpaRepository<ParticipacionEventoEntity, Long> {
}
