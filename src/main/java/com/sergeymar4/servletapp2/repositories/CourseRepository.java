package com.sergeymar4.servletapp2.repositories;


import com.sergeymar4.servletapp2.models.Course;
import com.sergeymar4.servletapp2.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CourseRepository {

    public Course getById(int id) {
        Course course = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            course = session.get(Course.class, id);
        }

        return course;
    }

    public List<Course> getAll() {
        List<Course> courses = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            courses = session.createQuery("from Course").list();
        }

        return courses;
    }

    public void create(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        }
    }

    public void update(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        }
    }

    public void delete(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(course);
            transaction.commit();
        }
    }
}