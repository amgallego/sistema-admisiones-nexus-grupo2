package com.grupo2.nexus.model.embeddable;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Embeddable


public class DatosPersonales {
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String contrasena;


    private String telefono;


}
