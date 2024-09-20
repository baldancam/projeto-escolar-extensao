package com.sistema.escolar.comunicacao_escolar;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.sistema.escolar.model.Aluno;
import com.sistema.escolar.model.Funcionario;
import com.sistema.escolar.model.Pai;
import com.sistema.escolar.model.Professor;

@SpringBootApplication
@EntityScan(basePackages = "com.sistema.escolar.model")
public class ComunicacaoEscolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComunicacaoEscolarApplication.class, args);

		// Criando o Pai
		Pai pai = new Pai("Carlos Silva", "(48) 99999-1234", "carlos.silva@gmail.com");

		// Criando um aluno e associando ao Pai
		Aluno aluno1 = new Aluno("João Silva", "12345", LocalDate.of(2010, 5, 12), "Manhã", pai);
		Aluno aluno2 = new Aluno("Maria Silva", "67890", LocalDate.of(2012, 8, 15), "Tarde", pai);

		// Exibindo as informações dos alunos
		System.out.println(aluno1.toString());
		System.out.println(aluno2.toString());

		// Criando um professor
		Professor professor = new Professor("Ana Maria", "54321", LocalDate.of(1980, 9, 7), "(48) 99999-9988",
				"ana.maria@gmail.com", "Quinta Série");

		// Exibindo as informações do professor
		System.out.println(professor.toString());

		// Criando um funcionário (sem turma, pois não é professor)
		Funcionario funcionario = new Funcionario("José de Souza", "13579", LocalDate.of(1985, 3, 25),
				"(48) 99999-5678", "jose.souza@empresa.com", "SECRETARIA");

		// Exibindo as informações do funcionário
		System.out.println(funcionario.toString());
	}
}
