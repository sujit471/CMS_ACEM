package com.cms.controller.api;

import com.cms.request.StudentSaveRequest;
import com.cms.request.mapper.StudentMapperUtil;
import com.cms.service.StudentService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v2/students")
public class StudentV2Controller {

    @Inject
    private StudentService studentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response students() {
        com.cms.response.Response responseBody = studentService.getAll();

        return Response
                .status(responseBody.getStatusCode())
                .entity(responseBody)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid StudentSaveRequest studentSaveRequest) {
        com.cms.response.Response responseBody = studentService.save(StudentMapperUtil.mapStudent(studentSaveRequest));

        return Response
                .status(responseBody.getStatusCode())
                .entity(responseBody)
                .build();
    }

}
