package com.sergeymar4.servletapp2.controllers;


import com.sergeymar4.servletapp2.models.Mark;
import com.sergeymar4.servletapp2.repositories.CourseRepository;
import com.sergeymar4.servletapp2.repositories.MarkRepository;
import com.sergeymar4.servletapp2.repositories.StudentRepository;
import com.sergeymar4.servletapp2.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;

public class MarkController {
    private MarkRepository markRepository;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    public MarkController() {
        this.markRepository = new MarkRepository();
        this.courseRepository = new CourseRepository();
        this.studentRepository = new StudentRepository();
    }

    public List<Mark> getAll() {
        return markRepository.getAll();
    }

    public Mark getById(int id) {
        return markRepository.getById(id);
    }

    public void create(Mark mark) {
        mark.setCourse(courseRepository.getById(mark.getCourse().getId()));
        mark.setStudent(studentRepository.getById(mark.getStudent().getId()));
        markRepository.create(mark);
    }

    public void update(int id, int course_id, int student_id, int mark1) {
        Mark mark = markRepository.getById(id);
        mark.setMark(mark1);
        mark.setStudent(studentRepository.getById(student_id));
        mark.setCourse(courseRepository.getById(course_id));
        markRepository.update(mark);
    }

    public void delete(int id) {
        markRepository.delete(markRepository.getById(id));
    }
}