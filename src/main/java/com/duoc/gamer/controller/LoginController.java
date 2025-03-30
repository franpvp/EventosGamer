package com.duoc.gamer.controller;

import com.duoc.gamer.dto.AuthDTO;
import com.duoc.gamer.security.JwtTokenUtil;
import com.duoc.gamer.service.impl.CustomUserDetailsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // Muestra la vista de login
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("auth", new AuthDTO());
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute("auth") AuthDTO authDTO,
                                HttpServletResponse response,
                                Model model) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
        // Generar token JWT
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authDTO.getEmail());
        String jwt = jwtTokenUtil.generateToken(userDetails);

        // Crear la cookie HttpOnly con el token
        Cookie cookie = new Cookie("JWT_TOKEN", jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);// Expiración: 1 día
        response.addCookie(cookie);

        // Verificar si el usuario tiene rol ADMIN para redirigirlo al template admin-home.html
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            return "redirect:/admin-home";
        } else {
            return "redirect:/home";
        }
    }


    @GetMapping("/logout")
    public String cerrarSesion(HttpServletResponse response) {
        Cookie cookie = new Cookie("JWT_TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // La cookie se elimina de inmediato
        response.addCookie(cookie);
        return "redirect:/login?logout"; // Redirige al login, opcionalmente con un mensaje
    }
}