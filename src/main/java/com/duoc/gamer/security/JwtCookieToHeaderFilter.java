package com.duoc.gamer.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

public class JwtCookieToHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        // Verifica si el header Authorization ya existe
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.isEmpty()) {
            // Si no existe, intenta obtener el token desde la cookie
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("JWT_TOKEN".equals(cookie.getName())) {
                        final String token = cookie.getValue();
                        // Envuelve la request para añadir el header Authorization
                        request = new HttpServletRequestWrapper(request) {
                            @Override
                            public String getHeader(String name) {
                                if ("Authorization".equalsIgnoreCase(name)) {
                                    return "Bearer " + token;
                                }
                                return super.getHeader(name);
                            }

                            @Override
                            public Enumeration<String> getHeaders(String name) {
                                if ("Authorization".equalsIgnoreCase(name)) {
                                    return Collections.enumeration(Collections.singletonList("Bearer " + token));
                                }
                                return super.getHeaders(name);
                            }
                        };
                        break; // Salimos una vez encontrado y añadido el token
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
