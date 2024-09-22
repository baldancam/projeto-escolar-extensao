package com.sistema.escolar.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long expirationTime;

	// Método para gerar o token
	public String generateToken(String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expirationTime);

		return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	// Método para obter o username do token
	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	// Método para validar o token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
