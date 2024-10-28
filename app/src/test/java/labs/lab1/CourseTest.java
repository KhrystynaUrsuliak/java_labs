package labs.lab1;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@code Course} class.
 */
public class CourseTest {

    @Test
    public void testCourseConstructorAndGetters() {
        LocalDate startDate = LocalDate.of(2024, 1, 10);
        LocalDate endDate = LocalDate.of(2024, 3, 20);
        
        Course course = new Course("English for Beginners", Language.ENGLISH, "A1", startDate, endDate, 250.0);

        assertEquals("English for Beginners", course.getName());
        assertEquals(Language.ENGLISH, course.getLanguage());  
        assertEquals("A1", course.getLevel());
        assertEquals(startDate, course.getStartDate());
        assertEquals(endDate, course.getEndDate());
        assertEquals(250.0, course.getPrice(), 0.0001);  
    }

    @Test
    public void testSetters() {
        LocalDate startDate = LocalDate.of(2024, 1, 10);
        LocalDate endDate = LocalDate.of(2024, 3, 20);
    
        Course course = new Course("English for Beginners", Language.ENGLISH, "A1", startDate, endDate, 250.0);

        course.setName("Intermediate English");
        assertEquals("Intermediate English", course.getName());

        course.setLanguage(Language.SPANISH);
        assertEquals(Language.SPANISH, course.getLanguage());

        course.setLevel("B1");
        assertEquals("B1", course.getLevel());

        LocalDate newStartDate = LocalDate.of(2024, 2, 1);
        course.setStartDate(newStartDate);
        assertEquals(newStartDate, course.getStartDate());

        LocalDate newEndDate = LocalDate.of(2024, 4, 1);
        course.setEndDate(newEndDate);
        assertEquals(newEndDate, course.getEndDate());

        course.setPrice(300.0);
        assertEquals(300.0, course.getPrice(), 0.0001);  
    }

    @Test
    public void testEqualsAndHashCode() {
        LocalDate startDate = LocalDate.of(2024, 1, 10);
        LocalDate endDate = LocalDate.of(2024, 3, 20);
        
        Course course1 = new Course("English for Beginners", Language.ENGLISH, "A1", startDate, endDate, 250.0);
        Course course2 = new Course("English for Beginners", Language.ENGLISH, "A1", startDate, endDate, 250.0);

        assertEquals(course1, course2);
        assertEquals(course1.hashCode(), course2.hashCode());

        course2.setPrice(300.0);
        assertNotEquals(course1, course2);
        assertNotEquals(course1.hashCode(), course2.hashCode());
    }

    @Test
    public void testToString() {
        LocalDate startDate = LocalDate.of(2024, 1, 10);
        LocalDate endDate = LocalDate.of(2024, 3, 20);

        Course course = new Course("English for Beginners", Language.ENGLISH, "A1", startDate, endDate, 250.0);

        String expected = "Course{name='English for Beginners', language=ENGLISH, level='A1', startDate=2024-01-10, endDate=2024-03-20, price=250.0}";
        assertEquals(expected, course.toString());
    }
}
