package labs.lab2;

import labs.lab1.Student;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StudentService {
  private List<Student> students;

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public List<Student> sortByLastName() {
    return students.stream()
            .sorted(Comparator.comparing(Student::getLastName))
            .collect(Collectors.toList());
    }

  public List<Student> findByEmail(String email) {
    return students.stream()
            .filter(s -> s.getEmail().equals(email))
            .collect(Collectors.toList());
  }

  public List<Student> findByLevel(String level) {
    return students.stream()
            .filter(s -> s.getInitialLevel().equals(level))
            .collect(Collectors.toList());
  }
}
