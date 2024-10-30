package com.mascotitas.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

@Entity
@Table (name = "solicitud_adopcion")

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
