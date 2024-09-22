package com.sistema.escolar.service;

import com.sistema.escolar.model.Role;
import com.sistema.escolar.model.User;
import com.sistema.escolar.repository.RoleRepository;
import com.sistema.escolar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Método para carregar o usuário por nome para autenticação via JWT
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}

		// Converte as roles para GrantedAuthority
		Set<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

		// Retorna o objeto UserDetails com username, senha e roles
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	// Método para criar novos usuários
	public void createUser(String username, String password, String roleName) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password)); // Criptografa a senha

		// Busca a role no banco de dados
		Role role = roleRepository.findByName(roleName);
		if (role == null) {
			throw new IllegalArgumentException("Role não encontrada: " + roleName);
		}

		// Adiciona a role ao usuário
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);

		userRepository.save(user); // Salva o usuário no banco de dados
	}
}
