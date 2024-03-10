package com.sergeymar4.servletapp2.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergeymar4.servletapp2.controllers.CourseController;
import com.sergeymar4.servletapp2.controllers.MarkController;
import com.sergeymar4.servletapp2.models.Course;
import com.sergeymar4.servletapp2.models.Mark;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "MarkServlet", value = "/marks")
public class MarkServlet extends HttpServlet {
    private MarkController markController;

    @Override
    public void init() {
        this.markController = new MarkController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String markId = request.getParameter("id");

        if (markId != null) {
            Mark mark = markController.getById(Integer.parseInt(markId));
            pw.println(new ObjectMapper().writeValueAsString(mark));
        }
        else {
            List<Mark> marks = markController.getAll();
            pw.println(new ObjectMapper().writeValueAsString(marks));
        }
    }
}
