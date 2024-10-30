package com.mascotitas.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotitas.api.models.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    
}
