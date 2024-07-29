package dev.pablo.Project_Support_App_Backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.pablo.Project_Support_App_Backend.models.Patient;
import dev.pablo.Project_Support_App_Backend.repositories.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public List<Patient> getAll() {
        return repository.findAll();
    }

    public Patient create(Patient patient) {
        return repository.save(patient);
    }

    public Patient getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Patient update(Long id, Patient patient) {
        if (repository.existsById(id)) {
            patient.setId(id);
            return repository.save(patient);
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