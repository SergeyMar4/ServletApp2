package com.sergeymar4.servletapp2.rest;

import com.sergeymar4.servletapp2.controllers.SchoolClassController;

public class Main {
    public static void main(String[] args) {
        SchoolClassController schoolClassController = new SchoolClassController();
        System.out.println(schoolClassController.getAll());
    }
}
