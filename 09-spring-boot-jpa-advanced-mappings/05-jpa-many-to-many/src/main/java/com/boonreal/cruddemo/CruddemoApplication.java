package com.boonreal.cruddemo;

import com.boonreal.cruddemo.dao.AppDAO;
import com.boonreal.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {
//			createCourseAndStudents(appDAO);
//			findCourseAndStudents(appDAO);
//          findStudentAndCourses(appDAO);
//          addMoreCoursesForStudent(appDAO);
//          deleteCourse(appDAO);
            deleteStudent(appDAO);
        };

    }

    private void deleteStudent(AppDAO appDAO) {
        appDAO.deleteStudentById(1);
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        Student student=appDAO.findStudentAndCoursesByStudentId(2);

        Course course1=new Course("Rubik's Cube - How to Speed Cube");
        Course course2=new Course("Atari 2600 - Game Development");

        student.addCourse(course1);
        student.addCourse(course2);

        appDAO.update(student);

    }

    private void findStudentAndCourses(AppDAO appDAO) {
        Student student = appDAO.findStudentAndCoursesByStudentId(2);

        System.out.println(student);
        System.out.println(student.getCourses());
    }

    private void findCourseAndStudents(AppDAO appDAO) {
        int id = 10;
        Course course = appDAO.findCourseAndStudentsByCourseId(id);

        System.out.println(course);
        System.out.println(course.getStudents());
    }

    private void createCourseAndStudents(AppDAO appDAO) {

        Course course = new Course("Pacman - How TO Score One Million Points");

        Student student1 = new Student("Boon", "Real", "test@test.com");
        Student student2 = new Student("Khanh", "Hoang", "test@test.com");

        course.addStudent(student1);
        course.addStudent(student2);

        appDAO.save(course);

    }

    private void deleteCourseAndRevews(AppDAO appDAO) {
        appDAO.deleteCourseById(10);
    }

    private void retrieveCourseAndReview(AppDAO appDAO) {
        Course course = appDAO.findCourseAndReviewsByCourseId(10);

        System.out.println(course);
        System.out.println(course.getReviews());
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course course = new Course("Pacman - How to Score One Million Points");

        course.addReview(new Review("Great course... loved it"));
        course.addReview(new Review("Cool course... loved it"));
        course.addReview(new Review("What a dumb course"));

        appDAO.save(course);

    }

    private void deleteCourse(AppDAO appDAO) {
        appDAO.deleteCourseById(1);
    }

    private void updateCourse(AppDAO appDAO) {
        Course course = appDAO.findCourseById(1);
        course.setTitle("Lol Pro tip");
        appDAO.update(course);
    }

    private void updateInstructor(AppDAO appDAO) {

        Instructor instructor = appDAO.findInstructorById(1);

        System.out.println("Updating");
        instructor.setLastName("BoonCa");
        appDAO.update(instructor);


    }

    private void findInstructorWithCourseJoinFetch(AppDAO appDAO) {
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(1);

        System.out.println(instructor);
        System.out.println(instructor.getCourses());
    }

    private void findCourseForInstructor(AppDAO appDAO) {

        int id = 1;
        Instructor tempInstructor = appDAO.findInstructorById(id);

        List<Course> courses = appDAO.findCoursesByInstructorId(id);
        tempInstructor.setCourses(courses);
        System.out.println(tempInstructor.getCourses());
    }

    private void findInstructorWithCourse(AppDAO appDAO) {

        int id = 1;
        Instructor tempInstructor = appDAO.findInstructorById(id);

        System.out.println(tempInstructor);
        System.out.println("the associatecd courses: " + tempInstructor.getCourses());
    }

    private void createInstructorWithCourse(AppDAO appDAO) {
        Instructor tempInstructor = new Instructor("Boon", "RealCua", "test@test");
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://boonreal/youtube", "testing");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create courses
        Course tempCourse1 = new Course("Pro tips for LoL");
        Course tempCourse2 = new Course("Air Guiltar");

        // add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        //save to db
        appDAO.save(tempInstructor);

    }

    private void findInstructorDetail(AppDAO appDAO) {
        InstructorDetail tmp = appDAO.findInstructorDetailById(1);
        System.out.println(tmp);
        System.out.println(tmp.getInstructor());
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 1;
        appDAO.deleteInstructorById(id);
    }

    private void findInstructor(AppDAO appDAO) {

        int id = 1;
        System.out.println("Finding instructor id " + id);
        Instructor tmpInstructor = appDAO.findInstructorById(id);

        System.out.println(tmpInstructor);
        System.out.println(tmpInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {

        Instructor tempInstructor = new Instructor("chad", "Darby", "test@test");
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://boonreal/youtube", "testing");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //save the instructor
        System.out.println("Saving instructor " + tempInstructor);
        appDAO.save(tempInstructor);


    }
}
