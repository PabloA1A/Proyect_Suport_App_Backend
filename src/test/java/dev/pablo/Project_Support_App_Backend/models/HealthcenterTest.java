package dev.pablo.Project_Support_App_Backend.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HealthcenterTest {

    private Healthcenter healthcenter;

    @BeforeEach
    void setUp() {
        healthcenter = new Healthcenter(1L, "Test Name", "2024/01/01", "Test Subject", "Test Description");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1L, healthcenter.getId());
        assertEquals("Test Name", healthcenter.getName());
        assertEquals("2024/01/01", healthcenter.getDate());
        assertEquals("Test Subject", healthcenter.getSubject());
        assertEquals("Test Description", healthcenter.getDescription());
    }

    @Test
    void testSetId() {
        healthcenter.setId(2L);
        assertEquals(2L, healthcenter.getId());
    }

    @Test
    void testSetName() {
        healthcenter.setName("New Name");
        assertEquals("New Name", healthcenter.getName());
    }

    @Test
    void testDate() {
        healthcenter.setDate("2024/02/02");
        assertEquals("2024/02/02", healthcenter.getDate());
    }

    @Test
    void testSetSubject() {
        healthcenter.setSubject("New Subject");
        assertEquals("New Subject", healthcenter.getSubject());
    }

    @Test
    void testSetDescription() {
        healthcenter.setDescription("New Description");
        assertEquals("New Description", healthcenter.getDescription());
    }

    @Test
    void testEmptyConstructor() {
        Healthcenter emptyHealthcenter = new Healthcenter();
        assertNull(emptyHealthcenter.getId());
        assertNull(emptyHealthcenter.getName());
        assertNull(emptyHealthcenter.getDate());
        assertNull(emptyHealthcenter.getSubject());
        assertNull(emptyHealthcenter.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        Healthcenter healthcenter1 = new Healthcenter(1L, "Test Name", "2024/01/01", "Test Subject",
                "Test Description");
        Healthcenter healthcenter2 = new Healthcenter(1L, "Test Name", "2024/01/01", "Test Subject",
                "Test Description");
        Healthcenter healthcenter3 = new Healthcenter(2L, "Test Name", "2024/02/02", "Other Subject",
                "Other Description");

        assertEquals(healthcenter1, healthcenter2);
        assertNotEquals(healthcenter1, healthcenter3);
        assertEquals(healthcenter1.hashCode(), healthcenter2.hashCode());
        assertNotEquals(healthcenter1.hashCode(), healthcenter3.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Healthcenter{id=1, name='Test Name', date='2024/01/01', subject='Test Subject', description='Test Description'}";
        assertThat(healthcenter.toString(), is(expected));
    }
}
