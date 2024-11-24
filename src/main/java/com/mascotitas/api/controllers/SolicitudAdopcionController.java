package com.mascotitas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.mascotitas.api.models.SolicitudAdopcion;
import com.mascotitas.api.repositories.SolicitudAdopcionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitud_adopcion")
public class SolicitudAdopcionController {
    
    @Autowired
    private SolicitudAdopcionRepository solicitudAdopcionRepository;

    @CrossOrigin
    @GetMapping
    public List<SolicitudAdopcion> getAll() {
        return solicitudAdopcionRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudAdopcion> getById(@PathVariable Long id) {
        Optional<SolicitudAdopcion> data = solicitudAdopcionRepository.findById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /* public boolean animalIsAdopted(Long animalId, EntityManager entityManager) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<SolicitudAdopcion> cq = cb.createQuery(SolicitudAdopcion.class);
            Root<SolicitudAdopcion> order = cq.from(SolicitudAdopcion.class);
            cq.where(cb.equal(order.get("animales").get("id"), animalId));

        TypedQuery<SolicitudAdopcion> query = entityManager.createQuery(cq);
        SolicitudAdopcion res = query.getResultStream().findFirst().orElse(null);
        return res != null;
    } */

    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> create(@RequestBody SolicitudAdopcion solicitud) {
        Boolean animalIsAdopted = false;
        List<SolicitudAdopcion> list = solicitudAdopcionRepository.findAll();

        for (SolicitudAdopcion solicitudAdopcion : list) {
            if (solicitudAdopcion.getMascota().getId() == solicitud.getMascota().getId()) {
                animalIsAdopted = true;
            }
        }

        if (animalIsAdopted) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La mascota ya esta adoptada.");
        }

        solicitudAdopcionRepository.save(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body("Adoptado con exito");
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!solicitudAdopcionRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        solicitudAdopcionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<SolicitudAdopcion> update(@PathVariable Long id,@RequestBody SolicitudAdopcion update){
        if(!solicitudAdopcionRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        update.setId(id);
        SolicitudAdopcion saved = solicitudAdopcionRepository.save(update);
        return  ResponseEntity.ok(saved);
    }
}
