package com.sistema.escolar.repository;

import com.sistema.escolar.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByAuthority(String authority); // Alterado de 'name' para 'authority'
}
