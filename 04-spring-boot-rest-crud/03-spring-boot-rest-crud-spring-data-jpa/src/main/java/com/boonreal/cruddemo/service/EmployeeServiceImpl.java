package com.boonreal.cruddemo.service;

import com.boonreal.cruddemo.dao.EmployeeRepository;
import com.boonreal.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {

        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {

        Optional<Employee> result = employeeRepository.findById(id);
        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Did not find employee id - "+id);
        }

        return theEmployee;
    }

    @Override
    public Employee save(Employee employee) {

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {

        employeeRepository.deleteById(id);
    }
}
