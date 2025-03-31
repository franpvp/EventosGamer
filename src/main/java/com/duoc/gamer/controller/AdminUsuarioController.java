package com.duoc.gamer.controller;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.mapper.UsuarioMapper;
import com.duoc.gamer.security.JwtTokenUtil;
import com.duoc.gamer.service.UsuarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminUsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AdminUsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    // Muestra el listado de usuarios y el formulario para agregar un nuevo usuario
    @GetMapping("/gestion-usuarios")
    public String listarUsuarios(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();
        if (userDetails != null) {
            model.addAttribute("usuarios", usuarios);
        }
        // Nuevo objeto para el formulario de creación
        model.addAttribute("nuevoUsuario", new UsuarioDTO());
        return "gestion-usuarios";
    }

    // Procesa el formulario para agregar un nuevo usuario
    @PostMapping("/gestion-usuarios")
    public String agregarUsuario(@Valid @ModelAttribute("nuevoUsuario") UsuarioDTO nuevoUsuario,
                                 @AuthenticationPrincipal UserDetails userDetails,
                                 BindingResult result, Model model, HttpServletResponse response) {
        if (result.hasErrors()) {
            log.error("Error validaciones");
            return "gestion-usuarios";
        }

        // Generar token JWT utilizando el userDetails del usuario autenticado (por ejemplo, el admin)
        String jwt = jwtTokenUtil.generateToken(userDetails);

        // Crear la cookie HttpOnly con el token
        Cookie cookie = new Cookie("JWT_TOKEN", jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60); // Expiración: 1 día
        response.addCookie(cookie);

        // Encriptar la contraseña recibida del formulario
        String encryptedPassword = passwordEncoder.encode(nuevoUsuario.getPassword());
        nuevoUsuario.setPassword(encryptedPassword);

        // Guardar el nuevo usuario en la base de datos usando el service
        usuarioService.createUsuario(nuevoUsuario);

        return "redirect:/gestion-usuarios"; // Redirige al listado actualizado
    }

}