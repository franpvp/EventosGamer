package com.duoc.gamer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeAdminController {

    @GetMapping("/admin-home")
    public String showHomeAdminPage() {
        return "admin-home";
    }

}
