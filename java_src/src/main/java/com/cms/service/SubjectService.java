package com.cms.service;

import com.cms.entity.Subject;
import com.cms.request.SubjectSaveRequest;
import com.cms.response.Response;

public interface SubjectService {
    Response getAll();

    Response getById(Long id);

    Response save(Subject subject);

    Response update(Subject subject);

    Response delete(Long id);
}