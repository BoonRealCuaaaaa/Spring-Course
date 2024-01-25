package com.boonreal.demo.rest;

import com.boonreal.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    //define @PostConstruct to load the student data
    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();

        students.add(new Student("Boon", "Real"));
        students.add(new Student("Boon", "Cha"));
        students.add(new Student("Hoang", "Nguyen"));
    }

    //define endpoint for "/student" - return a list of student
    @GetMapping("/students")
    public List<Student> getStudents() {

        return students;
    }

    //define endpoint for "/student/{studentId}" - return student at index
    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        //Check the valid student id
        if((studentId >=students.size())||(studentId<0)) {
            throw new StudentNotFoundException("Student id not found - "+studentId);
        }

        return students.get(studentId);
    }


    //Add an exception handler using @ExceptionHandler


}
