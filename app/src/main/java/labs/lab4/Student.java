package labs.lab4;

import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class Student {
  @NotNull(message = "First name cannot be null!")
  @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters.")
  private String firstName;

  @NotNull(message = "Last name cannot be null!")
  @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters.")
  private String lastName;

  @NotNull(message = "Email cannot be null!")
  @Email(message = "Email must be valid!")
  private String email;

  @Pattern(regexp = "^[A-C][1-2]$", message = "Initial level should be in format A1, A2, ... , C2.")
  private String initialLevel;

  private Student(String firstName, String lastName, String email, String initialLevel) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.initialLevel = initialLevel;
  }


  @Override
  public String toString() {
    return "Student{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", initialLevel='" + initialLevel + '\'' +
        '}';
  }

  public static StudentBuilder builder() {
    return new StudentBuilder();
  }

  public static class StudentBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private String initialLevel;

    StudentBuilder() {
    }

    public StudentBuilder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public StudentBuilder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public StudentBuilder email(String email) {
      this.email = email;
      return this;
    }

    public StudentBuilder initialLevel(String initialLevel) {
      this.initialLevel = initialLevel;
      return this;
    }

    public Student build() {
      var student = new Student(this.firstName, this.lastName, this.email, this.initialLevel);

      try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        List<String> validationErrors = violations.stream()
                  .map(v -> "validation error in" + v.getPropertyPath() + ", value `" + v.getInvalidValue() + "`should satisfy condition: " + v.getMessage()).toList();
        if (!violations.isEmpty()) {
          throw new ValidationException(String.join("\n", validationErrors));
        }

        return student;
      }
    }
  }
}
