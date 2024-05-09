package com.sergeymar4.servletapp2.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergeymar4.servletapp2.controllers.SchoolClassController;
import com.sergeymar4.servletapp2.controllers.TeacherController;
import com.sergeymar4.servletapp2.models.SchoolClass;
import com.sergeymar4.servletapp2.models.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TeacherServlet", value = "/teachers")
public class TeacherServlet extends HttpServlet {
    private TeacherController teacherController;

    @Override
    public void init() {
        this.teacherController = new TeacherController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String teacherId = request.getParameter("id");
        System.out.println("43y1hgreg03hjqhg3");

        if (teacherId != null) {
            Teacher teacher = teacherController.getById(Integer.parseInt(teacherId));
            pw.println(new ObjectMapper().writeValueAsString(teacher));
        }
        else {
            List<Teacher> teachers = teacherController.getAll();
            pw.println(new ObjectMapper().writeValueAsString(teachers));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println(1);
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        String line;

        try (BufferedReader br = request.getReader()) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }

        Teacher teacher = new ObjectMapper().readValue(sb.toString(), Teacher.class);
        teacherController.create(teacher);
        System.out.println(teacher);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String teacherId = request.getParameter("id");

        if (teacherId == null) {
            pw.println("<h1>Нет информации об id</h1>");
        }
        else {
            teacherController.delete(Integer.parseInt(teacherId));
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

        Teacher teacher = new ObjectMapper().readValue(sb.toString(), Teacher.class);
        teacherController.update(teacher);

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
