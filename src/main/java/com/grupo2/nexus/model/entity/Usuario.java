package com.grupo2.nexus.model.entity;

import jakarta.persistence.*; // Importación limpia de jakarta
import lombok.*;
import java.util.List;

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

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String contrasena;

    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Curso> cursos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // Crucial para evitar bucles infinitos con el ToString de Request
    private List<Request> solicitudes;

}