package com.sergeymar4.servletapp2.controllers;



import com.sergeymar4.servletapp2.models.Course;
import com.sergeymar4.servletapp2.repositories.CourseRepository;
import com.sergeymar4.servletapp2.repositories.TeacherRepository;

import java.util.List;

public class CourseController {
    private CourseRepository courseRepository;
    private TeacherRepository teacherRepository;

    public CourseController() {
        this.courseRepository = new CourseRepository();
        this.teacherRepository = new TeacherRepository();
    }

    public Course getById(int id) {
        return courseRepository.getById(id);
    }

    public List<Course> getAll() {
        return courseRepository.getAll();
    }

    public void create(String title, int teacher_id) {
        Course course = new Course();
        course.setTitle(title);
        course.setTeacher(teacherRepository.getById(teacher_id));
        courseRepository.create(course);
    }

    public void update(int id, String title, int teacher_id) {
        Course course = courseRepository.getById(id);
        course.setTitle(title);
        course.setTeacher(teacherRepository.getById(teacher_id));
        courseRepository.update(course);
    }
    public void delete(int id) {
        courseRepository.delete(courseRepository.getById(id));
    }
}