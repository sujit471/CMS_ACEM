package com.cms.service;

import com.cms.model.Student;
import com.cms.response.Response;


public interface StudentService {

    Response getAll();

    Response getById(Long id);

    Response getByEmailAddress(String emailAddress);

    Response getByContactNo(String contactNo);

    Response save(Student student);

    Response update(Student student);

    Response delete(Long id);
}
