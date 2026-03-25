package com.grupo2.nexus.model.entity;

import java.math.BigDecimal; // IMPORTANTE: Para el manejo de dinero
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import com.grupo2.nexus.model.enums.Activo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cursos")
@ToString
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private String categoria;

    @Column(name = "duracion_horas")
    private int duracionHoras;

    // --- NUEVOS CAMPOS DE CONTROL ---

    @Column(name = "cupos_maximos")
    private Integer cuposMaximos;

    @Column(name = "cupos_disponibles")
    private Integer cuposDisponibles;

    @Column(precision = 10, scale = 2) // Soporta hasta 99,999,999.99
    private BigDecimal precio;

    // --------------------------------

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "instructor_nombre", nullable = false)
    private String instructor;

    @Enumerated(EnumType.STRING)
    private Activo activo;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @ToString.Exclude
    private Usuario usuario;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        // Lógica automática: al crear el curso, los disponibles son iguales al máximo
        if (this.cuposDisponibles == null) {
            this.cuposDisponibles = this.cuposMaximos;
        }
    }
}