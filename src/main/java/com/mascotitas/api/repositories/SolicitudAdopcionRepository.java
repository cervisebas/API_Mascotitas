package com.mascotitas.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotitas.api.models.SolicitudAdopcion;

public interface SolicitudAdopcionRepository extends JpaRepository<SolicitudAdopcion, Long> {
    //Optional<SolicitudAdopcion> findByAnimalId(Long animalId);
}
