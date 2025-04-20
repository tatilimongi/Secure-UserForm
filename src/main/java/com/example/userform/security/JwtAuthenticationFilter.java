package com.example.userform.security;

import com.example.userform.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import org.slf4j.Logger;

@SuppressWarnings("unused")
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private static final Logger jwtLogger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	private static final String APPLICATION_JSON = "application/json";

	public JwtAuthenticationFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain)
			throws ServletException, IOException {

		String token = getTokenFromRequest(request);

		if (token != null) {
			try {
				if (jwtUtil.isTokenValid(token)) {
					if (jwtUtil.isTokenExpired(token)) {
						String email = jwtUtil.extractEmail(token);
						jwtLogger.warn("Token expired for user: {}", email);
						response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						response.setContentType(APPLICATION_JSON);
						response.getWriter().write("{\"message\": \"Token expired\"}");
						return;
					}
					String role = jwtUtil.extractRole(token);
					request.setAttribute("role", role);
					Authentication authentication = jwtUtil.getAuthentication(token);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					String email = jwtUtil.extractEmail(token);
					jwtLogger.info("Successful authentication for user: {}", email);
				} else {
					String email = jwtUtil.extractEmail(token);
					jwtLogger.warn("Invalid token attempt for user: {}", email);
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.setContentType(APPLICATION_JSON);
					response.getWriter().write("{\"message\": \"Invalid token\"}");
					return;
				}
			} catch (Exception e) {
				jwtLogger.error("Error processing token for user: {}", token, e);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType(APPLICATION_JSON);
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
