package com.sergeymar4.servletapp2.rest;

import com.google.gson.Gson;
import com.sergeymar4.servletapp2.controllers.SchoolClassController;
import com.sergeymar4.servletapp2.models.SchoolClass;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SchoolClassServlet extends HttpServlet {
    private SchoolClassController schoolClassController;

    @Override
    public void init() {
        schoolClassController = new SchoolClassController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String schoolClassId = request.getParameter("id");

        if (schoolClassId != null) {
            SchoolClass schoolClass = schoolClassController.getById(Integer.parseInt(schoolClassId));
            System.out.println(schoolClass);
            pw.println(new Gson().toJson(schoolClass));
        }
        else {
            List<SchoolClass> schoolClasses= schoolClassController.getAll();
            pw.println(new Gson().toJson(schoolClasses));
        }
    }
}
