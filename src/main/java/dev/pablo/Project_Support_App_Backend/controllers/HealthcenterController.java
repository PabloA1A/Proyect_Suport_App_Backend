package dev.pablo.Project_Support_App_Backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class HealthcenterController {

    @Autowired
    private HealthcenterService service;

    @GetMapping
    public List<Healthcenter> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Healthcenter> create(@RequestBody Healthcenter healthcenter) {
        Healthcenter created = service.create(healthcenter);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Healthcenter> update(@PathVariable Long id, @RequestBody Healthcenter healthcenter) {
        Healthcenter updated = service.update(id, healthcenter);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
