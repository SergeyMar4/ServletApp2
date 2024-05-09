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
import java.io.BufferedReader;
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
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
//        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String line;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = request.getReader()) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }

        Course course = new ObjectMapper().readValue(sb.toString(), Course.class);
        courseController.create(course);

//        pw.println("<h1>Объект успешно создан</h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String courseId = request.getParameter("id");

        if (courseId == null) {
            pw.println("<h1>Нет информации об id</h1>");
        }
        else {
            courseController.delete(Integer.parseInt(courseId));
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
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

        Course course = new ObjectMapper().readValue(sb.toString(), Course.class);
        courseController.update(course);

        pw.println("<h1>Объект успешно обновлён</h1>");
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
