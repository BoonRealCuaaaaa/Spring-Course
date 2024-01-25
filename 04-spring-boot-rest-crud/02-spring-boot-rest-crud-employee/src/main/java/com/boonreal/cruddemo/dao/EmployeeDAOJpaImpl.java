package com.boonreal.cruddemo.dao;

import com.boonreal.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    //define field for entitymanager
    private EntityManager entityManager;
    //set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManaget){
        this.entityManager=theEntityManaget;
    }
    @Override
    public List<Employee> findAll() {

        //create a query
        TypedQuery<Employee> query=entityManager.createQuery("FROM Employee ",Employee.class);
        //execute query and get result list
        List<Employee> employees=query.getResultList();
        //return the result
        return employees;
    }
}
