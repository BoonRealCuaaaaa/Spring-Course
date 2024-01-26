package com.boonreal.cruddemo.rest;

import com.boonreal.cruddemo.entity.Employee;
import com.boonreal.cruddemo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // quick and dirty: inject employee dao (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // add mapping for GET /employee/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){

        Employee employee=employeeService.findById(employeeId);

        if(employee==null){
            throw new RuntimeException("Employee id not found - "+employeeId);
        }

        return employee;
    }

    // add mapping for POST /employee - add new employee
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);

        Employee dbEmployee=employeeService.save(employee);

        return dbEmployee;
    }

    // add mapping for PUT /employees - update existing employee
    @PostMapping("/employee")
    public Employee updateEmployee(@RequestBody Employee employee){

        Employee dbEmployee=employeeService.save(employee);

        return dbEmployee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee=employeeService.findById(employeeId);

        if(tempEmployee==null){
            throw new RuntimeException("Employee id not found");
        }

        employeeService.deleteById(employeeId);
        return "Deleted employe id - "+employeeId;
    }
}
