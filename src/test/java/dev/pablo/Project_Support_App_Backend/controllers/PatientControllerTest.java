package dev.pablo.Project_Support_App_Backend.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.pablo.Project_Support_App_Backend.models.Patient;
import dev.pablo.Project_Support_App_Backend.services.PatientService;

@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PatientControllerTest {

        @Autowired
        MockMvc mockMvc;

        @MockBean
        PatientService service;

        @Autowired
        ObjectMapper mapper;

        @Test
        @DisplayName("Should return a list of patients")
        void testIndex() throws Exception {

                List<Patient> patients = new ArrayList<>();
                Patient pablo = new Patient(1L, "Pablo", "2024/01/01", "Test Subject", "Test Description");
                Patient maria = new Patient(2L, "Maria", "2024/02/02", "Test Subject", "Test Description");
                patients.add(pablo);
                patients.add(maria);

                when(service.getAll()).thenReturn(patients);
                MockHttpServletResponse response = mockMvc.perform(get("/patients")
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse();

                System.out.println(response.getContentAsString());

                assertThat(response.getStatus(), equalTo(200));
                assertThat(response.getContentAsString(), containsString("Pablo"));
                assertThat(response.getContentAsString(), containsString("Maria"));
                assertThat(response.getContentAsString(), equalTo(mapper.writeValueAsString(patients)));
        }

        @Test
        @DisplayName("Should create a new patient")
        void testCreate() throws Exception {

                Patient newPatient = new Patient(null, "Andres", "2024/03/03", "Test Subject", "Test Description");
                Patient createdPatient = new Patient(3L, "Andres", "2024/03/03", "Test Subject", "Test Description");

                when(service.create(newPatient)).thenReturn(createdPatient);
                MockHttpServletResponse response = mockMvc.perform(post("/patients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(newPatient))
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isCreated())
                                .andReturn()
                                .getResponse();

                System.out.println(response.getContentAsString());

                assertThat(response.getStatus(), equalTo(201));
                assertThat(response.getContentAsString(), containsString("Andres"));
                assertThat(response.getContentAsString(), equalTo(mapper.writeValueAsString(createdPatient)));
        }

        @Test
        @DisplayName("Should return a patient by ID")
        void testGetPatientById() throws Exception {

                Patient pablo = new Patient(1L, "Pablo", "2024/01/01", "Test Subject", "Test Description");

                when(service.getById(1L)).thenReturn(pablo);
                MockHttpServletResponse response = mockMvc.perform(get("/patients/1")
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse();

                System.out.println(response.getContentAsString());

                assertThat(response.getStatus(), equalTo(200));
                assertThat(response.getContentAsString(), containsString("Pablo"));
                assertThat(response.getContentAsString(), equalTo(mapper.writeValueAsString(pablo)));
        }

        @Test
        @DisplayName("Should update an existing patient")
        void testUpdate() throws Exception {

                Patient updatedPatient = new Patient(1L, "Pablo Updated", "2024/01/01", "Test Subject Updated",
                                "Test Description Updated");

                when(service.update(1L, updatedPatient)).thenReturn(updatedPatient);
                MockHttpServletResponse response = mockMvc.perform(put("/patients/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updatedPatient))
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse();

                System.out.println(response.getContentAsString());

                assertThat(response.getStatus(), equalTo(200));
                assertThat(response.getContentAsString(), containsString("Pablo Updated"));
                assertThat(response.getContentAsString(), equalTo(mapper.writeValueAsString(updatedPatient)));
        }

        @Test
        @DisplayName("Should delete an existing patient")
        void testDelete() throws Exception {

                when(service.delete(1L)).thenReturn(true);
                MockHttpServletResponse response = mockMvc.perform(delete("/patients/1")
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNoContent())
                                .andReturn()
                                .getResponse();

                System.out.println(response.getContentAsString());

                assertThat(response.getStatus(), equalTo(204));
        }
}