package labs.lab2;

import labs.lab1.Tutor;
import labs.lab1.Language;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Optional;


public class TutorService {
  private List<Tutor> tutors;

  public List<Tutor> getTutors() {
    return tutors;
  }

  public void setTutors(List<Tutor> tutors) {
    this.tutors = tutors;
  }

  public List<Tutor> sortBySalary(boolean descending) {
    return tutors.stream()
            .sorted(descending ? Comparator.comparing(Tutor::getSalary).reversed()
                              : Comparator.comparing(Tutor::getSalary))
            .collect(Collectors.toList());
  }

  public List<Tutor> findByLanguage(Language language) {
    return tutors.stream()
            .filter(t -> t.getLanguage().equals(language))
            .collect(Collectors.toList());
  }

  public Optional<Tutor> sortByAge () {
    return tutors.stream()
            .min(Comparator.comparing(Tutor::getBirthDate));
  }
}
