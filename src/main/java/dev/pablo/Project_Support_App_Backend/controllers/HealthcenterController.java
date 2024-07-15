package dev.pablo.Project_Support_App_Backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pablo.Project_Support_App_Backend.models.Healthcenter;
import dev.pablo.Project_Support_App_Backend.services.HealthcenterService;

@RestController
public class HealthcenterController {

    private HealthcenterService service;

    public HealthcenterController(HealthcenterService service) {
        this.service = service;
    }

    @GetMapping("/healthcenters")
    public List<Healthcenter> index() {

        return service.getAll();
    }
}
