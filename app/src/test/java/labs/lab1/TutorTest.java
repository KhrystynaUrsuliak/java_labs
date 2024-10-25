package labs.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@code Tutor} class.
 */
public class TutorTest {

    private Tutor tutor;

    @BeforeEach
    void setUp() {
        tutor = new Tutor.TutorBuilder("John", "Doe")
                .birthDate(LocalDate.of(1980, 5, 15))
                .qualification("Master of Linguistics")
                .salary(50.0)
                .build();
    }

    @Test
    void testTutorConstructorAndGetters() {
        assertEquals("John", tutor.getFirstName());
        assertEquals("Doe", tutor.getLastName());
        assertEquals(LocalDate.of(1980, 5, 15), tutor.getBirthDate());
        assertEquals("Master of Linguistics", tutor.getQualification());
        assertEquals(50.0, tutor.getSalary());
    }

    @Test
    void testSetters() {
        tutor.setFirstName("Alice");
        assertEquals("Alice", tutor.getFirstName());

        tutor.setLastName("Smith");
        assertEquals("Smith", tutor.getLastName());

        LocalDate newBirthDate = LocalDate.of(1990, 10, 1);
        tutor.setBirthDate(newBirthDate);
        assertEquals(newBirthDate, tutor.getBirthDate());

        tutor.setQualification("PhD in English Literature");
        assertEquals("PhD in English Literature", tutor.getQualification());

        tutor.setSalary(60.0);
        assertEquals(60.0, tutor.getSalary());
    }

    @Test
    void testEqualsAndHashCode() {
        Tutor tutor2 = new Tutor.TutorBuilder("John", "Doe")
                .birthDate(LocalDate.of(1980, 5, 15))
                .qualification("Master of Linguistics")
                .salary(50.0)
                .build();

        assertEquals(tutor, tutor2);
        assertEquals(tutor.hashCode(), tutor2.hashCode());

        tutor2.setSalary(60.0);
        assertNotEquals(tutor, tutor2);
        assertNotEquals(tutor.hashCode(), tutor2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Tutor{firstName='John', lastName='Doe', birthDate=1980-05-15, qualification='Master of Linguistics', salary=50.0}";
        assertEquals(expected, tutor.toString());
    }
}
