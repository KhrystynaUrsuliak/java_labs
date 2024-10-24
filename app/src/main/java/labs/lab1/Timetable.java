package labs.lab1;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Objects;

/**
 * The {@code Timetable} class represents the schedule of classes for a particular group.
 * It contains the group, day of the week, time and room.
 * 
 * @version 1.0
 * @since 2024-10-18
*/
public class Timetable {
  private Group group;
  private DayOfWeek day;
  private Time time;
  private String room;

  public Timetable(Group group, DayOfWeek day, Time time, String room) {
    this.group = group;
    this.day = day;
    this.time = time;
    this.room = room;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public DayOfWeek getDay() {
    return day;
  }

  public void setDay(DayOfWeek day) {
    this.day = day;
  }

  public Time getTime() {
    return time;
  }

  public void setTime(Time time) {
    this.time = time;
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  @Override
  public String toString() {
    return "Timetable{" +
    "group=" + group +
    ", day=" + day +
    ", time=" + time +
    ", room='" + room + '\'' +
    '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Timetable timetable = (Timetable) o;
    return group.equals(timetable.group) && day == timetable.day && time.equals(timetable.time) && room.equals(timetable.room);
  }

  @Override
  public int hashCode() {
    return Objects.hash(group, day, time, room);
  }
}
