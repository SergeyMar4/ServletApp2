package com.sergeymar4.servletapp2.repositories;


import com.sergeymar4.servletapp2.models.Course;
import com.sergeymar4.servletapp2.models.Teacher;
import com.sergeymar4.servletapp2.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TeacherRepository {

    public Teacher getById(int id) {
        Teacher teacher = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teacher = session.get(Teacher.class, id);
        }

        return teacher;
    }

    public List<Teacher> getAll() {
        List<Teacher> teachers = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teachers = session.createQuery("from Teacher").list();
        }

        return teachers;
    }

    public void addCourse(Teacher teacher, Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teacher.getCourses().add(course);
            Transaction transaction = session.beginTransaction();
            session.update(teacher);
            transaction.commit();
        }
    }

    public void create(Teacher teacher) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(teacher);
            transaction.commit();
        }
    }

    public void update(Teacher teacher) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(teacher);
            transaction.commit();
        }
    }

    public void delete(Teacher teacher) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(teacher);
            transaction.commit();
        }
    }
}