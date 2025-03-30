package com.duoc.gamer.controller;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PerfilController {

    private final UsuarioService usuarioService;

    public PerfilController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/perfil")
    public String showPerfilPage(Model model, Principal principal) {
        // Obtener nombre de usuario
        String username = principal.getName();
        UsuarioDTO usuario = usuarioService.findUsuarioByUsername(username);
        model.addAttribute("usuario", usuario);
        return "perfil";
    }
}
