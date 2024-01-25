package com.boonreal.cruddemo;

import java.util.List;

import com.boonreal.cruddemo.dao.StudentDAO;
import com.boonreal.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner ->{
			//Create student
			createStudent(studentDAO);

			//Read student by id
			//readStudent(studentDAO);

			//read all student
			//queryForStudent(studentDAO);

			//read student by last name
			//queryForStudentByLastName(studentDAO);

			//update student
			//updateStudent(studentDAO);

			//delete student
			//deleteStudent(studentDAO);

			//delete all student
			//deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {

		System.out.println("Deleting all students");
		int numRowDeleted=studentDAO.deleteAll();
		System.out.println("Deleted row count: "+numRowDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {

		int studentId=1;
		System.out.println("Deleting student id: "+studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {

		//retrieve student based on the id
		int studentId=1;
		System.out.println("Getting student with id: "+studentId);
		Student myStudent=studentDAO.findById(studentId);

		//change first name
		System.out.println("Updating student...");
		myStudent.setFirstName("Hoang Khanh");

		//update student
		studentDAO.update(myStudent);
		//display the student
		System.out.println(myStudent);
	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {

		//get a list of students
		List<Student> students=studentDAO.findByLastName("Cua");

		//display list of student
		for(Student student:students){
			System.out.println(student);
		}
	}

	private void queryForStudent(StudentDAO studentDAO) {

		// get a list of students
		List<Student> students=studentDAO.findAll();

		//display list of students
		for (Student student:students){
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		//create a student object
		System.out.println("Creating a new student object...");
		Student st=new Student("boonReal","cua","test2@test2.com");

		//save the student
		System.out.println("Saving the student");
		studentDAO.save(st);
		//display id of the saved student
		int theId=st.getId();
		System.out.println("Saved student. Generated id: "+theId);
		//retrieve student based on the id
		System.out.println("Retrieving student with id: "+theId);
		Student mySt=studentDAO.findById(theId);
		//display student
		System.out.println("Found the student: "+mySt);
	}

	private void createStudent(StudentDAO studentDAO) {

		//create the student object
		System.out.println("Creating a new student object....");
		Student tempStudent=new Student("Hoang","Nguyen","test@test.com");

		//save the student object
		System.out.println("Saving the student....");
		studentDAO.save(tempStudent);

		//display id of the saved student
		System.out.println("Saved student. Generated id: "+tempStudent.getId());
	}

}
