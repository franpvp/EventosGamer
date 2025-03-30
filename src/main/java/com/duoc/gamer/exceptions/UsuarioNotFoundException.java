package com.duoc.gamer.exceptions;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String mensaje){
        super(mensaje);
    }
}
