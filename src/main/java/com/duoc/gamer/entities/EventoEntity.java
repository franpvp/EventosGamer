package com.duoc.gamer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eventos")
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;

    @Column(name = "titulo", nullable = false, length = 30)
    private String titulo;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "organizadores", nullable = false, length = 50)
    private String organizadores;

    @Column(name = "servicios", nullable = false, length = 100)
    private String servicios;

    @Column(name = "expositores", nullable = false, length = 50)
    private String expositores;

    @Column(name = "premios", nullable = false, length = 50)
    private String premios;
}
