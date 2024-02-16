package com.boonreal.cruddemo;

import com.boonreal.cruddemo.dao.AppDAO;
import com.boonreal.cruddemo.entity.Course;
import com.boonreal.cruddemo.entity.Instructor;
import com.boonreal.cruddemo.entity.InstructorDetail;
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
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
//			createInstructorWithCourse(appDAO);
//			findInstructorWithCourse(appDAO);
//			findCourseForInstructor(appDAO);
//			findInstructorWithCourseJoinFetch(appDAO);
//			updateInstructor(appDAO);
//			updateCourse(appDAO);
//			deleteInstructor(appDAO);
			deleteCourse(appDAO);
		};

	}

	private void deleteCourse(AppDAO appDAO) {
		appDAO.deleteCourseById(1);
	}

	private void updateCourse(AppDAO appDAO) {
		Course course=appDAO.findCourseById(1);
		course.setTitle("Lol Pro tip");
		appDAO.update(course);
	}

	private void updateInstructor(AppDAO appDAO) {

		Instructor instructor=appDAO.findInstructorById(1);

		System.out.println("Updating");
		instructor.setLastName("BoonCa");
		appDAO.update(instructor);


	}

	private void findInstructorWithCourseJoinFetch(AppDAO appDAO) {
		Instructor instructor=appDAO.findInstructorByIdJoinFetch(1);

		System.out.println(instructor);
		System.out.println(instructor.getCourses());
	}

	private void findCourseForInstructor(AppDAO appDAO) {

		int id=1;
		Instructor tempInstructor=appDAO.findInstructorById(id);

		List<Course> courses=appDAO.findCoursesByInstructorId(id);
		tempInstructor.setCourses(courses);
		System.out.println(tempInstructor.getCourses());
	}

	private void findInstructorWithCourse(AppDAO appDAO) {

		int id=1;
		Instructor tempInstructor=appDAO.findInstructorById(id);

		System.out.println(tempInstructor);
		System.out.println("the associatecd courses: "+tempInstructor.getCourses());
	}

	private void createInstructorWithCourse(AppDAO appDAO) {
		Instructor tempInstructor=new Instructor("Boon","RealCua","test@test");
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://boonreal/youtube","testing");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create courses
		Course tempCourse1=new Course("Pro tips for LoL");
		Course tempCourse2=new Course("Air Guiltar");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		//save to db
		appDAO.save(tempInstructor);

	}

	private void findInstructorDetail(AppDAO appDAO) {
		InstructorDetail tmp=appDAO.findInstructorDetailById(1);
		System.out.println(tmp);
		System.out.println(tmp.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id=1;
		appDAO.deleteInstructorById(id);
	}

	private void findInstructor(AppDAO appDAO) {

		int id=1;
		System.out.println("Finding instructor id "+id);
		Instructor tmpInstructor=appDAO.findInstructorById(id);

		System.out.println(tmpInstructor);
		System.out.println(tmpInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		Instructor tempInstructor=new Instructor("chad","Darby","test@test");
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://boonreal/youtube","testing");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		System.out.println("Saving instructor "+tempInstructor);
		appDAO.save(tempInstructor);


	}
}
