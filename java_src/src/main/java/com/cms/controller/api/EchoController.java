package com.cms.controller.api;

import com.cms.builder.ResponseBuilder;
import com.cms.controller.Controller;
import com.cms.exception.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.PrintWriter;

public class EchoController extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ExceptionHandler.handleWithFallBack(
                () -> {
                    PrintWriter writer = response.getWriter();
                    response.setContentType(MediaType.TEXT_HTML);
                    response.setStatus(Response.Status.OK.getStatusCode());
                    writer.write("<h1>Application is running</h1>");
                },
                () -> buildResponse(response, ResponseBuilder.serverError())
        );
    }
}
