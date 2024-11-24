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

import com.mascotitas.api.models.Mascota;
import com.mascotitas.api.repositories.MascotaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mascota")
public class MascotaController {
    
    @Autowired
    private MascotaRepository adoptanteRepository;

    @CrossOrigin
    @GetMapping
    public List<Mascota> getAll() {
        return adoptanteRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getById(@PathVariable Long id) {
        Optional<Mascota> data = adoptanteRepository.findById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Mascota> create(@RequestBody Mascota adoptante){
        Mascota save = adoptanteRepository.save(adoptante);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!adoptanteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        adoptanteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> update(@PathVariable Long id,@RequestBody Mascota update){
        if(!adoptanteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        update.setId(id);
        Mascota saved = adoptanteRepository.save(update);
        return  ResponseEntity.ok(saved);
    }
}
