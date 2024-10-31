package labs.lab2;

import labs.lab1.Course;
import labs.lab1.Language;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CourseService {
  public List<Course> courses; 

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  public List<Course> sortByPrice(boolean ascending) {
    return courses.stream()
            .sorted(ascending ? Comparator.comparing(Course::getPrice)
                              : Comparator.comparing(Course::getPrice).reversed())
            .collect(Collectors.toList());
  }

  public List<Course> filterByLanguage(Language language) {
    return courses.stream()
            .filter(c -> c.getLanguage().equals(language))
            .collect(Collectors.toList());
  }

  public List<Course> findEnglishCourses(Language language) {
    return courses.stream()
            .filter(c -> c.getLanguage() == Language.ENGLISH)
            .collect(Collectors.toList());
  } 
}
