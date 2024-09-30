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
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}

	public void createUser(String username, String password, Set<Role> roles) {
		if (userRepository.findByUsername(username).isPresent()) {
			throw new RuntimeException("Usuário já existe!");
		}

		Set<Role> verifiedRoles = roles.stream().map(
				role -> roleRepository.findByAuthority(role.getAuthority()).orElseGet(() -> roleRepository.save(role)))
				.collect(Collectors.toSet());

		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.setRoles(verifiedRoles);

		userRepository.save(newUser);
	}

	public List<String> getUserRoles(String username) {
		return userRepository.findByUsername(username)
				.map(user -> user.getRoles().stream().map(Role::getAuthority).collect(Collectors.toList()))
				.orElse(List.of());
	}
}
