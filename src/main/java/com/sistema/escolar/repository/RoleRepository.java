package com.sistema.escolar.repository;

import com.sistema.escolar.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByAuthority(String authority);
}
