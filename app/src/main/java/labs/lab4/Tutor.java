package labs.lab4;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import labs.lab1.Language;

import java.time.LocalDate;
import java.util.Set;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Tutor {

  @NotNull(message = "First name cannot be null!")
  @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters.")
  private String firstName;

  @NotNull(message = "Last name cannot be null!")
  @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters.")
  private String lastName;

  @NotNull(message = "Birth date cannot be null!")
  @Past(message = "Birth date must be in the past.")
  private LocalDate birthDate;

  @NotNull(message = "Language cannot be null!")
  private Language language;

  @PositiveOrZero(message = "Salary cannot be negative.")
  private double salary;

  private Tutor(String firstName, String lastName, LocalDate birthDate, Language language, double salary) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.language = language;
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "Tutor{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", birthDate=" + birthDate +
            ", language=" + language +
            ", salary=" + salary +
            '}';
  }

  public static TutorBuilder builder() {
    return new TutorBuilder();
  }

  public static class TutorBuilder {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Language language;
    private double salary;

    public TutorBuilder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public TutorBuilder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public TutorBuilder birthDate(LocalDate birthDate) {
      this.birthDate = birthDate;
      return this;
    }

    public TutorBuilder language(Language language) {
      this.language = language;
      return this;
    }

    public TutorBuilder salary(double salary) {
      this.salary = salary;
      return this;
    }

    public Tutor build() {
      Tutor tutor = new Tutor(this.firstName, this.lastName, this.birthDate, this.language, this.salary);

      try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        List<String> validationErrors = violations.stream()
            .map(v -> "Validation error in " + v.getPropertyPath() +
                       ", value `" + v.getInvalidValue() +
                       "` should satisfy condition: " + v.getMessage())
            .toList();

        if (!violations.isEmpty()) {
          throw new ValidationException(String.join("\n", validationErrors));
        }

        return tutor;
      }
    }
  }
}
