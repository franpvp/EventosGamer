package com.duoc.gamer.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

public class JwtCookieToHeaderFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        // Verifica si el header Authorization ya existe
        String authHeader = request.getHeader(AUTHORIZATION);
        if ((authHeader == null || authHeader.isEmpty()) && request.getCookies() != null) {
            request = getHttpServletRequest(request);
        }
        filterChain.doFilter(request, response);
    }

    private static HttpServletRequest getHttpServletRequest(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if ("JWT_TOKEN".equals(cookie.getName())) {
                final String token = cookie.getValue();
                request = getHttpServletRequest(request, token);
                break;
            }
        }
        return request;
    }

    private static HttpServletRequest getHttpServletRequest(HttpServletRequest request, String token) {
        request = new HttpServletRequestWrapper(request) {
            @Override
            public String getHeader(String name) {
                if (AUTHORIZATION.equalsIgnoreCase(name)) {
                    return "Bearer " + token;
                }
                return super.getHeader(name);
            }

            @Override
            public Enumeration<String> getHeaders(String name) {
                if (AUTHORIZATION.equalsIgnoreCase(name)) {
                    return Collections.enumeration(Collections.singletonList("Bearer " + token));
                }
                return super.getHeaders(name);
            }
        };
        return request;
    }
}
