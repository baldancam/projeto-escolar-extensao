package com.sistema.escolar.model;

public enum UserRole {
	ADMIN("ROLE_ADMIN"), PROFESSOR("ROLE_PROFESSOR"), PAI("ROLE_PAI");

	private String role;

	private UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}
