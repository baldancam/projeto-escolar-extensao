package com.sistema.escolar.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sistema.escolar.model.Role;
import com.sistema.escolar.repository.RoleRepository;
import com.sistema.escolar.service.UserService;

@Component
public class DataLoader implements CommandLineRunner {

	private final UserService userService;
	private final RoleRepository roleRepository;

	public DataLoader(UserService userService, RoleRepository roleRepository) {
		this.userService = userService;
		this.roleRepository = roleRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("DataLoader executado");

		// Cria ROLE_ADMIN se não existir
		Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
		if (roleAdmin == null) {
			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			roleRepository.save(adminRole);
			System.out.println("Role ROLE_ADMIN criada!");
		} else {
			System.out.println("Role ROLE_ADMIN já existe");
		}

		// Verifica se o usuário admin já existe
		if (userService.findByUsername("admin") == null) {
			System.out.println("Criando usuário admin...");
			userService.createUser("admin", "1234", "ROLE_ADMIN");
			System.out.println("Usuário admin criado!");
		} else {
			System.out.println("Usuário admin já existe");
		}
	}

}
