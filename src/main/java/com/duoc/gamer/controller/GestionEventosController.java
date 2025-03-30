package com.duoc.gamer.controller;

import com.duoc.gamer.service.UsuarioService;
import org.springframework.stereotype.Controller;

@Controller
public class GestionEventosController {

    private final UsuarioService usuarioService;

    public GestionEventosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

//    @GetMapping("/gestion-eventos")
//    public String showGestionUsuariosPage() {
//        return "gestion-eventos";
//    }
}
