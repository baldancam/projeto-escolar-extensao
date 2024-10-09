package com.sistema.escolar.model;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import com.sistema.escolar.dto.UsuarioRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity(name = "tb_usuario")
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	private UUID id;

	private String nome;
	private String telefone;
	private String email;
	private String password;
	private String funcao; // Para diferenciar os tipos de usuário (ADM, Professor, Pai)
	private UserRole role;

	@OneToMany(mappedBy = "pai", cascade = CascadeType.ALL)
	private List<Aluno> alunos;

	// Construtor utilizando o DTO
	public Usuario(UsuarioRequestDTO data) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.nome = data.nome();
		this.telefone = data.telefone();
		this.email = data.email();
		this.password = passwordEncoder.encode(data.password());
		this.funcao = data.funcao();
		this.role = UserRole.valueOf(data.funcao().toUpperCase());
	}

	public boolean isPai() {
		return "PAI".equalsIgnoreCase(this.funcao);
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