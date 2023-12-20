package com.cms.controller.ui;


import com.cms.controller.Controller;
import com.cms.service.StudentService;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class StudentController extends Controller {

    @Inject
    private StudentService studentService;

    @Override
    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students", studentService.getAll().getData());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/students/list.jsp");
        requestDispatcher.forward(request, response);
    }
}
