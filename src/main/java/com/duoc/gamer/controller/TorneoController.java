package com.duoc.gamer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TorneoController {

    @GetMapping("/torneo")
    public String showTorneoPage() {
        return "torneo";
    }
}
