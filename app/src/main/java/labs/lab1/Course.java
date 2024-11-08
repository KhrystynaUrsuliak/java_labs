package labs.lab1;

import java.util.Objects;
import java.time.LocalDate;

/**
 * The {@code Course} class represents a language course offered by the language academy. 
 * It contains details such as course name, language, level, start and end dates, and price.
 * 
 * @version 1.0
 * @since 2024-10-18

 */

public class Course implements Comparable<Course>{
  private String name;
  private Language language;
  private String level;
  private LocalDate startDate;
  private LocalDate endDate;
  private double price;

  public Course(String name, Language language, String level, LocalDate startDate, LocalDate endDate, double price) {
    this.name = name;
    this.language = language;
    this.level = level;
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Course{" +
            "name='" + name + '\'' +
            ", language=" + language +
            ", level='" + level + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", price=" + price +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Course course = (Course) o;
    return Double.compare(course.price, price) == 0 &&
            Objects.equals(name, course.name) &&
            language == course.language && 
            Objects.equals(level, course.level) &&
            Objects.equals(startDate, course.startDate) &&
            Objects.equals(endDate, course.endDate);
  }

  @Override
    public int hashCode() {
        return Objects.hash(name, language, level, startDate, endDate, price);
    }

    @Override
    public int compareTo(Course other) {
      String thisLevelPrefix = this.level.substring(0, 1); // "A", "B", etc.
      String otherLevelPrefix = other.level.substring(0, 1);

      int levelPrefixComparison = thisLevelPrefix.compareTo(otherLevelPrefix);
      if (levelPrefixComparison != 0) {
        return levelPrefixComparison;
      }

      int thisLevelNumber = Integer.parseInt(this.level.substring(1));
      int otherLevelNumber = Integer.parseInt(other.level.substring(1));

      int levelNumberComparison = Integer.compare(thisLevelNumber, otherLevelNumber);
      if (levelNumberComparison != 0) {
          return levelNumberComparison;
      }

      int nameComparison = this.name.compareTo(other.name);
      if (nameComparison != 0) {
          return nameComparison;
      }

      return Double.compare(this.price, other.price);
  }
}
