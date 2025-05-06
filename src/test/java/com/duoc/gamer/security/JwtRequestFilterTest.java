package com.duoc.gamer.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtRequestFilterTest {

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void testDoFilterInternal() throws ServletException, IOException {

        String jwt = "token";
        String username = "testUser";

        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(jwtTokenUtil.extractUsername(jwt)).thenReturn(username);

        UserDetails userDetails = new User(username, "password", Collections.emptyList());

        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtTokenUtil.validateToken(jwt, userDetails)).thenReturn(true);

        jwtRequestFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(username, SecurityContextHolder.getContext().getAuthentication().getName());

        verify(filterChain).doFilter(httpServletRequest, httpServletResponse);

    }

    @Test
    void testAuthorizationHeaderIsInvalid() throws ServletException, IOException {
        when(httpServletRequest.getHeader("Authorization")).thenReturn("InvalidHeader");

        jwtRequestFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(httpServletRequest, httpServletResponse);

    }

    @Test
    void testAuthenticationUsernameFails() throws ServletException, IOException {
        String jwt = "invalid.token";

        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(jwtTokenUtil.extractUsername(jwt)).thenThrow(new RuntimeException("JWT parsing error"));

        jwtRequestFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(httpServletRequest, httpServletResponse);
    }

    @Test
    void testSkipAuthenticationIfAlreadyAuthenticated() throws ServletException, IOException {
        String jwt = "valid.token";
        String username = "alreadyAuthenticatedUser";

        UsernamePasswordAuthenticationToken existingAuth =
                new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(existingAuth);

        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer " + jwt);

        jwtRequestFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        // No se llama a loadUserByUsername ni a validateToken
        verify(userDetailsService, never()).loadUserByUsername(any());
        verify(jwtTokenUtil, never()).validateToken(any(), any());

        verify(filterChain).doFilter(httpServletRequest, httpServletResponse);
    }
}