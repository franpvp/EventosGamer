package com.duoc.gamer.controller;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.mapper.UsuarioMapper;
import com.duoc.gamer.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//@RequestMapping("/admin/usuarios")
@RequiredArgsConstructor
public class AdminUsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioMapper usuarioMapper;

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
    @PostMapping
    public String agregarUsuario(@ModelAttribute("nuevoUsuario") UsuarioDTO nuevoUsuario) {
        // Encriptar la contraseña recibida del formulario
        String encryptedPassword = passwordEncoder.encode(nuevoUsuario.getPassword());
        nuevoUsuario.setPassword(encryptedPassword);
        // Guardar el nuevo usuario en la base de datos usando el service
        usuarioService.createUsuario(nuevoUsuario);
        return "redirect:/gestion-usuarios"; // Redirige al listado actualizado
    }
}