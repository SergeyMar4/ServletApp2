package com.sergeymar4.servletapp2.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergeymar4.servletapp2.controllers.CourseController;
import com.sergeymar4.servletapp2.controllers.StudentController;
import com.sergeymar4.servletapp2.models.Course;
import com.sergeymar4.servletapp2.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CourseServlet", value = "/courses")
public class CourseServlet extends HttpServlet {
    private CourseController courseController;

    @Override
    public void init() {
        this.courseController = new CourseController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String courseId = request.getParameter("id");

        if (courseId != null) {
            Course course = courseController.getById(Integer.parseInt(courseId));
            pw.println(new ObjectMapper().writeValueAsString(course));
        }
        else {
            List<Course> courses = courseController.getAll();
            pw.println(new ObjectMapper().writeValueAsString(courses));
        }
    }
}
