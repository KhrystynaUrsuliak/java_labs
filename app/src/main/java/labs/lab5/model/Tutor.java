package labs.lab5.model;

import java.util.Objects;
import java.time.LocalDate;

/**
 * The {@code Tutor} class represents a tutor who teaches at the academy.
 * 
 * @version 1.0
 * @since 2024-10-18
 */

public class Tutor {
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private Language language;
  private double salary;

  public Tutor(TutorBuilder builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.birthDate = builder.birthDate;
    this.language = builder.language;
    this.salary = builder.salary;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tutor tutor = (Tutor) o;
    return Double.compare(tutor.salary, salary) == 0 &&
                firstName.equals(tutor.firstName) &&
                lastName.equals(tutor.lastName) &&
                birthDate.equals(tutor.birthDate) &&
                language == tutor.language; 
    }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, birthDate, language, salary);
  }

  public static class TutorBuilder {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Language language;
    private double salary;

    public TutorBuilder(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
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
      return new Tutor(this);
    }
  }
}
