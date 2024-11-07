package labs.lab2;

import labs.lab1.Tutor;
import labs.lab1.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TutorServiceTest {
    private TutorService tutorService;
    private Tutor tutor1;
    private Tutor tutor2;
    private Tutor tutor3;
    private List<Tutor> tutors;

    @BeforeEach
    public void setup() {
      tutor1 = new Tutor.TutorBuilder("John", "Smith")
          .birthDate(LocalDate.of(1980, 5, 15))
          .language(Language.ENGLISH)
          .salary(50000)
          .build();

      tutor2 = new Tutor.TutorBuilder("Alice", "Brown")
            .birthDate(LocalDate.of(1975, 3, 22))
            .language(Language.FRENCH)
            .salary(55000)
            .build();

      tutor3 = new Tutor.TutorBuilder("Bob", "Johnson")
            .birthDate(LocalDate.of(1990, 7, 10))
            .language(Language.ENGLISH)
            .salary(45000)
            .build();
            
      tutors = Arrays.asList(tutor1, tutor2, tutor3);
      tutorService = new TutorService();
      tutorService.setTutors(tutors);
    }

    @Test
    public void testSortBySalaryAscending() {
        List<Tutor> sortedTutors = tutorService.sortBySalary(false);
        List<Tutor> expected = Arrays.asList(tutor3, tutor1, tutor2);
        assertEquals(expected, sortedTutors, "Tutors should be sorted by salary in ascending order");
    }

    @Test
    public void testSortBySalaryDescending() {
        List<Tutor> sortedTutors = tutorService.sortBySalary(true);
        List<Tutor> expected = Arrays.asList(tutor2, tutor1, tutor3);
        assertEquals(expected, sortedTutors, "Tutors should be sorted by salary in descending order");
    }

    @Test
    public void testFindByLanguage() {
        List<Tutor> englishTutors = tutorService.findByLanguage(Language.ENGLISH);
        assertEquals(2, englishTutors.size(), "Should find two tutors who teach English");
        assertTrue(englishTutors.contains(tutor1), "Tutor list should contain John Smith");
        assertTrue(englishTutors.contains(tutor3), "Tutor list should contain Bob Johnson");

        List<Tutor> frenchTutors = tutorService.findByLanguage(Language.FRENCH);
        assertEquals(1, frenchTutors.size(), "Should find one tutor who teaches French");
        assertEquals(tutor2, frenchTutors.get(0), "The tutor found should be Alice Brown");
    }

    @Test
    public void testSortByAge() {
        Optional<Tutor> oldestTutor = tutorService.sortByAge();
        assertTrue(oldestTutor.isPresent(), "There should be an oldest tutor");
        assertEquals(tutor2, oldestTutor.get(), "The oldest tutor should be Alice Brown");
    }
}
