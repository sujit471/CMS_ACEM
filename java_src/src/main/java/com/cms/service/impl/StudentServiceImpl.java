package com.cms.service.impl;

import com.cms.builder.ResponseBuilder;
import com.cms.constant.ResponseMessageConstant;
import com.cms.dao.StudentDao;
import com.cms.dao.qualifer.Datasource;
import com.cms.dao.qualifer.DatasourceType;
import com.cms.model.Student;
import com.cms.response.Response;
import com.cms.service.StudentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StudentServiceImpl implements StudentService {

    @Inject
    @Datasource(DatasourceType.JDBC_TEMPLATE)
    private StudentDao studentDao;

    @Override
    public Response getAll() {
        Optional<List<Student>> optionalStudentList = studentDao.getAll();
        Response responseBody = null;
        if (optionalStudentList.isPresent()) {
            List<Student> students = optionalStudentList.get();
            responseBody = ResponseBuilder.success(ResponseMessageConstant.Student.ALL, students);
        } else {
            responseBody = ResponseBuilder.notFound(ResponseMessageConstant.Student.NOT_FOUND);
        }
        return responseBody;
    }

    @Override
    public Response getById(Long id) {
        Optional<Student> optionalStudent = studentDao.getById(id);
        Response responseBody = null;
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            responseBody = ResponseBuilder.success(ResponseMessageConstant.Student.ONE, student);

        } else {
            responseBody = ResponseBuilder.notFound(ResponseMessageConstant.Student.NOT_FOUND);
        }
        return responseBody;
    }

    @Override
    public Response getByEmailAddress(String emailAddress) {
        return null;
    }

    @Override
    public Response getByContactNo(String contactNo) {
        return null;
    }

    @Override
    public Response save(Student student) {
        Boolean isSaved = studentDao.save(student);
        Response responseBody = null;
        if (isSaved) {
            responseBody = ResponseBuilder.success(ResponseMessageConstant.Student.SAVED);
        } else {
            responseBody = ResponseBuilder.failure(ResponseMessageConstant.Student.NOT_SAVED);
        }
        return responseBody;
    }

    @Override
    public Response update(Student student) {
        Boolean isUpdated = studentDao.update(student);
        Response responseBody = null;
        if (isUpdated) {
            responseBody = ResponseBuilder.success(ResponseMessageConstant.Student.UPDATED);
        } else {
            responseBody = ResponseBuilder.failure(ResponseMessageConstant.Student.NOT_UPDATED);
        }
        return responseBody;
    }

    @Override
    public Response delete(Long id) {
        Boolean isDeleted = studentDao.delete(id);
        Response responseBody = null;
        if (isDeleted) {
            responseBody = ResponseBuilder.success(ResponseMessageConstant.Student.DELETED);
        } else {
            responseBody = ResponseBuilder.failure(ResponseMessageConstant.Student.NOT_DELETED);
        }
        return responseBody;
    }
}
