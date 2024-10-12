package com.sistema.escolar.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sistema.escolar.model.Usuario;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String generateToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("auth-api").withSubject(usuario.getEmail())
					.withExpiresAt(genExpirationDate()).sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error while generating token", exception);
		}
	}

	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm).withIssuer("auth-api").build().verify(token).getSubject(); // Agora retornando
																										// o assunto,
																										// que é o email
		} catch (JWTVerificationException exception) {
			return ""; // Ou lançar uma exceção personalizada
		}
	}

	private Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
