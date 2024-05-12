package com.generation;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{

    public static void main( String[] args )
        throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option = 0;
        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, scanner );
                    break;
                case 4:
                    enrollStudentToCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
            }
        }
        while ( option != 7 );
    }

    private static void enrollStudentToCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println( course );
        courseService.enrollStudent( courseId, student );
        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );

    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }

    private static void gradeStudent( StudentService studentService, Scanner scanner )
    {
        // TODO required but was missing in the assessment notes
        // Use findStudent (StudentService)
        // If student is found, check against approvedCourses
        // Prompt user what course they want to grade (scanner)
        // Obtain course from courseService before grading, course exists within CourseService
        // take the courseCode from the course, and compare if the student is attending course.
        // if student.isAttendingCourse(courseCode) returns true (is found in approvedCourses)
        // prompt user to enter grade
        // else tell user course not found with the student
        // Prompt user(scanner) to enter grade
        // else tell user course not found with the student
        int grade = 0;

        System.out.println("Insert student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);

        // If student is found
        if (student != null) {
            System.out.println("Grading: " + student + "\n");

            // If student has enrolled into course(s)
            if (!student.getApprovedCourses().isEmpty()) {
                System.out.println("Course(s) enrolled:");
                for (Course course : student.getApprovedCourses()) {
                    System.out.println(course);
                }
                System.out.println("\nInsert course code to grade: ");
                String courseCode = scanner.next();
                CourseService courseService = new CourseService();
                Course course = courseService.getCourse(courseCode);

                // If student is attending the course to be graded
                if(student.isAttendingCourse(courseCode)){
                    do {
                        System.out.println("Please enter grade 0 - 100");
                        try {
                            grade = scanner.nextInt();
                        } catch (InputMismatchException e) { // To reject any input that is not an int
                            System.out.println("Invalid input. Please enter a valid grade between 0 and 100.");
                            scanner.nextLine(); // To clear the scanner buffer
                        }
                    } while (grade < 0 || grade > 100); // To reject any int input that is negative or > 100
                } else {
                    System.out.println("Student not found in this course");
                    return;
                }
            }
        }
    }

    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner )
        throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }
}
