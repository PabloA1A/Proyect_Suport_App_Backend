package dev.pablo.Project_Support_App_Backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.pablo.Project_Support_App_Backend.models.Healthcenter;
import dev.pablo.Project_Support_App_Backend.repositories.HealthcenterRepository;

@Service
public class HealthcenterService {

    HealthcenterRepository repository;

    public HealthcenterService(HealthcenterRepository repository) {
        this.repository = repository;
    }

    public List<Healthcenter> getAll() {
        List<Healthcenter> healthcenters = repository.findAll();
        return healthcenters;
    }
}
