package com.sistema.escolar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.escolar.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
	List<Noticia> findAll();
}
