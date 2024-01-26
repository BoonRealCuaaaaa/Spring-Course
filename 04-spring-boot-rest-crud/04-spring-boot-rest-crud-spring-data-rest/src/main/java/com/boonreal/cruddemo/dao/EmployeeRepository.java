package com.boonreal.cruddemo.dao;

import com.boonreal.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path="member")
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
