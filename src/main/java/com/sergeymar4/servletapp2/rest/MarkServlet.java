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
import java.io.BufferedReader;
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
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        Mark mark = new ObjectMapper().readValue(sb.toString(), Mark.class);
        markController.create(mark);

        pw.println("<h1>Объект успешно создан</h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String markId = request.getParameter("id");

        if (markId == null) {
            pw.println("<h1>Нет информации об id</h1>");
        }
        else {
            markController.delete(Integer.parseInt(markId));
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

        Mark mark = new ObjectMapper().readValue(sb.toString(), Mark.class);
        markController.update(mark);

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
