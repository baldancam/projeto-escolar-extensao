package com.sistema.escolar.service;

import com.sistema.escolar.model.Role;
import com.sistema.escolar.model.User;
import com.sistema.escolar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		return user;
	}

	public void createUser(String username, String password, Set<Role> roles) {
		// Adicionar logs para depuração
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		System.out.println("Roles: " + roles);

		// Criação de um novo usuário com codificação de senha
		User newUser = new User();
		newUser.setUsername(username); // Define o username
		newUser.setPassword(passwordEncoder.encode(password)); // Codifica a senha
		newUser.setRoles(roles); // Define as roles

		// Salva o novo usuário no banco de dados
		userRepository.save(newUser);
	}
}
