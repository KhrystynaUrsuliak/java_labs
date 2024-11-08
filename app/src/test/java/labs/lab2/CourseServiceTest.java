package labs.lab2;

import labs.lab1.Course;
import labs.lab1.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseServiceTest {
  private CourseService courseService;
    private Course course1;
    private Course course2;
    private Course course3;

    @BeforeEach
    public void setUp() {
        courseService = new CourseService();

        course1 = new Course("Basic Spanish", Language.SPANISH, "Beginner", 
                LocalDate.of(2024, 10, 1), LocalDate.of(2024, 12, 1), 100.0);
        course2 = new Course("Advanced English", Language.ENGLISH, "Advanced", 
                LocalDate.of(2024, 9, 1), LocalDate.of(2024, 11, 1), 150.0);
        course3 = new Course("Intermediate French", Language.FRENCH, "Intermediate", 
                LocalDate.of(2024, 8, 1), LocalDate.of(2024, 10, 1), 120.0);

        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courseService.setCourses(courses);
    }

    @Test
    public void testSortByPriceAscending() {
        List<Course> sortedCourses = courseService.sortByPrice(true);
        assertEquals(100.0, sortedCourses.get(0).getPrice());
        assertEquals(120.0, sortedCourses.get(1).getPrice());
        assertEquals(150.0, sortedCourses.get(2).getPrice());
    }

    @Test
    public void testSortByPriceDescending() {
        List<Course> sortedCourses = courseService.sortByPrice(false);
        assertEquals(150.0, sortedCourses.get(0).getPrice());
        assertEquals(120.0, sortedCourses.get(1).getPrice());
        assertEquals(100.0, sortedCourses.get(2).getPrice());
    }

    @Test
    public void testFilterByLanguage() {
        List<Course> spanishCourses = courseService.filterByLanguage(Language.SPANISH);
        assertEquals(1, spanishCourses.size());
        assertEquals("Basic Spanish", spanishCourses.get(0).getName());
    }
}
