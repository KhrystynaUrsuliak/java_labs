package labs.lab4;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
public class Group {

  @NotNull(message = "Group name cannot be null!")
  @Size(min = 1, max = 50, message = "Group name must be between 1 and 50 characters.")
  private String name;

  @NotNull(message = "Course cannot be null!")
  private Course course;

  @NotNull(message = "Tutor cannot be null!")
  private Tutor tutor;

  @NotNull(message = "Students list cannot be null!")
  @Size(min = 1, message = "Group must have at least one student.")
  private List<Student> students;

  private Group(String name, Course course, Tutor tutor, List<Student> students) {
    this.name = name;
    this.course = course;
    this.tutor = tutor;
    this.students = students;
  }

  @Override
  public String toString() {
    return "Group{" +
            "name='" + name + '\'' +
            ", course=" + course +
            ", tutor=" + tutor +
            ", students=" + students +
            '}';
  }

  public static GroupBuilder builder() {
    return new GroupBuilder();
  }

  public static class GroupBuilder {
    private String name;
    private Course course;
    private Tutor tutor;
    private List<Student> students;

    public GroupBuilder name(String name) {
      this.name = name;
      return this;
    }

    public GroupBuilder course(Course course) {
      this.course = course;
      return this;
    }

    public GroupBuilder tutor(Tutor tutor) {
      this.tutor = tutor;
      return this;
    }

    public GroupBuilder students(List<Student> students) {
      this.students = students;
      return this;
    }

    public Group build() {
      Group group = new Group(this.name, this.course, this.tutor, this.students);

      try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        List<String> validationErrors = violations.stream()
            .map(v -> "Validation error in " + v.getPropertyPath() + ", value `" + v.getInvalidValue() +
                       "` should satisfy condition: " + v.getMessage())
            .collect(Collectors.toList());

        if (!violations.isEmpty()) {
          throw new ValidationException(String.join("\n", validationErrors));
        }

        return group;
      }
    }
  }
}
