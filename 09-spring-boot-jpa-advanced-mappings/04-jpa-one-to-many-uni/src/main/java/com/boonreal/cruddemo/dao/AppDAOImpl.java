package com.boonreal.cruddemo.dao;

import com.boonreal.cruddemo.entity.Course;
import com.boonreal.cruddemo.entity.Instructor;
import com.boonreal.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager theEntityManager) {
        entityManager=theEntityManager;
    }
    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor=entityManager.find(Instructor.class,id);
        List<Course> courses=instructor.getCourses();
        for(Course temp:courses) {
            temp.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail tmp=entityManager.find(InstructorDetail.class,id);
        tmp.getInstructor().setInstructorDetail(null);
        entityManager.remove(tmp);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query=entityManager.createQuery("FROM Course WHERE instructor.id= :data",Course.class);
        query.setParameter("data",id);

        List<Course> courses=query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query=entityManager.createQuery(
                "SELECT i FROM Instructor i "
                +"JOIN FETCH i.courses "
                +"WHERE i.id=:data",Instructor.class
        );
        query.setParameter("data",id);

        Instructor instructor=query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    public void deleteCourseById(int id) {
        Course course=entityManager.find(Course.class,id);
        entityManager.remove(course);
    }

    @Override
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query=entityManager.createQuery(
                "SELECT c FROM Course c "
                + "JOIN FETCH c.reviews "
                + "WHERE c.id=:data",Course.class
        );
        query.setParameter("data",id);

        return query.getSingleResult();
    }
}
