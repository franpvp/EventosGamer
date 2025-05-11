package com.duoc.gamer.controller;

import com.duoc.gamer.dto.AuthDTO;
import com.duoc.gamer.security.JwtTokenUtil;
import com.duoc.gamer.service.impl.CustomUserDetailsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private HttpServletResponse response;

    @Mock
    private Model model;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void showLoginPageTest() {

        String resultado = loginController.showLoginPage(model);

        assertNotNull(resultado);
        assertEquals("login", resultado);
    }

    @Test
    void login_usuarioNormal_redirigeHome() {
        // Arrange
        AuthDTO dto = new AuthDTO("user@test.com", "123");
        var user = new org.springframework.security.core.userdetails.User(
                dto.getEmail(),
                dto.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        when(customUserDetailsService.loadUserByUsername(dto.getEmail())).thenReturn(user);
        when(jwtTokenUtil.generateToken(user)).thenReturn("mock.jwt.token");

        // Captura la cookie
        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);

        // Act
        String vista = loginController.procesarLogin(dto, response, model);

        // Assert
        assertEquals("redirect:/home", vista);
        verify(authenticationManager).authenticate(any());
        verify(response).addCookie(cookieCaptor.capture());
        assertEquals("JWT_TOKEN", cookieCaptor.getValue().getName());
        assertEquals("mock.jwt.token", cookieCaptor.getValue().getValue());
    }

    @Test
    void login_admin_redirigeAdminHome() {
        // Arrange
        AuthDTO dto = new AuthDTO("admin@test.com", "admin");
        var admin = new org.springframework.security.core.userdetails.User(
                dto.getEmail(),
                dto.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );

        when(customUserDetailsService.loadUserByUsername(dto.getEmail())).thenReturn(admin);
        when(jwtTokenUtil.generateToken(admin)).thenReturn("admin.jwt");

        // Act
        String resultado = loginController.procesarLogin(dto, response, model);

        // Assert
        assertEquals("redirect:/admin-home", resultado);
        verify(response).addCookie(any());
    }

    @Test
    void login_credencialesInvalidas_devuelveLoginConError() {
        // Arrange
        AuthDTO dto = new AuthDTO("bad@test.com", "wrong");

        doThrow(new BadCredentialsException("Credenciales inválidas"))
                .when(authenticationManager)
                .authenticate(any());

        // Act
        String resultado = loginController.procesarLogin(dto, response, model);

        // Assert
        assertEquals("login", resultado);
        verify(model).addAttribute("mensajeError", "Correo o contraseña incorrectos");
    }

    @Test
    public void cerrarSesionTest() {

        String resultado = loginController.cerrarSesion(response);

        assertNotNull(resultado);
        assertEquals("redirect:/login?logout", resultado);
    }
}