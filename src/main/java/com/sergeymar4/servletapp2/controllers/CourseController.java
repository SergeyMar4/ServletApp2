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

    public void create(Course course) {
        course.setTeacher(teacherRepository.getById(course.getTeacher().getId()));
        courseRepository.create(course);
    }

    public void update(Course course) {
        Course oldCourse = courseRepository.getById(course.getId());

        if (course.getTitle() != null) {
            oldCourse.setTitle(course.getTitle());
        }
        if (course.getTeacher() != null) {
            oldCourse.setTeacher(teacherRepository.getById(course.getTeacher().getId()));
        }

        courseRepository.update(oldCourse);
    }

    public void delete(int id) {
        courseRepository.delete(courseRepository.getById(id));
    }
}