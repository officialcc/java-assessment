package test;

import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.service.CourseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Nested
class CourseServiceTest {

    CourseService courseService = new CourseService();

    @BeforeEach
    public void init() {
        // Dummy module and courses
        Module module = new Module("TEST-MODULE", "Test Module", "Test description");
        courseService.registerCourse(new Course("TEST-COURSE-1", "Test Course 1", 6, module));
        courseService.registerCourse(new Course("TEST-COURSE-2", "Test Course 2", 6, module));
    }

    @Test
    void registerCourse() {
        // Assuming you have added some courses in the setup
        // Register a new course
        Module newModule = new Module("NEW-MODULE", "New Module", "New description");
        Course newCourse = new Course("NEW-COURSE", "New Course", 8, newModule);
        courseService.registerCourse(newCourse);

        // Test that course was registered successfully
        Course retrievedCourse = courseService.getCourse("NEW-COURSE");
        assertNotNull(retrievedCourse);
        assertEquals("New Course", retrievedCourse.getName());
        assertEquals(8, retrievedCourse.getCredits());
        assertEquals("New Module", retrievedCourse.getModule().getName());
    }

    @Test
    void getCourse() {
        // Test that course code is recognised as correct
        assertNotNull(courseService.getCourse( "INTRO-CS-1" ));
        // Test that course code is recognised as incorrect
        assertNull(courseService.getCourse( "CS-1"));
    }
}