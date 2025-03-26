package com.duoc.gamer.rest;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioRestController {

    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {

        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

}
