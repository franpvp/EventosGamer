package com.duoc.gamer.controller;

import com.duoc.gamer.dto.EventoDTO;
import com.duoc.gamer.security.JwtTokenUtil;
import com.duoc.gamer.service.EventoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private EventoService eventoService;

    @GetMapping("/home")
    public String home(
            @RequestParam(name = "name", required = false, defaultValue = "Gamer Website") String name,
            Model model
    ) {
        model.addAttribute("name", name);
        model.addAttribute("nuevoEvento", new EventoDTO());
        return "home";
    }

    @GetMapping("/")
    public String root(
            @RequestParam(name = "name", required = false, defaultValue = "Gamer Website") String name,
            Model model
    ) {
        model.addAttribute("name", name);
        model.addAttribute("nuevoEvento", new EventoDTO());
        return "home";
    }

    @PostMapping("/home")
    public String crearEventoUsuario(
            @Valid @ModelAttribute("nuevoEvento") EventoDTO nuevoEvento,
            BindingResult result,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletResponse response
    ) {
        if (result.hasErrors()) {
            log.error("Error en validaciones");
            return "home";
        }

        String jwt = jwtTokenUtil.generateToken(userDetails);
        Cookie cookie = new Cookie("JWT_TOKEN", jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60); // 1 día
        response.addCookie(cookie);

        eventoService.crearEvento(nuevoEvento);
        return "redirect:/home";
    }

}
