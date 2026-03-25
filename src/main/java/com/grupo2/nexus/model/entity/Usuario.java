package com.grupo2.nexus.model.entity;

import java.time.LocalDateTime;
import java.util.List; // Importación limpia de jakarta

import com.grupo2.nexus.model.embeddable.DatosPersonales;
import com.grupo2.nexus.model.enums.RolUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.grupo2.nexus.model.enums.Activo;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Embedded
    private DatosPersonales datosPersonales;

    @Enumerated(EnumType.STRING)
    private Activo activo;

    @Enumerated(EnumType.STRING)
    private RolUsuario rolUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Curso> cursos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // Crucial para evitar bucles infinitos con el ToString de Request
    private List<Request> solicitudes;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

}