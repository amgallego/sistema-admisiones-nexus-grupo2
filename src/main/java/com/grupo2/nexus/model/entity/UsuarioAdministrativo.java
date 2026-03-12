package com.grupo2.nexus.model.entity;

import com.grupo2.nexus.model.embeddable.DatosPersonales;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.grupo2.nexus.model.enums.Activo;

@Entity
@Table(name = "usuarios_administrativos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class UsuarioAdministrativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DatosPersonales datosPersonales;

    @Enumerated(EnumType.STRING)
    private Activo activo;

    @Column(nullable = false, length = 100)
    private String apellido;

}
