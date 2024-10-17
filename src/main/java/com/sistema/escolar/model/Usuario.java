package com.sistema.escolar.model;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema.escolar.dto.UsuarioRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	private UUID id;

	private String nome;
	private String telefone;
	private String email;
	private String password;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
	private List<Turma> turmas; // Professores têm várias turmas

	@OneToMany(mappedBy = "pai", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Aluno> alunos;

	public Usuario(String email, String password, UserRole role) {

		this.email = email;
		this.password = password;
		this.role = role;

	}

	// Construtor utilizando o DTO
	public Usuario(UsuarioRequestDTO data) {
		if (data.password() == null || data.password().isEmpty()) {
			throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.nome = data.nome();
		this.telefone = data.telefone();
		this.email = data.email();
		this.password = passwordEncoder.encode(data.password());
		this.role = data.role();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == UserRole.ADMIN)
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_PROFESSOR"),
					new SimpleGrantedAuthority("ROLE_PAI"));
		else if (this.role == UserRole.PROFESSOR)
			return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"), new SimpleGrantedAuthority("ROLE_PAI"));
		else
			return List.of(new SimpleGrantedAuthority("ROLE_PAI"));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email; // Usar email como nome de usuário
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}