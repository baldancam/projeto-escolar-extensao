package com.sistema.escolar.comunicacao_escolar;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sistema.escolar.model.Aluno;
import com.sistema.escolar.model.Professor;

@SpringBootApplication
public class ComunicacaoEscolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComunicacaoEscolarApplication.class, args);

		// Criação de um aluno 
		Aluno aluno = new Aluno("","", LocalDate.of(2000,01,02), "");

		// Exibindo as informações do aluno
		System.out.println(aluno.toString());

		
		// Criação de um professor
		Professor professor = new Professor("prof da silva", "12345", LocalDate.of(1980, 9, 7), "primeiro ano", "(48) 99999-9988", "teste@prof.com");

		// Exibindo as informações do aluno
		System.out.println(professor.toString());
		
	}

}
