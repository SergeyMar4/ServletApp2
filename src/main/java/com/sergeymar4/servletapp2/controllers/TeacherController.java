package com.sergeymar4.servletapp2.controllers;



import com.sergeymar4.servletapp2.models.Teacher;
import com.sergeymar4.servletapp2.repositories.CourseRepository;
import com.sergeymar4.servletapp2.repositories.TeacherRepository;

import java.util.List;

public class TeacherController {
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;

    public TeacherController() {
        this.teacherRepository = new TeacherRepository();
        this.courseRepository = new CourseRepository();
    }
    public Teacher getById(int id) {
        return teacherRepository.getById(id);
    }

    public List<Teacher> getAll() {
        return teacherRepository.getAll();
    }

    public void addCourse(int teacher_id, int course_id) {
        teacherRepository.addCourse(teacherRepository.getById(teacher_id), courseRepository.getById(course_id));
    }

    public void create(String firstName, String lastName, int age) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setAge(age);
        teacherRepository.create(teacher);
    }

    public void update(int id, String firstName, String lastName, int age) {
        Teacher teacher = teacherRepository.getById(id);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setAge(age);
        teacherRepository.update(teacher);
    }

    public void delete(int id) {
        teacherRepository.delete(teacherRepository.getById(id));
    }
}
