package com.sistema.escolar.repository;

import com.sistema.escolar.model.Aluno;
import com.sistema.escolar.model.Usuario;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {

	List<Aluno> findByPai(Usuario pai);

}
