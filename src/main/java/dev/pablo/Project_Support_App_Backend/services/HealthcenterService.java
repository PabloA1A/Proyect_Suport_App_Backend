package dev.pablo.Project_Support_App_Backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.pablo.Project_Support_App_Backend.models.Healthcenter;
import dev.pablo.Project_Support_App_Backend.repositories.HealthcenterRepository;

@Service
public class HealthcenterService {

    @Autowired
    private HealthcenterRepository repository;

    public List<Healthcenter> getAll() {
        return repository.findAll();
    }

    public Healthcenter create(Healthcenter healthcenter) {
        return repository.save(healthcenter);
    }

    public Healthcenter getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Healthcenter update(Long id, Healthcenter healthcenter) {
        if (repository.existsById(id)) {
            healthcenter.setId(id);
            return repository.save(healthcenter);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
