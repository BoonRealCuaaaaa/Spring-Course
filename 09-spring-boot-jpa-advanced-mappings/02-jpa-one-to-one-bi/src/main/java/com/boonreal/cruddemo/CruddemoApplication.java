package com.boonreal.cruddemo;

import com.boonreal.cruddemo.dao.AppDAO;
import com.boonreal.cruddemo.entity.Instructor;
import com.boonreal.cruddemo.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
			findInstructorDetail(appDAO);
		};
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
