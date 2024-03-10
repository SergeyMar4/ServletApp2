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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String teacherId = request.getParameter("id");

        if (teacherId != null) {
            Teacher teacher = teacherController.getById(Integer.parseInt(teacherId));
            pw.println(new ObjectMapper().writeValueAsString(teacher));
        }
        else {
            List<Teacher> teachers = teacherController.getAll();
            pw.println(new ObjectMapper().writeValueAsString(teachers));
        }
    }
}
