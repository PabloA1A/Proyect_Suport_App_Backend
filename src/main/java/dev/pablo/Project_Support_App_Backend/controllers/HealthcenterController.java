package dev.pablo.Project_Support_App_Backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.pablo.Project_Support_App_Backend.models.Healthcenter;
import dev.pablo.Project_Support_App_Backend.services.HealthcenterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/healthcenters")
@CrossOrigin(origins = "http://localhost:5173")
public class HealthcenterController {

    @Autowired
    private HealthcenterService service;

    @GetMapping
    public List<Healthcenter> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Healthcenter> createHealthcenter(@RequestBody Healthcenter healthcenter) {
        Healthcenter created = service.create(healthcenter);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Healthcenter> getHealthcenterById(@PathVariable Long id) {
        Healthcenter healthcenter = service.getById(id);
        return healthcenter != null ? ResponseEntity.ok(healthcenter) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Healthcenter> updateHealthcenter(@PathVariable Long id, @RequestBody Healthcenter healthcenter) {
        Healthcenter updated = service.update(id, healthcenter);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthcenter(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
