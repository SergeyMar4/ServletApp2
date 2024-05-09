package com.sergeymar4.servletapp2.controllers;



import com.sergeymar4.servletapp2.models.Course;
import com.sergeymar4.servletapp2.models.SchoolClass;
import com.sergeymar4.servletapp2.models.Student;
import com.sergeymar4.servletapp2.repositories.CourseRepository;
import com.sergeymar4.servletapp2.repositories.SchoolClassRepository;
import com.sergeymar4.servletapp2.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class SchoolClassController {
    private SchoolClassRepository schoolClassRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public SchoolClassController() {
        this.schoolClassRepository = new SchoolClassRepository();
        this.studentRepository = new StudentRepository();
        this.courseRepository = new CourseRepository();
    }

    public SchoolClass getById(int id) {
        return schoolClassRepository.getById(id);
    }

    public List<SchoolClass> getAll() {
        return schoolClassRepository.getAll();
    }

    public List<Student> getStudentByFirstName(int class_id, String firstName) {
        return schoolClassRepository.getStudentByFirstName(class_id, firstName);
    }

    public List<Student> getAllStudent(int class_id) {
        return schoolClassRepository.getAllStudent(class_id);
    }

    public void addStudent(int school_id, int student_id) {
        schoolClassRepository.addStudent(schoolClassRepository.getById(school_id), studentRepository.getById(student_id));
    }

    public void addCourse(int school_id, int course_id) {
        schoolClassRepository.addCourse(schoolClassRepository.getById(school_id), courseRepository.getById(course_id));
    }

    public void create(SchoolClass schoolClass) {
        schoolClass.setCourses(schoolClass.getCourses());
        schoolClassRepository.create(schoolClass);
    }

    public void update(SchoolClass schoolClass) {
        SchoolClass oldSchoolClass = schoolClassRepository.getById(schoolClass.getId());

        if (schoolClass.getTitle() != null) {
            oldSchoolClass.setTitle(schoolClass.getTitle());
        }
        if (schoolClass.getStudents() != null) {

            for (Student student : schoolClass.getStudents()) {
                Student student1 = studentRepository.getById(student.getId());
                student1.setSchoolClass(schoolClassRepository.getById(schoolClass.getId()));
                studentRepository.update(student1);
            }
        }
        if (schoolClass.getCourses() != null) {
            List<Course> courses = new ArrayList<>();

            for (Course course : schoolClass.getCourses()) {
                courses.add(courseRepository.getById(course.getId()));
            }

            oldSchoolClass.setCourses(courses);
        }

        schoolClassRepository.update(oldSchoolClass);
    }

    public void delete(int id) {
        schoolClassRepository.delete(schoolClassRepository.getById(id));
    }
}
