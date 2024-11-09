package labs.lab4;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import jakarta.validation.ValidationException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class CourseTest {
  private Course.CourseBuilder courseBuilder;

  @BeforeEach
  public void setUp() {
    courseBuilder = Course.builder()
        .name("Advanced English")
        .language(Language.ENGLISH)
        .level("C2")
        .startDate(LocalDate.of(2021, 9, 1))
        .endDate(LocalDate.of(2022, 6, 30))
        .price(1000);
  }

  @Test
  public void testValidCourse() {
    assertDoesNotThrow(() -> courseBuilder.build());
  }

  @Test
  public void testNameNotNull() {
    courseBuilder.name(null);
    ValidationException exception = assertThrows(ValidationException.class, () -> courseBuilder.build());
    assertTrue(exception.getMessage().contains("Name cannot be null!"));
  }

  @Test
  public void testNameSizeConstraint() {
    courseBuilder.name("");
    ValidationException exception = assertThrows(ValidationException.class, () -> courseBuilder.build());
    System.out.println();
    assertTrue(exception.getMessage().contains("name must be between 1 and 50 characters long."));
  }

  @Test
  public void testLanguageNotNull() {
    courseBuilder.language(null);
    ValidationException exception = assertThrows(ValidationException.class, () -> courseBuilder.build());
    assertTrue(exception.getMessage().contains("language cannot be null"));
  }

  @Test
  public void testLevelPattern() {
    courseBuilder.level("D1");
    ValidationException exception = assertThrows(ValidationException.class, () -> courseBuilder.build());
    assertTrue(exception.getMessage().contains("level should be in format A1, A2, ... , C2"));
  }

  @Test
  public void testStartDatePastOrPresent() {
    courseBuilder.startDate(LocalDate.now().plusDays(1));
    ValidationException exception = assertThrows(ValidationException.class, () -> courseBuilder.build());
    assertTrue(exception.getMessage().contains("must be a date in the past or in the present"));
  }

  @Test
  public void testEndDateBeforeStartDate() {
    courseBuilder.startDate(LocalDate.of(2023, 1, 1));
    courseBuilder.endDate(LocalDate.of(2022, 12, 31));
    ValidationException exception = assertThrows(ValidationException.class, () -> courseBuilder.build());
    assertTrue(exception.getMessage().contains("End date cannot be before start date"));
  }

  @Test
  public void testPricePositive() {
    courseBuilder.price(-50.0);
    ValidationException exception = assertThrows(ValidationException.class, () -> courseBuilder.build());
    assertTrue(exception.getMessage().contains("Price cannot be negative"));
  }
}
