package labs.lab2;

import labs.lab1.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentServiceTest {
    private StudentService studentService;
    private Student student1;
    private Student student2;
    private Student student3;
    private List<Student> students;

    @BeforeEach
    public void setup() {
        student1 = new Student("John", "Smith", "john.smith@example.com", "B2");
        student2 = new Student("Alice", "Brown", "alice.brown@example.com", "C1");
        student3 = new Student("Bob", "Smith", "bob.smith@example.com", "B1");

        students = Arrays.asList(student1, student2, student3);
        studentService = new StudentService();
        studentService.setStudents(students);
    }

    @Test
    public void testSortByLastName() {
        List<Student> expected = Arrays.asList(student2, student1, student3);
        List<Student> sortedStudents = studentService.sortByLastName();
        assertEquals(expected, sortedStudents, "Students should be sorted by last name");
    }

    @Test
    public void testFindByEmail() {
        List<Student> result = studentService.findByEmail("alice.brown@example.com");
        assertEquals(1, result.size(), "Should find one student with the specified email");
        assertEquals(student2, result.get(0), "The student found should match the expected student");

        result = studentService.findByEmail("nonexistent@example.com");
        assertTrue(result.isEmpty(), "No students should be found for a non-existent email");
    }

    @Test
    public void testFindByLevel() {
        List<Student> result = studentService.findByLevel("B2");
        assertEquals(1, result.size(), "Should find one student with level B2");
        assertEquals(student1, result.get(0), "The student found should match the expected student");

        result = studentService.findByLevel("B1");
        assertEquals(1, result.size(), "Should find one student with level B1");
        assertEquals(student3, result.get(0), "The student found should match the expected student");

        result = studentService.findByLevel("A1");
        assertTrue(result.isEmpty(), "No students should be found for a non-existent level");
    }
}
