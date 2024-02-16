package com.boonreal.cruddemo.dao;

import com.boonreal.cruddemo.entity.Instructor;

public interface AppDAO {

    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
}
