package labs.lab1;

import java.util.Objects;

/**
 * The {@code Student} class represents a student who is enrolled in a course. 
 * It contains personal details, contact information and the initial language level.
 * 
 * @version 1.0
 * @since 2024-10-18
 */

public class Student {
  private String firstName;
  private String lastName;
  private String email;
  private String initialLevel;

  public Student(String firstName, String lastName, String email, String initialLevel) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.initialLevel = initialLevel;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getInitialLevel() {
    return initialLevel;
  }

  public void setInitialLevel(String initialLevel) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return firstName.equals(student.firstName) && lastName.equals(student.lastName) && email.equals(student.email) && initialLevel.equals(student.initialLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, email, initialLevel);
  }
}
