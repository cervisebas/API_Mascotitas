package com.mascotitas.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter

public class SolicitudAdopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @ManyToOne
    @JoinColumn(name = "id_adoptante")
    private Adoptante adoptante;

   @ManyToOne
   @JoinColumn(name = "id_mascota")
    private Mascota mascota;

    private Date fecha;
}
