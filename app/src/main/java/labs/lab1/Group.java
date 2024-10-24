package labs.lab1;

import java.util.Objects;

/**
 * The {@code Group} class represents a group of students for a specific course, taught by a particular tutor.
 * 
 * @version 1.0
 * @since 2024-10-18
 */

public class Group {
  private String name;
  private Course course;
  private Tutor tutor;

  public Group(String name, Course course, Tutor tutor) {
    this.name = name;
    this.course = course;
    this.tutor = tutor;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Tutor getTutor() {
    return tutor;
  }

  public void setTutor(Tutor tutor) {
    this.tutor = tutor;
  }

  @Override
  public String toString() {
    return "Group{" +
    "name='" + name + '\'' +
    ", course=" + course +
    ", tutor=" + tutor +
    '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Group group = (Group) o;
    return Objects.equals(name, group.name) && Objects.equals(course, group.course) && Objects.equals(tutor, group.tutor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, course, tutor);
  }
}
