package com.sistema.escolar.dto;

import com.sistema.escolar.model.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {

}
