package com.sistema.escolar.service;

import com.sistema.escolar.model.Role;
import com.sistema.escolar.model.User;
import com.sistema.escolar.repository.RoleRepository;
import com.sistema.escolar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		return user;
	}

	public void createUser(String username, String password, Set<Role> roles) {
		Set<Role> verifiedRoles = new HashSet<>();
		for (Role role : roles) {
			Optional<Role> existingRole = roleRepository.findByAuthority(role.getAuthority());
			if (existingRole.isEmpty()) {
				roleRepository.save(role); // Salva o novo papel se não existir
				verifiedRoles.add(role); // Adiciona o novo papel
			} else {
				verifiedRoles.add(existingRole.get()); // Adiciona o papel existente
			}
		}

		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(passwordEncoder.encode(password)); // Codifica a senha
		newUser.setRoles(verifiedRoles); // Usa os papéis verificados

		userRepository.save(newUser); // Salva o usuário no banco
	}

	// Novo método para obter as roles de um usuário
	public List<String> getUserRoles(String username) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return user.getRoles().stream().map(Role::getAuthority).collect(Collectors.toList());
		}
		return List.of(); // Retorna uma lista vazia se o usuário não for encontrado
	}
}
