package dev.pablo.Project_Support_App_Backend.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.pablo.Project_Support_App_Backend.models.Healthcenter;
import dev.pablo.Project_Support_App_Backend.services.HealthcenterService;

@WebMvcTest(HealthcenterController.class)
// @AutoConfigureMockMvc(addFilters = false) to disable security
public class HealthcenterControllerTest {

        @Autowired
        MockMvc mockMvc;

        @MockBean
        HealthcenterService service;

        @Autowired
        ObjectMapper mapper;

        @Test
        @DisplayName("Should return a list of healthcenter")
        void testIndex() throws Exception {

                List<Healthcenter> healthcenters = new ArrayList<>();
                Healthcenter pablo = new Healthcenter(1L, "Pablo", "2024/01/01", "Test Subject", "Test Description");
                Healthcenter maria = new Healthcenter(2L, "Maria", "2024/02/02", "Test Subject", "Test Description");
                healthcenters.add(pablo);
                healthcenters.add(maria);

                when(service.getAll()).thenReturn(healthcenters);
                MockHttpServletResponse response = mockMvc.perform(get("/healthcenters")
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse();

                System.out.println(response.getContentAsString());

                assertThat(response.getStatus(), equalTo(200));
                assertThat(response.getContentAsString(), containsString("Pablo"));
                assertThat(response.getContentAsString(), containsString("Maria"));
                assertThat(response.getContentAsString(), equalTo(mapper.writeValueAsString(healthcenters)));
        }

        @Test
        @DisplayName("Should create a new healthcenter")
        void testCreate() throws Exception {

                Healthcenter newHealthcenter = new Healthcenter(null, "Andres", "2024/03/03", "Test Subject",
                                "Test Description");
                Healthcenter createdHealthcenter = new Healthcenter(3L, "Andres", "2024/03/03", "Test Subject",
                                "Test Description");

                when(service.create(newHealthcenter)).thenReturn(createdHealthcenter);
                MockHttpServletResponse response = mockMvc.perform(post("/healthcenters")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(newHealthcenter))
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isCreated())
                                .andReturn()
                                .getResponse();

                System.out.println(response.getContentAsString());

                assertThat(response.getStatus(), equalTo(201));
                assertThat(response.getContentAsString(), containsString("Andres"));
                assertThat(response.getContentAsString(), equalTo(mapper.writeValueAsString(createdHealthcenter)));
        }

        @Test
        @DisplayName("Should update an existing healthcenter")
        void testUpdate() throws Exception {

                Healthcenter updatedHealthcenter = new Healthcenter(1L, "Pablo Updated", "2024/01/01",
                                "Test Subject Updated", "Test Description Updated");

                when(service.update(1L, updatedHealthcenter)).thenReturn(updatedHealthcenter);
                MockHttpServletResponse response = mockMvc.perform(put("/healthcenters/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updatedHealthcenter))
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse();

                System.out.println(response.getContentAsString());

                assertThat(response.getStatus(), equalTo(200));
                assertThat(response.getContentAsString(), containsString("Pablo Updated"));
                assertThat(response.getContentAsString(), equalTo(mapper.writeValueAsString(updatedHealthcenter)));
        }
}
