package com.cms.controller;


import com.cms.response.Response;
import com.cms.utils.JacksonUtil;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Controller extends HttpServlet {

    private static final String APPLICATION_JSON = "application/json";


    protected void buildResponse(HttpServletResponse response, Response responseBody) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType(APPLICATION_JSON);
        response.setStatus(responseBody.getStatusCode());
        writer.write(JacksonUtil.toJson(responseBody));
    }
}
