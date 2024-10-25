package labs.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@code Timetable} class.
 */
public class TimetableTest {

    private Timetable timetable;
    private Group group;
    private Time time;

    @BeforeEach
    void setUp() {
        // Create mock data for Group
        Course course = new Course("English for Beginners", "English", "A1", LocalDate.of(2024, 1, 10), LocalDate.of(2024, 3, 20), 250.0);
        Tutor tutor = new Tutor.TutorBuilder("John", "Doe")
                .birthDate(LocalDate.of(1980, 5, 15))
                .qualification("Master of Linguistics")
                .salary(50.0)
                .build();
        Student student1 = new Student("Alice", "Smith", "alice@example.com", "A1");
        Student student2 = new Student("Bob", "Johnson", "bob@example.com", "A1");
        List<Student> students = Arrays.asList(student1, student2);
        group = new Group("Group A1", course, tutor, students);

        time = Time.valueOf(LocalTime.of(10, 30)); // 10:30 AM

        timetable = new Timetable(group, DayOfWeek.MONDAY, time, "Room 101");
    }

    @Test
    void testTimetableConstructorAndGetters() {
        assertEquals(group, timetable.getGroup());
        assertEquals(DayOfWeek.MONDAY, timetable.getDay());
        assertEquals(time, timetable.getTime());
        assertEquals("Room 101", timetable.getRoom());
    }

    @Test
    void testSetters() {
        Time newTime = Time.valueOf(LocalTime.of(14, 0)); // 2:00 PM
        Group newGroup = new Group("Group B1", group.getCourse(), group.getTutor(), group.getStudents());

        timetable.setGroup(newGroup);
        assertEquals(newGroup, timetable.getGroup());

        timetable.setDay(DayOfWeek.WEDNESDAY);
        assertEquals(DayOfWeek.WEDNESDAY, timetable.getDay());

        timetable.setTime(newTime);
        assertEquals(newTime, timetable.getTime());

        timetable.setRoom("Room 202");
        assertEquals("Room 202", timetable.getRoom());
    }

    @Test
    void testEqualsAndHashCode() {
        Timetable timetable2 = new Timetable(group, DayOfWeek.MONDAY, time, "Room 101");
        assertEquals(timetable, timetable2);
        assertEquals(timetable.hashCode(), timetable2.hashCode());

        timetable2.setRoom("Room 202");
        assertNotEquals(timetable, timetable2);
        assertNotEquals(timetable.hashCode(), timetable2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Timetable{group=" + group + ", day=MONDAY, time=10:30:00, room='Room 101'}";
        assertEquals(expected, timetable.toString());
    }
}
