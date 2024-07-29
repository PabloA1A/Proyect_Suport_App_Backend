package dev.pablo.Project_Support_App_Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.pablo.Project_Support_App_Backend.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
