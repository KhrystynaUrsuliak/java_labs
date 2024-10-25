package labs.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@code Group} class.
 */
public class GroupTest {

    private Group group;
    private Course course;
    private Tutor tutor;
    private List<Student> students;

    @BeforeEach
    void setUp() {
        course = new Course("English for Beginners", "English", "A1", LocalDate.of(2024, 1, 10), LocalDate.of(2024, 3, 20), 250.0);
        tutor = new Tutor.TutorBuilder("John", "Doe")
                .birthDate(LocalDate.of(1980, 5, 15))
                .qualification("Master of Linguistics")
                .salary(50.0)
                .build();

        Student student1 = new Student("Alice", "Smith", "alice@example.com", "A1");
        Student student2 = new Student("Bob", "Johnson", "bob@example.com", "A1");
        students = Arrays.asList(student1, student2);

        group = new Group("Group A1", course, tutor, students);
    }

    @Test
    void testGroupConstructorAndGetters() {
        assertEquals("Group A1", group.getName());
        assertEquals(course, group.getCourse());
        assertEquals(tutor, group.getTutor());
        assertEquals(students, group.getStudents());
    }

    @Test
    void testSetters() {
        Course newCourse = new Course("Advanced English", "English", "B1", LocalDate.of(2024, 2, 1), LocalDate.of(2024, 4, 1), 300.0);
        Tutor newTutor = new Tutor.TutorBuilder("Jane", "Doe")
                .birthDate(LocalDate.of(1982, 8, 25))
                .qualification("PhD in English Literature")
                .salary(60.0)
                .build();
        Student student3 = new Student("Charlie", "Brown", "charlie@example.com", "B1");
        List<Student> newStudents = Arrays.asList(student3);

        // Test setters
        group.setName("Group B1");
        assertEquals("Group B1", group.getName());

        group.setCourse(newCourse);
        assertEquals(newCourse, group.getCourse());

        group.setTutor(newTutor);
        assertEquals(newTutor, group.getTutor());

        group.setStudents(newStudents);
        assertEquals(newStudents, group.getStudents());
    }

    @Test
    void testEqualsAndHashCode() {
        Group group2 = new Group("Group A1", course, tutor, students);
        assertEquals(group, group2);
        assertEquals(group.hashCode(), group2.hashCode());

        group2.setName("Group B1");
        assertNotEquals(group, group2);
        assertNotEquals(group.hashCode(), group2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Group{name='Group A1', course=" + course + ", tutor=" + tutor + ", students=" + students + "}";
        assertEquals(expected, group.toString());
    }
}
