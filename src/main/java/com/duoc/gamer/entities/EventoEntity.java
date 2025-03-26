package com.duoc.gamer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_inicio_evento")
    private LocalDate fechaInicioEvento;

    @Column(name = "fecha_fin_evento")
    private LocalDate fechaFinEvento;

    @Column(name = "fechaCreacionEvento")
    private String creacionEvento;
}
