package com.sistema.escolar.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sistema.escolar.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	TokenService tokenService;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var token = this.recoverToken(request);

		if (token != null) {

			var email = tokenService.validateToken(token); // talvez preciso trocar por login, verificar como foi
															// escrito o finByEmail

			UserDetails usuario = usuarioRepository.findByEmail(email); // talvez preciso trocar por login, verificar
																		// como foi escrito o finByEmail

			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		filterChain.doFilter(request, response);

	}

	private String recoverToken(HttpServletRequest request) {

		var authHeader = request.getHeader("Authorization");

		if (authHeader == null)
			return null;

		return authHeader.replace("Bearer ", "");

	}

}
