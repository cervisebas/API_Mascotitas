package com.mascotitas.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

@Entity
@Table (name = "adoptantes")

public class Adoptante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nombre;
    private String apellido;
    private int dni;
    private int telefono;
    private String email;
    private String direccion;
    private String ocupacion;

}