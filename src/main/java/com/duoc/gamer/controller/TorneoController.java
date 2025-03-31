package com.duoc.gamer.controller;

import com.duoc.gamer.dto.EventoDTO;
import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.service.EventoService;
import com.duoc.gamer.service.ParticipacionEventoService;
import com.duoc.gamer.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
public class TorneoController {

    private final ParticipacionEventoService participacionEventoService;
    private final UsuarioService usuarioService;
    private final EventoService eventoService;

    public TorneoController(ParticipacionEventoService participacionEventoService, UsuarioService usuarioService, EventoService eventoService) {
        this.participacionEventoService = participacionEventoService;
        this.usuarioService = usuarioService;
        this.eventoService = eventoService;
    }

    @GetMapping("/torneo")
    public String showTorneoPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        // Obtener todos los eventos desde la base de datos
        List<EventoDTO> eventos = eventoService.getAllEventos();
        log.info("Cantidad de eventos: " + eventos.size());
        model.addAttribute("eventos", eventos);
        return "torneo";
    }


    @PostMapping("/torneo/{eventoId}")
    public String participarEvento(@PathVariable("eventoId") Long eventoId,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   RedirectAttributes redirectAttributes) {
        // Obtener el username del usuario autenticado
        String username = userDetails.getUsername();
        // Recuperar el objeto UsuarioDTO a partir del username
        UsuarioDTO usuario = usuarioService.findByEmail(username);
        // Registrar la participación utilizando el id del usuario, el id del evento y la fecha actual
        participacionEventoService.registrarParticipacion(usuario.getId(), eventoId, LocalDate.now());

        // Agregar un atributo flash para mostrar el mensaje en la vista
        redirectAttributes.addFlashAttribute("mensaje", "¡Te has registrado para participar en el evento!");
        return "redirect:/torneo"; // Redirige a /torneo
    }


}
