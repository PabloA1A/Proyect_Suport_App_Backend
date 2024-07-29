package dev.pablo.Project_Support_App_Backend.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.pablo.Project_Support_App_Backend.models.Patient;
import dev.pablo.Project_Support_App_Backend.repositories.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    PatientService service;

    @Mock
    PatientRepository repository;

    @Test
    @DisplayName("Should return a list of all patients")
    void testGetAll() {

        List<Patient> patients = new ArrayList<>();
        Patient pablo = new Patient(1L, "Pablo", "2024/01/01", "Test Subject", "Test Description");
        Patient maria = new Patient(2L, "Maria", "2024/02/02", "Test Subject", "Test Description");
        patients.add(pablo);
        patients.add(maria);

        when(repository.findAll()).thenReturn(patients);
        List<Patient> result = service.getAll();

        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0).getName(), equalTo(pablo.getName()));
        assertThat(result, contains(pablo, maria));
    }

    @Test
    @DisplayName("Should create a new patient")
    void testCreate() {

        Patient newPatient = new Patient(null, "Pablo", "2024/01/01", "Test Subject", "Test Description");
        Patient savedPatient = new Patient(1L, "Pablo", "2024/01/01", "Test Subject", "Test Description");

        when(repository.save(any(Patient.class))).thenReturn(savedPatient);
        Patient result = service.create(newPatient);

        assertThat(result.getId(), equalTo(1L));
        assertThat(result.getName(), equalTo("Pablo"));
    }

    @Test
    @DisplayName("Should return a patient by ID")
    void testGetById() {

        Patient pablo = new Patient(1L, "Pablo", "2024/01/01", "Test Subject", "Test Description");

        when(repository.findById(1L)).thenReturn(Optional.of(pablo));
        Patient result = service.getById(1L);

        assertThat(result.getId(), equalTo(1L));
        assertThat(result.getName(), equalTo("Pablo"));
    }

    @Test
    @DisplayName("Should update an existing patient")
    void testUpdate() {

        Patient updatedPatient = new Patient(1L, "Pablo Updated", "2024/01/01", "Test Subject Updated",
                "Test Description Updated");

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(any(Patient.class))).thenReturn(updatedPatient);
        Patient result = service.update(1L, updatedPatient);

        assertThat(result.getId(), equalTo(1L));
        assertThat(result.getName(), equalTo("Pablo Updated"));
        assertThat(result.getSubject(), equalTo("Test Subject Updated"));
    }

    @Test
    @DisplayName("Should return null when updating a non-existing patient")
    void testUpdateNonExisting() {

        Patient updatedPatient = new Patient(1L, "Pablo Updated", "2024/01/01", "Test Subject Updated",
                "Test Description Updated");

        when(repository.existsById(1L)).thenReturn(false);
        Patient result = service.update(1L, updatedPatient);

        assertThat(result, equalTo(null));
    }

    @Test
    @DisplayName("Should delete an existing patient")
    void testDelete() {

        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        boolean result = service.delete(1L);

        assertThat(result, equalTo(true));
        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Should return false when deleting a non-existing patient")
    void testDeleteNonExisting() {

        when(repository.existsById(1L)).thenReturn(false);

        boolean result = service.delete(1L);

        assertThat(result, equalTo(false));
    }
}