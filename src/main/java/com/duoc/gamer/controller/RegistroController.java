package com.duoc.gamer.controller;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String showRegistroPage(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "registro";
    }
    @PostMapping("/registro")
    public String processRegistro(@Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "registro";
        }
        usuarioService.createUsuario(usuarioDTO);
        return "redirect:/login";
    }


}
