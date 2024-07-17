package dev.pablo.Project_Support_App_Backend.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
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

import dev.pablo.Project_Support_App_Backend.models.Healthcenter;
import dev.pablo.Project_Support_App_Backend.repositories.HealthcenterRepository;

@ExtendWith(MockitoExtension.class)
public class HealthcenterServiceTest {

    @InjectMocks
    HealthcenterService service;

    @Mock
    HealthcenterRepository repository;

    @Test
    @DisplayName("Should return a list of all healthcenters")
    void testGetAll() {

        List<Healthcenter> healthcenters = new ArrayList<>();
        Healthcenter pablo = new Healthcenter(1L, "Pablo", "2024/01/01", "Test Subject", "Test Description");
        Healthcenter maria = new Healthcenter(2L, "Maria", "2024/02/02", "Test Subject", "Test Description");
        healthcenters.add(pablo);
        healthcenters.add(maria);

        when(repository.findAll()).thenReturn(healthcenters);
        List<Healthcenter> result = service.getAll();

        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0).getName(), equalTo(pablo.getName()));
        assertThat(result, contains(pablo, maria));
    }

    @Test
    @DisplayName("Should create a new healthcenter")
    void testCreate() {
        
        Healthcenter newHealthcenter = new Healthcenter(null, "Pablo", "2024/01/01", "Test Subject", "Test Description");
        Healthcenter savedHealthcenter = new Healthcenter(1L, "Pablo", "2024/01/01", "Test Subject", "Test Description");

        when(repository.save(any(Healthcenter.class))).thenReturn(savedHealthcenter);
        Healthcenter result = service.create(newHealthcenter);

        assertThat(result.getId(), equalTo(1L));
        assertThat(result.getName(), equalTo("Pablo"));
    }

    @Test
    @DisplayName("Should update an existing healthcenter")
    void testUpdate() {
        
        Healthcenter existingHealthcenter = new Healthcenter(1L, "Pablo", "2024/01/01", "Test Subject", "Test Description");
        Healthcenter updatedHealthcenter = new Healthcenter(1L, "Pablo Updated", "2024/01/01", "Test Subject Updated", "Test Description Updated");

        when(repository.findById(1L)).thenReturn(Optional.of(existingHealthcenter));
        when(repository.save(any(Healthcenter.class))).thenReturn(updatedHealthcenter);
        Healthcenter result = service.update(1L, updatedHealthcenter);

        assertThat(result.getId(), equalTo(1L));
        assertThat(result.getName(), equalTo("Pablo Updated"));
        assertThat(result.getSubject(), equalTo("Test Subject Updated"));
    }
}
