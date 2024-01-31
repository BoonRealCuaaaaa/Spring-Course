package com.boonreal.springboot.thymeleafdemo.controller;

import com.boonreal.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${systems}")
    private List<String> systems;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {

        // create a new student object
        Student student=new Student();

        // add student object to the model
        theModel.addAttribute("student",student);

        // add the list of countries to the model
        theModel.addAttribute("countries",countries);

        // add the list of languages to the model
        theModel.addAttribute("languages",languages);

        // add the list of OS to the model
        theModel.addAttribute("systems",systems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student student) {

        //log the input data
        System.out.println("the Student: "+ student.getFirstName()+" "+student.getLastName());
        return "student-confirmation";
    }
}
