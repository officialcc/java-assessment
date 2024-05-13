package test;

import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.service.CourseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    CourseService courseService;

    @BeforeEach
    void setUp() {
        courseService = new CourseService();
    }

    @Test
    void getCourse() {
        // Test that course code is recognised as correct - changing code to anything else will throw an error
        assertNotNull(courseService.getCourse( "INTRO-CS-1"));
        // Test that course code is recognised as incorrect - changing code to "INTRO-CS-1" will throw an error
        assertNull(courseService.getCourse( "INTRO-CS"));
    }

    @Test
    void registerCourse() {
        // To register a new course
        Course course = new Course("TEST-COURSE", "Test Course", 8, new Module("TEST-MODULE", "Test Module", "Test Module Description") );

        courseService.registerCourse(course);

        // Test that new course was registered successfully and equal to the new course created - changing code to anything else will throw an error
        assertNotNull(courseService.getCourse("TEST-COURSE"));
        assertEquals(course, courseService.getCourse("TEST-COURSE"));
    }


}