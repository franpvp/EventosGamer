package com.duoc.gamer.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@AllArgsConstructor
public class EventoDTO {

    @NotNull(message = "El campo idEvento no puede estar vacío")
    private Long idEvento;

    @NotNull(message = "El campo titulo no puede estar vacío")
    private String titulo;

    @NotNull(message = "El campo descripcion no puede estar vacío")
    private String descripcion;

    @NotNull(message = "El campo fechaInicioEvento no puede estar vacío")
    private LocalDate fechaInicioEvento;

    @NotNull(message = "El campo fechaFinEvento no puede estar vacío")
    private LocalDate fechaFinEvento;

    @NotNull(message = "El campo creacionEvento no puede estar vacío")
    private String creacionEvento;
}
