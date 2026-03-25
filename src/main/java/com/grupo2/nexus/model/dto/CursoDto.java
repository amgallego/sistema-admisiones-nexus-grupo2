package com.grupo2.nexus.model.dto;
import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private int duracionHoras;
    private Integer cuposMaximos;
    private Integer cuposDisponibles;
    private BigDecimal precio;
    private String instructor;

    // Campo extra para que el Front no tenga que hacer lógica
    private String mensajeDisponibilidad;

}
