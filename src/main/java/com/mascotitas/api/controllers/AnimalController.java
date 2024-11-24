package com.mascotitas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mascotitas.api.models.Animal;
import com.mascotitas.api.repositories.AnimalRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {
    
    @Autowired
    private AnimalRepository animalRepository;

    @CrossOrigin
    @GetMapping
    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getById(@PathVariable Long id) {
        Optional<Animal> data = animalRepository.findById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Animal> create(@RequestBody Animal adoptante){
        Animal save = animalRepository.save(adoptante);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!animalRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        animalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Animal> update(@PathVariable Long id,@RequestBody Animal update){
        if(!animalRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        update.setId(id);
        Animal saved = animalRepository.save(update);
        return  ResponseEntity.ok(saved);
    }
}
