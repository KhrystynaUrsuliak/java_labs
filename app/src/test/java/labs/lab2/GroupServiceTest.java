package labs.lab2;

import labs.lab1.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class GroupServiceTest {
    private GroupService groupService;
    private Group group1;
    private Group group2;
    private Group group3;

    @BeforeEach
    public void setUp() {
        groupService = new GroupService();

        Course beginnerCourse = new Course("Spanish Basics", Language.SPANISH, "Beginner", LocalDate.of(2024, 1, 10), LocalDate.of(2024, 4, 10), 200.0);
        Course intermediateCourse = new Course("English Intermediate", Language.ENGLISH, "Intermediate", LocalDate.of(2024, 2, 15), LocalDate.of(2024, 5, 15), 300.0);

        Tutor tutor1 = new Tutor.TutorBuilder("John", "Doe").birthDate(LocalDate.of(1980, 6, 15)).language(Language.ENGLISH).salary(50000).build();
        Tutor tutor2 = new Tutor.TutorBuilder("Alice", "Smith").birthDate(LocalDate.of(1975, 3, 20)).language(Language.SPANISH).salary(55000).build();

        Student student1 = new Student("Jack", "Johnson", "jack@example.com", "A2");
        Student student2 = new Student("Emily", "Davis", "emily@example.com", "B1");
        Student student3 = new Student("Michael", "Brown", "michael@example.com", "A1");

        group1 = new Group("Group A", beginnerCourse, tutor1, Arrays.asList(student1, student2));
        group2 = new Group("Group B", intermediateCourse, tutor2, Arrays.asList(student1, student2, student3));
        group3 = new Group("Group C", beginnerCourse, tutor1, Arrays.asList(student1));

        groupService.setGroups(Arrays.asList(group1, group2, group3));
    }

    @Test
    public void testFilterByCourseLevel() {
        List<Group> beginnerGroups = groupService.filterByCourseLevel("Beginner");
        assertEquals(2, beginnerGroups.size());
        assertTrue(beginnerGroups.contains(group1));
        assertTrue(beginnerGroups.contains(group3));
    }

    @Test
    public void testSortByTutorName() {
        List<Group> sortedGroups = groupService.sortByTutorName(groupService.getGroups());
        assertEquals("Doe", sortedGroups.get(0).getTutor().getLastName());
        assertEquals("Smith", sortedGroups.get(1).getTutor().getLastName());
    }

    @Test
    public void testFilterByStudentCount() {
        List<Group> largeGroups = groupService.filterByStudentCount(3);
        assertEquals(1, largeGroups.size());
        assertTrue(largeGroups.contains(group2));
    }
}
