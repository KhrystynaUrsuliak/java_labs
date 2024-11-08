package labs.lab4;

import labs.lab1.Language;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import java.util.Set;
import java.util.List;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class Course {
  @NotNull(message = "Name cannot be null!")
  @Size(min = 1, max = 50, message = "name must be between 1 and 50 characters long.")
  private String name;

  @NotNull(message = "language cannot be null!")
  private Language language;

  @Pattern(regexp = "^[A-C][1-2]$", message = "level should be in format A1, A2, ... , C2.")
  private String level;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @PastOrPresent
  private LocalDate startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @PastOrPresent
  private LocalDate endDate;

  @Positive(message = "Price cannot be negative!")
  private double price;

  private Course(String name, Language language, String level, LocalDate startDate, LocalDate endDate, double price) {
    this.name = name;
    this.language = language;
    this.level = level;
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
  }

  @Override
  public String toString() {
    return "Course{" +
            "name='" + name + '\'' +
            ", language=" + language +
            ", level='" + level + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", price=" + price +
            '}';
  }

  public static CourseBuilder builder() {
    return new CourseBuilder();
  }

  public static class CourseBuilder {
    private String name;
    private Language language;
    private String level;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;

    CourseBuilder() {
    }

    public CourseBuilder name(String name) {
      this.name = name;
      return this;
    }

    public CourseBuilder language(Language language) {
      this.language = language;
      return this;
    }

    public CourseBuilder level(String level) {
      this.level = level;
      return this;
    }

    public CourseBuilder startDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    public CourseBuilder endDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    public CourseBuilder price(double price) {
      this.price = price;
      return this;
    }

    public Course build() {
      var course = new Course(this.name, this.language, this.level, this.startDate, this.endDate, this.price);

      try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        List<String> validationErrors = violations.stream()
                .map(v -> "validation error in" + v.getPropertyPath() + ", value `" + v.getInvalidValue() + "`should satisfy condition: " + v.getMessage()).toList();
        if (!violations.isEmpty()) {
          throw new ValidationException(String.join("\n", validationErrors));
        }

        return course;
      }        
    }
  }
}
