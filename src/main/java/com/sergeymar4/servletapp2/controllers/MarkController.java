package com.sergeymar4.servletapp2.controllers;


import com.sergeymar4.servletapp2.models.Mark;
import com.sergeymar4.servletapp2.repositories.CourseRepository;
import com.sergeymar4.servletapp2.repositories.MarkRepository;
import com.sergeymar4.servletapp2.repositories.StudentRepository;

public class MarkController {
    private MarkRepository markRepository;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    public MarkController() {
        this.markRepository = new MarkRepository();
        this.courseRepository = new CourseRepository();
        this.studentRepository = new StudentRepository();
    }

    public Mark getById(int id) {
        return markRepository.getById(id);
    }

    public void create(int course_id, int student_id, int mark1, int quarter) {
        Mark mark = new Mark();
        mark.setMark(mark1);
        mark.setCourse(courseRepository.getById(course_id));
        mark.setStudent(studentRepository.getById(student_id));
        mark.setQuarter(quarter);
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