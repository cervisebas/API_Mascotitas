package com.mascotitas.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotitas.api.models.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    
}
