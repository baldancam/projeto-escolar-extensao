package com.sistema.escolar.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.escolar.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, UUID> {
	List<Noticia> findAll();
}
