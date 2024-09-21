package com.sistema.escolar.service;

import com.sistema.escolar.model.Role;
import com.sistema.escolar.model.User;
import com.sistema.escolar.repository.RoleRepository;
import com.sistema.escolar.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder; // Injetando o PasswordEncoder

	// Método para buscar usuário pelo nome
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	// Método para criar um novo usuário
	public void createUser(String username, String password, String roleName) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));

		// Adiciona a role ao usuário
		Role role = roleRepository.findByName(roleName);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);

		userRepository.save(user);
		System.out.println("Usuário " + username + " criado com sucesso!");
	}

}
