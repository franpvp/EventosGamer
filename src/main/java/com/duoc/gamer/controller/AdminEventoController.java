package com.duoc.gamer.controller;

import com.duoc.gamer.dto.EventoDTO;
import com.duoc.gamer.service.EventoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class AdminEventoController {

    private final EventoService eventoService;

    public AdminEventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // Este método carga la vista de "Participa en Eventos"
    @GetMapping("/gestion-eventos")
    public String mostrarEventos(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<EventoDTO> eventos = eventoService.getAllEventos();
        if (userDetails != null) {
            model.addAttribute("eventos", eventos);
        }
        model.addAttribute("nuevoEvento", new EventoDTO());
        return "gestion-eventos";
    }

    @PostMapping("/gestion-eventos")
    public String processEvento(@Valid @ModelAttribute("evento") EventoDTO eventoDTO, BindingResult result) {
        if (result.hasErrors()) {
            log.error("Error validaciones");
            return "gestion-eventos";
        }
        eventoService.crearEvento(eventoDTO);
        return "redirect:/gestion-eventos";
    }

}
