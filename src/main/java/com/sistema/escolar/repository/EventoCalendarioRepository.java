package com.sistema.escolar.repository;

import com.sistema.escolar.model.EventoCalendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoCalendarioRepository extends JpaRepository<EventoCalendario, Long> {
}
