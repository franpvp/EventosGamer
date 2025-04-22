package com.duoc.gamer.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtCookieToHeaderFilterTest {

    private static final String AUTHORIZATION = "Authorization";

    private Cookie[] cookie;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private JwtCookieToHeaderFilter jwtCookieToHeaderFilter;

    @BeforeEach
    void setUp() {

    }

    @Test
    void doFilterInternal() throws ServletException, IOException {

        when(request.getHeader(AUTHORIZATION)).thenReturn("");
        when(request.getCookies()).thenReturn(cookie);

        jwtCookieToHeaderFilter.doFilterInternal(request, httpServletResponse, filterChain);

    }
}