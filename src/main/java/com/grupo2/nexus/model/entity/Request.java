package com.grupo2.nexus.model.entity;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "requests")
@ToString

public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificación
    @Column(name = "tipo_doc", nullable = false, length = 10)
    private String tipoDoc;

    @Column(name = "numero_doc", nullable = false, unique = true, length = 20)
    private String numeroDoc;

    @Column(name = "fecha_expedicion")
    private LocalDate fechaExpedicion;

    // Ubicación de Expedición
    @Column(name = "pais_expedicion")
    private String paisExpedicion;

    @Column(name = "departamento_expedicion")
    private String departamentoExpedicion;

    @Column(name = "ciudad_expedicion")
    private String ciudadExpedicion;

    // Datos Personales
    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    private String genero;

    @Column(name = "estado_civil")
    private String estadoCivil;

    // Nacimiento
    @Column(name = "pais_nacimiento")
    private String paisNacimiento;

    @Column(name = "departamento_nacimiento")
    private String departamentoNacimiento;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "ciudad_nacimiento")
    private String ciudadNacimiento;

    // Contacto
    @Column(name = "indicativo_pais")
    private String indicativoPais;

    @Column(name = "numero_celular")
    private String numeroCelular;

    // Académico
    @Column(name = "primer_programa")
    private String primerPrograma;

    @Column(name = "segundo_programa")
    private String segundoPrograma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @ToString.Exclude
    private Usuario usuario;
}