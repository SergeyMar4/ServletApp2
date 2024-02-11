package com.sergeymar4.servletapp2.repositories;


import com.sergeymar4.servletapp2.models.Mark;
import com.sergeymar4.servletapp2.models.Student;
import com.sergeymar4.servletapp2.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepository {

    public Student getById(int id) {
        Student student = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            student = session.get(Student.class, id);
        }

        return student;
    }

    public List<Student> getAll() {
        List<Student> students = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            students = session.createQuery("from Student").list();
        }

        return students;
    }

    public void create(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }
    }

    public void update(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        }
    }

    public void delete(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        }
    }

    public List<Mark> getAllMarksByCourse(int student_id, int course_id, int quarter) {
        List<Mark> marks = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Mark where student_id=:student_id and course_id=:course_id and quarter=:quarter");
            query.setParameter("student_id", student_id);
            query.setParameter("course_id", course_id);
            query.setParameter("quarter", quarter);
            marks = query.list();
        }

        return marks;
    }
}