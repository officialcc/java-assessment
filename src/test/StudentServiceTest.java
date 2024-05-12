package test;

import com.generation.model.Student;
import com.generation.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    StudentService studentService;

    Student student = new Student("0001", "Colin", "some@email.com", new Date(02/07/1977));

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void subscribeStudent() {
        studentService.subscribeStudent(student);

        assertTrue(studentService.isSubscribed("0001"));
    }

    @Test
    void isSubscribed() {
        studentService.subscribeStudent(student);

        assertTrue(studentService.isSubscribed("0001"));
    }

    @Test
    void isSubscribedNotEquals() {
        assertFalse(studentService.isSubscribed("0002"));
    }
}