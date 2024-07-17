package dev.pablo.Project_Support_App_Backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.pablo.Project_Support_App_Backend.models.Healthcenter;
import dev.pablo.Project_Support_App_Backend.repositories.HealthcenterRepository;

@Service
public class HealthcenterService {

    private HealthcenterRepository repository;

    public HealthcenterService(HealthcenterRepository repository) {
        this.repository = repository;
    }

    public List<Healthcenter> getAll() {
        return repository.findAll();
    }

    public Healthcenter create(Healthcenter healthcenter) {
        return repository.save(healthcenter);
    }

    public Healthcenter update(Long id, Healthcenter healthcenter) {
        return repository.findById(id).map(existingHealthcenter -> {
            existingHealthcenter.setName(healthcenter.getName());
            existingHealthcenter.setDate(healthcenter.getDate());
            existingHealthcenter.setSubject(healthcenter.getSubject());
            existingHealthcenter.setDescription(healthcenter.getDescription());
            return repository.save(existingHealthcenter);
        }).orElse(null);
    }
}
