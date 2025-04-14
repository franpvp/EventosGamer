package com.duoc.gamer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ContactoController {

    @GetMapping("/contacto")
    public String mostrarFormulario() {
        return "contacto";
    }

    @PostMapping("/contacto")
    public String procesarFormulario(@RequestParam String nombre,
                                     @RequestParam String email,
                                     @RequestParam String mensaje) {
        log.info("Nombre: " + nombre);
        log.info("Email: " + email);
        log.info("Mensaje: " + mensaje);
        return "redirect:/contacto?success";
    }
}
