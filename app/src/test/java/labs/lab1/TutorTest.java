package labs.lab1;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@code Tutor} class.
 */

public class TutorTest {

    @Test
    public void testTutorBuilderAndGetters() {
        LocalDate birthDate = LocalDate.of(1985, 7, 12);

        Tutor tutor = new Tutor.TutorBuilder("John", "Doe")
                .birthDate(birthDate)
                .language(Language.ENGLISH)
                .salary(4000.0)
                .build();

        assertEquals("John", tutor.getFirstName());
        assertEquals("Doe", tutor.getLastName());
        assertEquals(birthDate, tutor.getBirthDate());
        assertEquals(Language.ENGLISH, tutor.getLanguage());
        assertEquals(4000.0, tutor.getSalary());
    }

    @Test
    public void testSetters() {
        LocalDate birthDate = LocalDate.of(1985, 7, 12);
        Tutor tutor = new Tutor.TutorBuilder("John", "Doe")
                .birthDate(birthDate)
                .language(Language.ENGLISH)
                .salary(4000.0)
                .build();

        tutor.setFirstName("Jane");
        tutor.setLastName("Smith");
        tutor.setBirthDate(LocalDate.of(1990, 1, 1));
        tutor.setLanguage(Language.SPANISH);
        tutor.setSalary(4500.0);

        assertEquals("Jane", tutor.getFirstName());
        assertEquals("Smith", tutor.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), tutor.getBirthDate());
        assertEquals(Language.SPANISH, tutor.getLanguage());
        assertEquals(4500.0, tutor.getSalary());
    }

    @Test
    public void testEqualsAndHashCode() {
        LocalDate birthDate = LocalDate.of(1985, 7, 12);

        Tutor tutor1 = new Tutor.TutorBuilder("John", "Doe")
                .birthDate(birthDate)
                .language(Language.ENGLISH)
                .salary(4000.0)
                .build();

        Tutor tutor2 = new Tutor.TutorBuilder("John", "Doe")
                .birthDate(birthDate)
                .language(Language.ENGLISH)
                .salary(4000.0)
                .build();

        assertEquals(tutor1, tutor2);
        assertEquals(tutor1.hashCode(), tutor2.hashCode());

        tutor2.setSalary(4500.0);
        assertNotEquals(tutor1, tutor2);
        assertNotEquals(tutor1.hashCode(), tutor2.hashCode());
    }

    @Test
    public void testToString() {
        LocalDate birthDate = LocalDate.of(1985, 7, 12);
        Tutor tutor = new Tutor.TutorBuilder("John", "Doe")
                .birthDate(birthDate)
                .language(Language.ENGLISH)
                .salary(4000.0)
                .build();

        String expected = "Tutor{firstName='John', lastName='Doe', birthDate=1985-07-12, language=ENGLISH, salary=4000.0}";
        assertEquals(expected, tutor.toString());
    }
}
