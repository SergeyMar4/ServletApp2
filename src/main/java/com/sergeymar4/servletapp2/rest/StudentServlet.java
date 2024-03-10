package com.sergeymar4.servletapp2.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergeymar4.servletapp2.controllers.StudentController;
import com.sergeymar4.servletapp2.controllers.TeacherController;
import com.sergeymar4.servletapp2.models.Student;
import com.sergeymar4.servletapp2.models.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/students")
public class StudentServlet extends HttpServlet {
    private StudentController studentController;

    @Override
    public void init() {
        this.studentController = new StudentController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String studentId = request.getParameter("id");

        if (studentId != null) {
            Student student = studentController.getById(Integer.parseInt(studentId));
            pw.println(new ObjectMapper().writeValueAsString(student));
        }
        else {
            List<Student> students = studentController.getAll();
            pw.println(new ObjectMapper().writeValueAsString(students));
        }
    }
}
