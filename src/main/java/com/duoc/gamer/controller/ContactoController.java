package com.duoc.gamer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Mensaje: " + mensaje);
        return "redirect:/contacto?success";
    }
}
