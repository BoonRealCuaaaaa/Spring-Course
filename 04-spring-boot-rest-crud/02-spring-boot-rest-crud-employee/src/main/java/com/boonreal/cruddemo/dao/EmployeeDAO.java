package com.boonreal.cruddemo.dao;

import com.boonreal.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
}
