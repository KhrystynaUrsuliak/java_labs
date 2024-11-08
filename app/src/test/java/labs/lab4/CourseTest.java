package labs.lab4;

import labs.lab4.Course;
import labs.lab4.Language;
import jakarta.validation.ValidationException;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
@Test
  void testValidCourseCreation() {
    Course course = Course.builder()
    .name("English Basics")
    .language(Language.ENGLISH)
    .level("A1")
    .startDate(LocalDate.of(2023, 1, 1))
    .endDate(LocalDate.of(2023, 6, 1))
    .price(100.0)
    .build();

    String expectedCourseString = "Course{name='English Basics', language=ENGLISH, level='A1', startDate=2023-01-01, endDate=2023-06-01, price=100.0}";
    assertEquals(expectedCourseString, course.toString());
  }

  @Test
  void testInvalidCourseName() {
    Exception exception = assertThrows(ValidationException.class, () -> {
      Course.builder()
        .name("")
        .language(Language.ENGLISH)
        .level("A1")
        .startDate(LocalDate.of(2023, 1, 1))
        .endDate(LocalDate.of(2023, 6, 1))
        .price(100.0)
        .build();
    });
    assertTrue(exception.getMessage().contains("name must be between 1 and 50 characters"));
  }

  @Test
  void testNullLanguage() {
    Exception exception = assertThrows(ValidationException.class, () -> {
      Course.builder()
        .name("English Basics")
        .language(null)
        .level("A1")
        .startDate(LocalDate.of(2023, 1, 1))
        .endDate(LocalDate.of(2023, 6, 1))
        .price(100.0)
        .build();
    });
    assertTrue(exception.getMessage().contains("language cannot be null"));
  }

  @Test
  void testInvalidLevelFormat() {
    Exception exception = assertThrows(ValidationException.class, () -> {
      Course.builder()
        .name("English Basics")
        .language(Language.ENGLISH)
        .level("A5")
        .startDate(LocalDate.of(2023, 1, 1))
        .endDate(LocalDate.of(2023, 6, 1))
        .price(100.0)
        .build();
    });
    assertTrue(exception.getMessage().contains("level should be in format A1, A2, ... , C2"));
  }

  @Test
  void testNegativePrice() {
    Exception exception = assertThrows(ValidationException.class, () -> {
      Course.builder()
        .name("English Basics")
        .language(Language.ENGLISH)
        .level("A1")
        .startDate(LocalDate.of(2023, 1, 1))
        .endDate(LocalDate.of(2023, 6, 1))
        .price(-50.0)
        .build();
    });
    assertTrue(exception.getMessage().contains("Price cannot be negative"));
  }

  @Test
  void testEndDateBeforeStartDate() {
    Exception exception = assertThrows(ValidationException.class, () -> {
      Course.builder()
        .name("English Basics")
        .language(Language.ENGLISH)
        .level("A1")
        .startDate(LocalDate.of(2023, 6, 1))
        .endDate(LocalDate.of(2023, 1, 1))
        .price(100.0)
        .build();
      });
      assertTrue(exception.getMessage().contains("End date cannot be before start date"));
  }
}
