package com.mascotitas.api.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

@Entity
@Table (name = "mascotas")

public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_animal")
    private Animal tipo;

    private String nombre;
    private Date nacimiento;
    private String descripcion;

}
