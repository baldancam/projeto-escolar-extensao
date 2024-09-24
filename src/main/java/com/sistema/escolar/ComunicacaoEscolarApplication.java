package com.sistema.escolar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ComunicacaoEscolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComunicacaoEscolarApplication.class, args);

		System.out.println("Classe Principal iniciou corretamente Matheus!!!");
	}
}
