package com.duoc.gamer.exceptions;

public class UsuarioBadRequestException extends RuntimeException{
    public UsuarioBadRequestException(String mensaje) {
        super(mensaje);
    }
}
