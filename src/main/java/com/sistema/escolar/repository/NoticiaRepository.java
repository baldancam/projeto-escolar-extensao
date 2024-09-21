package com.sistema.escolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.escolar.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
}
