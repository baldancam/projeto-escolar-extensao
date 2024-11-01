package com.sistema.escolar.repository;

import com.sistema.escolar.model.Cardapio;
import com.sistema.escolar.model.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
	boolean existsByDiaSemana(DiaSemana diaSemana);

	Optional<Cardapio> findByDiaSemana(DiaSemana diaSemana);

}
