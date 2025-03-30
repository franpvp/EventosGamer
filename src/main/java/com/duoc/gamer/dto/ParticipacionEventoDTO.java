package com.duoc.gamer.dto;

import com.duoc.gamer.entities.EventoEntity;
import com.duoc.gamer.entities.UsuarioEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipacionEventoDTO {

    @NotNull(message = "El campo id no puede estar vacío")
    @Positive(message = "El campo id debe ser un número positivo")
    private Long id;

    @NotNull(message = "El campo usuario no puede estar vacío")
    private UsuarioEntity usuario;

    @NotNull(message = "El campo evento no puede estar vacío")
    private EventoEntity evento;

    @NotNull(message = "El campo fechaInscripcion no puede estar vacío")
    private LocalDate fechaInscripcion;
}
