package labs.lab1;

import java.util.Objects;
import java.time.LocalDate;

/**
 * The {@code Tutor} class represents a tutor who teaches at the academy.
 */

public class Tutor {
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private String qualification;
  private double salary;

  public Tutor(TutorBuilder builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.birthDate = builder.birthDate;
    this.qualification = builder.qualification;
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

  public String getQualification() {
    return qualification;
  }

  public void setQualification(String qualification) {
    this.qualification = qualification;
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
    ", qualification='" + qualification + '\'' +
    ", salary=" + salary +
    '}';
  } 

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tutor tutor = (Tutor) o;
    return Double.compare(tutor.salary, salary) == 0 &&
    Objects.equals(firstName, tutor.firstName) &&
    Objects.equals(lastName, tutor.lastName) &&
    Objects.equals(birthDate, tutor.birthDate) &&
    Objects.equals(qualification, tutor.qualification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, birthDate, qualification, salary);
  }

  public static class TutorBuilder {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String qualification;
    private double salary;

    public TutorBuilder(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public TutorBuilder birthDate(LocalDate birthDate) {
      this.birthDate = birthDate;
      return this;
    }

    public TutorBuilder qualification(String qualification) {
      this.qualification = qualification;
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
