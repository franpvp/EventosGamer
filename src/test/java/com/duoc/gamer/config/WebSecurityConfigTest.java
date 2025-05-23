package com.duoc.gamer.config;

import com.duoc.gamer.security.JwtAuthenticationEntryPoint;
import com.duoc.gamer.security.JwtCookieToHeaderFilter;
import com.duoc.gamer.security.JwtRequestFilter;
import com.duoc.gamer.service.impl.CustomUserDetailsService;
import jakarta.servlet.Filter;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(controllers = WebSecurityConfigTest.DummyController.class)
@Import(WebSecurityConfig.class)
class WebSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;


    @Test
    void authenticationManagerDebeRetornarInstancia() throws Exception {
        AuthenticationManager resultado = webSecurityConfig.authenticationManager(authenticationConfiguration);
        assertNotNull(resultado);
    }

    @Test
    void authenticationProviderDebeRetornarInstancia() {
        DaoAuthenticationProvider resultado = webSecurityConfig.authenticationProvider();
        assertNotNull(resultado);
    }

    @Test
    void passwordEncoderDebeRetornarInstancia() {
        PasswordEncoder resultado = webSecurityConfig.passwordEncoder();
        assertNotNull(resultado);
    }

    @RestController
    static class DummyController {

        @GetMapping(value = "/home", produces = "text/plain")
        public String home() {
            return "home";
        }

        @GetMapping("/perfil")
        public String perfil() {
            return "perfil";
        }
    }
}