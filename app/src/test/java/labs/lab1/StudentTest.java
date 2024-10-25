package labs.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    Student student;

    @BeforeEach
    void setUp() {
        student = new Student("John", "Doe", "john.doe@example.com", "A1");
    }

    @Test
    void testConstructorAndGetters() {
        // Act & Assert
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("john.doe@example.com", student.getEmail());
        assertEquals("A1", student.getInitialLevel());
    }

    @Test
    void testSetters() {
        // Act
        student.setFirstName("Jane");
        student.setLastName("Smith");
        student.setEmail("jane.smith@example.com");
        student.setInitialLevel("B2");

        // Assert
        assertEquals("Jane", student.getFirstName());
        assertEquals("Smith", student.getLastName());
        assertEquals("jane.smith@example.com", student.getEmail());
        assertEquals("B2", student.getInitialLevel());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Student student2 = new Student("John", "Doe", "john.doe@example.com", "A1");

        // Act & Assert
        assertEquals(student, student2);
        assertEquals(student.hashCode(), student2.hashCode());
    }

    @Test
    void testNotEquals() {
        // Arrange
        Student student2 = new Student("Jane", "Smith", "jane.smith@example.com", "B2");

        // Act & Assert
        assertNotEquals(student, student2);
    }

    @Test
    void testToString() {
        // Act
        String expected = "Student{firstName='John', lastName='Doe', email='john.doe@example.com', initialLevel='A1'}";
        String actual = student.toString();

        // Assert
        assertEquals(expected, actual);
    }
}
