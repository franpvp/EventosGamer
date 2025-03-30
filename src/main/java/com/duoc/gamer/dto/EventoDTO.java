package com.duoc.gamer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {

    @NotNull(message = "El campo idEvento no puede estar vacío")
    private Long idEvento;

    @NotNull(message = "El campo titulo no puede estar vacío")
    private String titulo;

    @NotNull(message = "El campo descripcion no puede estar vacío")
    private String descripcion;

    @NotNull(message = "El campo fechaInicio no puede estar vacío")
    private LocalDate fechaInicio;

    @NotNull(message = "El campo organizadores no puede estar vacío")
    private String organizadores;

    @NotNull(message = "El campo servicios no puede estar vacío")
    private String servicios;

    @NotNull(message = "El campo expositores no puede estar vacío")
    private String expositores;

    @NotNull(message = "El campo premios no puede estar vacío")
    private String premios;
}
