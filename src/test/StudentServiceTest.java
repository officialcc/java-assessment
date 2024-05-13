package test;

import com.generation.model.Student;
import com.generation.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    StudentService studentService;

    // To create a new student
    Student student = new Student("0001", "Colin", "some@email.com", new Date(02/07/1977));

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void isSubscribed() {
        studentService.subscribeStudent(student);

        // Test that studentID "0001" isSubscribed is true - changing studentId to anything else will throw an error
        assertTrue(studentService.isSubscribed("0001"));

        // Test that studentID "0002" isSubscribed is false - changing studentId to "0001" will throw an error
        assertFalse(studentService.isSubscribed("0002"));
    }

    @Test
    void findStudent() {
        studentService.subscribeStudent(student);

        // Test that studentId "0001" will be found - changing studentId to anything else throws an error
        Student foundStudent = studentService.findStudent("0001");
        assertNotNull(foundStudent);

        // Test that Student name matches foundStudent studentId - changing name to anything else throws an error
        assertEquals("Colin", foundStudent.getName());
    }
}