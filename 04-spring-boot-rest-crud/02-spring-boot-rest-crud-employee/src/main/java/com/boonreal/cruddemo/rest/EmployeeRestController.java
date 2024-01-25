package com.boonreal.cruddemo.rest;

import com.boonreal.cruddemo.dao.EmployeeDAO;
import com.boonreal.cruddemo.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeDAO employeeDAO;
    //inject employee dao
    public EmployeeRestController(EmployeeDAO theEmployeeDAO){
        employeeDAO=theEmployeeDAO;
    }
    //expose "/employees" - return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeDAO.findALl();
    }
}
