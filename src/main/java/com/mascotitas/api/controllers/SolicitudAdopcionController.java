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

import com.mascotitas.api.models.SolicitudAdopcion;
import com.mascotitas.api.repositories.SolicitudAdopcionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitud_adopcion")
public class SolicitudAdopcionController {
    
    @Autowired
    private SolicitudAdopcionRepository adoptanteRepository;

    @CrossOrigin
    @GetMapping
    public List<SolicitudAdopcion> getAllMovies() {
        return adoptanteRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudAdopcion> getMovieById(@PathVariable Long id) {
        Optional<SolicitudAdopcion> data = adoptanteRepository.findById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<SolicitudAdopcion> create(@RequestBody SolicitudAdopcion adoptante){
        SolicitudAdopcion save = adoptanteRepository.save(adoptante);
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
    public ResponseEntity<SolicitudAdopcion> update(@PathVariable Long id,@RequestBody SolicitudAdopcion update){
        if(!adoptanteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        update.setId(id);
        SolicitudAdopcion saved = adoptanteRepository.save(update);
        return  ResponseEntity.ok(saved);
    }
}
