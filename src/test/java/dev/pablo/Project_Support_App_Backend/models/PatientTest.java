package dev.pablo.Project_Support_App_Backend.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient(1L, "Test Name", "2024/01/01", "Test Subject", "Test Description");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1L, patient.getId());
        assertEquals("Test Name", patient.getName());
        assertEquals("2024/01/01", patient.getDate());
        assertEquals("Test Subject", patient.getSubject());
        assertEquals("Test Description", patient.getDescription());
    }

    @Test
    void testSetId() {
        patient.setId(2L);
        assertEquals(2L, patient.getId());
    }

    @Test
    void testSetName() {
        patient.setName("New Name");
        assertEquals("New Name", patient.getName());
    }

    @Test
    void testDate() {
        patient.setDate("2024/02/02");
        assertEquals("2024/02/02", patient.getDate());
    }

    @Test
    void testSetSubject() {
        patient.setSubject("New Subject");
        assertEquals("New Subject", patient.getSubject());
    }

    @Test
    void testSetDescription() {
        patient.setDescription("New Description");
        assertEquals("New Description", patient.getDescription());
    }

    @Test
    void testEmptyConstructor() {
        Patient emptyPatient = new Patient();
        assertNull(emptyPatient.getId());
        assertNull(emptyPatient.getName());
        assertNull(emptyPatient.getDate());
        assertNull(emptyPatient.getSubject());
        assertNull(emptyPatient.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        Patient patient1 = new Patient(1L, "Test Name", "2024/01/01", "Test Subject", "Test Description");
        Patient patient2 = new Patient(1L, "Test Name", "2024/01/01", "Test Subject", "Test Description");
        Patient patient3 = new Patient(2L, "Test Name", "2024/02/02", "Other Subject", "Other Description");

        assertEquals(patient1, patient2);
        assertNotEquals(patient1, patient3);
        assertEquals(patient1.hashCode(), patient2.hashCode());
        assertNotEquals(patient1.hashCode(), patient3.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Patient{id=1, name='Test Name', date='2024/01/01', subject='Test Subject', description='Test Description'}";
        assertThat(patient.toString(), is(expected));
    }
}