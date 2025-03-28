package com.duoc.gamer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "participacion_eventos")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipacionEventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_evento", nullable = false)
    private EventoEntity evento;

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDate fechaInscripcion;
}
