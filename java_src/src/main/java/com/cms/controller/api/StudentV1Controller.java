package com.cms.controller.api;

import com.cms.builder.ResponseBuilder;
import com.cms.constant.RegexConstant;
import com.cms.controller.Controller;
import com.cms.exception.ExceptionHandler;
import com.cms.request.StudentSaveRequest;
import com.cms.request.StudentUpdateRequest;
import com.cms.request.mapper.StudentMapperUtil;
import com.cms.response.Response;
import com.cms.service.StudentService;
import com.cms.utils.InputStreamMapperUtil;
import com.cms.utils.ValidationUtil;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class StudentV1Controller extends Controller {

    @Inject
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ExceptionHandler.handleWithFallBack(
                () -> {
                    String url = request.getRequestURL().toString();
                    String[] urlTokenized = url.split("/");
                    String id = urlTokenized[urlTokenized.length - 1];
                    Response responseBody = null;
                    if (id.matches(RegexConstant.isNumber)) {
                        responseBody = studentService.getById(Long.parseLong(id));
                    } else {
                        responseBody = studentService.getAll();
                    }
                    buildResponse(response, responseBody);
                },
                () -> buildResponse(response, ResponseBuilder.serverError())
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExceptionHandler.handleWithFallBack(
                () -> {
                    StudentSaveRequest studentSaveRequest = InputStreamMapperUtil
                            .mapToObject(request.getInputStream(), StudentSaveRequest.class);

                    Optional<List<String>> violations = ValidationUtil.validate(studentSaveRequest);

                    Response responseBody = null;
                    if (violations.isPresent()) {
                        responseBody = ResponseBuilder.validationFailed(violations.get());
                    } else {
                        responseBody = studentService.save(StudentMapperUtil.mapStudent(studentSaveRequest));
                    }
                    buildResponse(response, responseBody);
                },
                () -> buildResponse(response, ResponseBuilder.serverError())
        );
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExceptionHandler.handleWithFallBack(
                () -> {
                    StudentUpdateRequest studentUpdateRequest = InputStreamMapperUtil
                            .mapToObject(request.getInputStream(), StudentUpdateRequest.class);

                    Optional<List<String>> violations = ValidationUtil.validate(studentUpdateRequest);

                    Response responseBody = null;
                    if (violations.isPresent()) {
                        responseBody = ResponseBuilder.validationFailed(violations.get());
                    } else {
                        responseBody = studentService.update(StudentMapperUtil.mapStudent(studentUpdateRequest));
                    }
                    buildResponse(response, responseBody);
                },
                () -> buildResponse(response, ResponseBuilder.serverError())
        );
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExceptionHandler.handleWithFallBack(
                () -> {
                    String url = request.getRequestURL().toString();
                    String[] urlTokenized = url.split("/");
                    String id = urlTokenized[urlTokenized.length - 1];
                    Response responseBody = null;
                    if (id.matches(RegexConstant.isNumber)) {
                        responseBody = studentService.delete(Long.parseLong(id));
                    } else {
                        responseBody = ResponseBuilder.invalidPathParameter();
                    }
                    buildResponse(response, responseBody);
                },
                () -> buildResponse(response, ResponseBuilder.serverError())
        );
    }
}
