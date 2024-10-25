package labs.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@code Payment} class.
 */
public class PaymentTest {

    private Payment payment;
    private Student student;
    private Course course;
    private Date paymentDate;

    @BeforeEach
    void setUp() {
        student = new Student("Alice", "Smith", "alice@example.com", "A1");
        course = new Course("English for Beginners", "English", "A1", LocalDate.of(2024, 1, 10), LocalDate.of(2024, 3, 20), 250.0);
        paymentDate = new Date(); 

        payment = new Payment(student, course, "October", paymentDate, 250.0);
    }

    @Test
    void testPaymentConstructorAndGetters() {
        assertEquals(student, payment.getStudent());
        assertEquals(course, payment.getCourse());
        assertEquals("October", payment.getPaidMonth());
        assertEquals(paymentDate, payment.getPaymentDate());
        assertEquals(250.0, payment.getAmount());
    }

    @Test
    void testSetters() {
        Student newStudent = new Student("Bob", "Johnson", "bob@example.com", "B1");
        Course newCourse = new Course("Advanced English", "English", "B1", LocalDate.of(2024, 4, 1), LocalDate.of(2024, 6, 1), 300.0);
        Date newPaymentDate = new Date(System.currentTimeMillis() - 100000); // Example of a new date

        // Test setters
        payment.setStudent(newStudent);
        assertEquals(newStudent, payment.getStudent());

        payment.setCourse(newCourse);
        assertEquals(newCourse, payment.getCourse());

        payment.setPaidMonth("November");
        assertEquals("November", payment.getPaidMonth());

        payment.setPaymentDate(newPaymentDate);
        assertEquals(newPaymentDate, payment.getPaymentDate());

        payment.setAmount(300.0);
        assertEquals(300.0, payment.getAmount());
    }

    @Test
    void testEqualsAndHashCode() {
        Payment payment2 = new Payment(student, course, "October", paymentDate, 250.0);
        assertEquals(payment, payment2);
        assertEquals(payment.hashCode(), payment2.hashCode());

        payment2.setAmount(300.0);
        assertNotEquals(payment, payment2);
        assertNotEquals(payment.hashCode(), payment2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Payment{student=" + student + ", course=" + course + ", paidMonth='October', paymentDate=" + paymentDate + ", amount=250.0}";
        assertEquals(expected, payment.toString());
    }
}
