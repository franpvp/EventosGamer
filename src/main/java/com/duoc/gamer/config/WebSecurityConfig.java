package com.duoc.gamer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/login", "/registro", "/eventos", "/contacto").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Asegura archivos estáticos
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS)); // Para aplicaciones sin sesiones

        return http.build();
    }

    @Bean
    @Description("In memory Userdetails service registered since DB doesn't have user table")
    public UserDetailsService userDetailsService() {
        var user1 = User.builder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        var user2 = User.builder()
                .username("user")
                .password("user123")
                .roles("USER")
                .build();

        var user3 = User.builder()
                .username("viewer")
                .password("viewer123")
                .roles("VIEWER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}

