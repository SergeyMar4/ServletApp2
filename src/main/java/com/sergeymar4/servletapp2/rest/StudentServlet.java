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
import java.io.BufferedReader;
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
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String line;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = request.getReader()) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }

        Student student = new ObjectMapper().readValue(sb.toString(), Student.class);
        studentController.create(student);
        System.out.println(student);
        System.out.println(student.getSchoolClass());

        pw.println("<h1>Объект успешно создан</h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String studentId = request.getParameter("id");

        if (studentId == null) {
            pw.println("<h1>Нет информации об id</h1>");
        }
        else {
            studentController.delete(Integer.parseInt(studentId));
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String line;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = request.getReader()) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }

        Student student = new ObjectMapper().readValue(sb.toString(), Student.class);
        studentController.update(student);
        pw.println("<h1>Объект успешно обновлён</h1>");
    }
}
