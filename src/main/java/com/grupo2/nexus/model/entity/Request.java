package com.grupo2.nexus.model.entity;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Cursos")
@ToString(exclude = "Cursos")

public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificación
    @Column(nullable = false, length = 10)
    private String tipoDoc;

    @Column(nullable = false, unique = true, length = 20)
    private String numeroDoc;

    private LocalDate fechaExpedicion;

    // Ubicación de Expedición
    private String paisExpedicion;
    private String departamentoExpedicion;
    private String ciudadExpedicion;

    // Datos Personales
    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    private String genero;
    private String estadoCivil;

    // Nacimiento
    private String paisNacimiento;
    private String departamentoNacimiento;
    private LocalDate fechaNacimiento;
    private String ciudadNacimiento;

    // Contacto
    private String indicativoPais;
    private String numeroCelular;

    // Académico
    private String primerPrograma;
    private String segundoPrograma;
}