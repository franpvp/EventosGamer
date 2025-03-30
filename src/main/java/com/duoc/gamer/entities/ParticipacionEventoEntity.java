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
@Table(name = "participacion_eventos")
public class ParticipacionEventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participacion")
    private Long idParticipacionEvento;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "id_evento", nullable = false)
    private Long idEvento;

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDate fechaInscripcion;
}
