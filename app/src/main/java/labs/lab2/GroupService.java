package labs.lab2;

import labs.lab1.Group;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;


public class GroupService {
  public List<Group> groups;

  public List<Group> getGroups() {
    return groups;
  }

  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }

  public List<Group> filterByCourseLevel(String level) {
    return groups.stream()
            .filter(g -> g.getCourse().getLevel().equals(level))
            .collect(Collectors.toList());
  }

  public List<Group> sortByTutorName(List<Group> groups) {
    return groups.stream()
            .sorted(Comparator.comparing(g -> g.getTutor().getLastName()))
            .collect(Collectors.toList());
  }

  public List<Group> filterByStudentCount(int count) {
    return groups.stream()
            .filter(g -> g.getStudents().size() >= count)
            .collect(Collectors.toList());
  }
}
