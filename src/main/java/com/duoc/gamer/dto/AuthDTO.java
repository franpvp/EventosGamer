package com.duoc.gamer.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {

    @NotNull(message = "El campo email no puede estar vacío")
    private String email;

    @NotNull(message = "El campo password no puede estar vacío")
    @Size(min = 6, max = 20, message = "El campo password debe tener entre 6 y 20 caracteres")
    private String password;

}
