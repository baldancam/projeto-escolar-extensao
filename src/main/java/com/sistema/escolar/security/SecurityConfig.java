package com.sistema.escolar.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // Desabilita a proteção CSRF
				.authorizeHttpRequests().requestMatchers("/api/users/register").permitAll() // Permitir acesso sem
																							// autenticação para esta
																							// rota
				.anyRequest().authenticated();

		return http.build();
	}
}
