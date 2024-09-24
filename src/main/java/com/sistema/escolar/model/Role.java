package com.sistema.escolar.model;

import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.*;

@Entity
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name; // Este será o campo usado como authority

	// Implementação do método da interface GrantedAuthority
	@Override
	public String getAuthority() {
		return name; // Retorna o nome da role como autoridade
	}

	// Getter e Setter para 'name'
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
