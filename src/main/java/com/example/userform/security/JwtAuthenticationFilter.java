package com.example.userform.security;

import com.example.userform.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import org.slf4j.Logger;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	public JwtAuthenticationFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = getTokenFromRequest(request);

		if (token != null) {
			try {
				if (jwtUtil.isTokenValid(token)) {
					if (jwtUtil.isTokenExpired(token)) {
						String email = jwtUtil.extractEmail(token);
						logger.warn("Token expired for user: {}", email);
						response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						response.setContentType("application/json");
						response.getWriter().write("{\"message\": \"Token expired\"}");
						return;
					}

					String role = jwtUtil.extractRole(token);
					request.setAttribute("role", role);

					Authentication authentication = jwtUtil.getAuthentication(token);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					String email = jwtUtil.extractEmail(token);
					logger.info("Successful authentication for user: {}", email);
				} else {
					String email = jwtUtil.extractEmail(token);
					logger.warn("Invalid token attempt for user: {}", email);
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.setContentType("application/json");
					response.getWriter().write("{\"message\": \"Invalid token\"}");
					return;
				}
			} catch (Exception e) {
				logger.error("Error processing token for user: {}", token, e);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType("application/json");
				response.getWriter().write("{\"message\": \"Invalid token\", \"error\": \"" + e.getMessage() + "\"}");
				return;
			}
		}

		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header != null && header.startsWith("Bearer ")) {
			return header.substring(7);
		}
		return null;
	}
}
