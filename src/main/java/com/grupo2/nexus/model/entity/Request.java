package com.grupo2.nexus.model.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.grupo2.nexus.model.enums.EstadoSolicitud;
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
    private String tipodoc;

    @Column(name = "numero_doc", nullable = false, unique = true, length = 20)
    private String numeroDoc;

    // Datos Personales
    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "numero_celular")
    private String numeroCelular;

    // Académico
    @Column(name = "primer_programa")
    private String primerPrograma;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estadoSolicitud;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @ToString.Exclude
    private Usuario usuario;
}