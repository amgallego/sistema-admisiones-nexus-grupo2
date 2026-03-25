package com.grupo2.nexus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestDto {
    private Long id;

    // Datos del aspirante simplificados
    private String nombreCompleto;
    private String numeroDoc;
    private String numeroCelular;

    // Información del curso
    private String programaInteres;

    // Información del proceso
    private String estado;
    private LocalDateTime fechaCreacion;
}
