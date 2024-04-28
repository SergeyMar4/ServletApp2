package com.sergeymar4.servletapp2.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sergeymar4.servletapp2.controllers.SchoolClassController;
import com.sergeymar4.servletapp2.models.SchoolClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SchoolServlet", value = "/schoolClass")
public class SchoolClassServlet extends HttpServlet {
    private SchoolClassController schoolClassController;

    @Override
    public void init() {
        schoolClassController = new SchoolClassController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String schoolClassId = request.getParameter("id");

        if (schoolClassId != null) {
            SchoolClass schoolClass = schoolClassController.getById(Integer.parseInt(schoolClassId));
            System.out.println(schoolClass);
            pw.println(new ObjectMapper().writeValueAsString(schoolClass));
        } else {
            List<SchoolClass> schoolClasses = schoolClassController.getAll();
            System.out.println(schoolClasses);
            pw.println(new ObjectMapper().writeValueAsString(schoolClasses));
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

        SchoolClass schoolClass = new ObjectMapper().readValue(sb.toString(), SchoolClass.class);
        schoolClassController.create(schoolClass);

        pw.println("<h1>Объект успешно создан</h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String schoolClassId = request.getParameter("id");

        if (schoolClassId == null) {
            pw.println("<h1>Нет информации об id</h1>");
        }
        else {
            schoolClassController.delete(Integer.parseInt(schoolClassId));
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

        SchoolClass schoolClass = new ObjectMapper().readValue(sb.toString(), SchoolClass.class);
        schoolClassController.update(schoolClass);

        pw.println("<h1>Объект успешно обновлён</h1>");
    }
}