package com.mascotitas.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotitas.api.models.Adoptante;

public interface AdoptanteRepository extends JpaRepository<Adoptante, Long> {

}
