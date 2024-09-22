// Caminho sugerido: com/sistema/escolar/request/LoginRequest.java
package com.sistema.escolar.request;

public class LoginRequest {
	private String username;
	private String password;

	// Construtor vazio
	public LoginRequest() {
	}

	// Construtor com parâmetros
	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// Getters e Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
