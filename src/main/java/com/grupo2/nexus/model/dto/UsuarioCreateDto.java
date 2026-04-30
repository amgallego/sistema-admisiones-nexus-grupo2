package com.grupo2.nexus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDto {

    private String username;

    private String password; 

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;


}