package com.boonreal.cruddemo.dao;

import com.boonreal.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // define field for entitymanager
    private EntityManager entityManager;


    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // execute query and get result list

        // return the results
        return theQuery.getResultList();
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class,id);
    }

    @Override
    public Employee save(Employee employee) {
        //entityManager.merge: if id==0 -> add else update
        Employee dbEmployee=entityManager.merge(employee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {

        //find employee by id
        Employee employee=entityManager.find(Employee.class,id);
        //remove employee
        entityManager.remove(employee);

    }
}
