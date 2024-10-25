package labs.lab1;

import java.util.Objects;
import java.util.Date;

/**
 * The {@code Payment} class represents a payment made by a student for a specific course.
 * It contains details about the student, course, payment date, and amount.
 * 
 * @version 1.0
 * @since 2024-10-18
 */

public class Payment {
  private Student student;
  private Course course;
  private String paidMonth;
  private Date paymentDate;
  private double amount;

  public Payment(Student student, Course course, String paidMonth, Date paymentDate, double amount) {
    this.student = student;
    this.course = course;
    this.paidMonth = paidMonth;
    this.paymentDate = paymentDate;
    this.amount = amount;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public String getPaidMonth() {
    return paidMonth;
  }

  public void setPaidMonth(String paidMonth) {
    this.paidMonth = paidMonth;
  }

  public Date getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "Payment{" +
    "student=" + student +
    ", course=" + course +
    ", paidMonth='" + paidMonth + '\'' +
    ", paymentDate=" + paymentDate +
    ", amount=" + amount +
    '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Payment payment = (Payment) o;
    return Double.compare(payment.amount, amount) == 0 && student.equals(payment.student) && course.equals(payment.course) && paidMonth.equals(payment.paidMonth) && paymentDate.equals(payment.paymentDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(student, course, paidMonth, paymentDate, amount);
  }
}
