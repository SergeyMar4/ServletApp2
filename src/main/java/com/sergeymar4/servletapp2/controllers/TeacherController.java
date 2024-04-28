package com.sergeymar4.servletapp2.controllers;



import com.sergeymar4.servletapp2.models.Course;
import com.sergeymar4.servletapp2.models.Teacher;
import com.sergeymar4.servletapp2.repositories.CourseRepository;
import com.sergeymar4.servletapp2.repositories.TeacherRepository;

import java.util.ArrayList;
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

    public void create(Teacher teacher) {
        teacher.setCourses(teacher.getCourses());
        teacherRepository.create(teacher);
    }

    public void update(Teacher teacher) {
        Teacher oldTeacher = teacherRepository.getById(teacher.getId());

        if (teacher.getFirstName() != null) {
            oldTeacher.setFirstName(teacher.getFirstName());
        }
        if (teacher.getLastName() != null) {
            oldTeacher.setLastName(teacher.getLastName());
        }
        if (teacher.getAge() != 0) {
            oldTeacher.setAge(teacher.getAge());
        }
        if (teacher.getCourses() != null) {

            for (Course course : teacher.getCourses()) {
                Course course1 = courseRepository.getById(course.getId());
                course1.setTeacher(teacherRepository.getById(teacher.getId()));
                courseRepository.update(course1);
            }
        }

        teacherRepository.update(oldTeacher);
    }

    public void delete(int id) {
        teacherRepository.delete(teacherRepository.getById(id));
    }
}
