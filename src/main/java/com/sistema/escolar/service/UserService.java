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
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(passwordEncoder.encode(password)); // Codifica a senha
		newUser.setRoles(roles); // Define as roles

		userRepository.save(newUser); // Salva o usuário no banco
	}
}
