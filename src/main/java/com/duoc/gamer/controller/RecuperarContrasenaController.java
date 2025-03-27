package com.duoc.gamer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecuperarContrasenaController {

    @GetMapping("/recuperar-contrasena")
    public String showRecuperarContrasenaPage() {
        return "recuperar-contrasena";
    }
}
