package com.duoc.gamer.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtCookieToHeaderFilterTest {

    private static final String AUTHORIZATION = "Authorization";

    private JwtCookieToHeaderFilter filter;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        filter = new JwtCookieToHeaderFilter();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        filterChain = Mockito.mock(FilterChain.class);

    }

    @Test
    void testDoFilterInternal_WithAuthorizationHeader() throws ServletException, IOException {
        when(request.getHeader(AUTHORIZATION)).thenReturn("Bearer existingToken");

        filter.doFilterInternal(request, response, filterChain);

        // Verifica que el filtro no modifica el header si ya existe
        verify(request, never()).getCookies();
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_WithoutAuthorizationHeader_WithCookie() throws ServletException, IOException {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        FilterChain mockFilterChain = mock(FilterChain.class);

        Cookie jwtCookie = new Cookie("JWT_TOKEN", "mockedToken");
        mockRequest.setCookies(jwtCookie);

        filter.doFilterInternal(mockRequest, mockResponse, mockFilterChain);

        // Captura la solicitud que se envía al siguiente filtro
        ArgumentCaptor<HttpServletRequest> requestCaptor = ArgumentCaptor.forClass(HttpServletRequest.class);
        verify(mockFilterChain).doFilter(requestCaptor.capture(), eq(mockResponse));

        // Verifica que se haya agregado el header correctamente
        HttpServletRequest capturedRequest = requestCaptor.getValue();
        assertEquals("Bearer mockedToken", capturedRequest.getHeader(AUTHORIZATION));
    }

}