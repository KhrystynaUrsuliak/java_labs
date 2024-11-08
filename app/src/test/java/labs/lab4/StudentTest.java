package labs.lab4;

import labs.lab4.Student;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testValidStudentCreation() {
        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .initialLevel("A1")
                .build();

        String expectedStudentString = "Student{firstName='John', lastName='Doe', email='john.doe@example.com', initialLevel='A1'}";
        assertEquals(expectedStudentString, student.toString());
    }

    @Test
    void testNullFirstName() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            Student.builder()
                    .firstName(null)
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .initialLevel("A1")
                    .build();
        });
        assertTrue(exception.getMessage().contains("First name cannot be null"));
    }

    @Test
    void testEmptyLastName() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            Student.builder()
                    .firstName("John")
                    .lastName("")
                    .email("john.doe@example.com")
                    .initialLevel("A1")
                    .build();
        });
        assertTrue(exception.getMessage().contains("Last name must be between 1 and 50 characters"));
    }

    @Test
    void testInvalidEmailFormat() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            Student.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("invalid-email")
                    .initialLevel("A1")
                    .build();
        });
        assertTrue(exception.getMessage().contains("Email must be valid"));
    }

    @Test
    void testInvalidInitialLevel() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            Student.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .initialLevel("D3")
                    .build();
        });
        assertTrue(exception.getMessage().contains("Initial level should be in format A1, A2, ... , C2"));
    }

    @Test
    void testValidInitialLevelBoundaries() {
        Student student = Student.builder()
                .firstName("Alice")
                .lastName("Smith")
                .email("alice.smith@example.com")
                .initialLevel("C2")
                .build();

        String expectedStudentString = "Student{firstName='Alice', lastName='Smith', email='alice.smith@example.com', initialLevel='C2'}";
        assertEquals(expectedStudentString, student.toString());
    }
}
