package com.cms.service.impl;

import com.cms.builder.ResponseBuilder;
import com.cms.constant.ResponseMessageConstant;
import com.cms.entity.Subject;
import com.cms.mapper.SubjectMapper;
import com.cms.repository.SubjectRepository;
import com.cms.response.Response;
import com.cms.response.SubjectResponse;
import com.cms.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public Response getAll() {
        List<Subject> subjects = subjectRepository.findAll();
        Response responseBody = null;
        if (!subjects.isEmpty()) {
            responseBody = ResponseBuilder.success(ResponseMessageConstant.Subject.ALL, subjects);
        } else {
            responseBody = ResponseBuilder.notFound(ResponseMessageConstant.Subject.NOT_FOUND);
        }
        return responseBody;
    }

    @Override
    public Response getById(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        Response responseBody = null;
        try {
            if (optionalSubject.isPresent()) {
                Subject subject = optionalSubject.get();
                SubjectResponse subjectResponse = subjectMapper.map(subject);
                responseBody = ResponseBuilder.success(ResponseMessageConstant.Subject.ONE,
                        subjectResponse);
            }
        } catch (Exception exception) {
            responseBody = ResponseBuilder.notFound(ResponseMessageConstant.Subject.NOT_SAVED);
        }

        return responseBody;
    }

    @Override
    public Response save(Subject subject) {

        subject.setStatus(true);
        Subject savedSubject = subjectRepository.save(subject);
        Response responseBody = null;
        try {
            responseBody = ResponseBuilder.success(ResponseMessageConstant.Subject.SAVED);
        } catch (Exception exception) {
            responseBody = ResponseBuilder.notFound(ResponseMessageConstant.Subject.NOT_SAVED);
        }
        return responseBody;
    }


    @Override
    public Response update(Subject subject) {

        Response responseBody = null;
        try {
            subjectRepository.findById(subject.getId());
            subject.setStatus(true);
            subjectRepository.save(subject);
            responseBody = ResponseBuilder.success(ResponseMessageConstant.Subject.UPDATED, subject);
        } catch (Exception exception) {
            responseBody = ResponseBuilder.notFound(ResponseMessageConstant.Subject.NOT_UPDATED);
        }
        return responseBody;
    }

    @Override
    public Response delete(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        Subject subject = optionalSubject.get();
        Response responseBody = null;
        try {
            subjectRepository.deleteById(id);
            responseBody = ResponseBuilder.success(ResponseMessageConstant.Subject.DELETED,subject);
        } catch (Exception exception) {
            responseBody = ResponseBuilder.notFound(ResponseMessageConstant.Subject.NOT_DELETED);
        }
        return responseBody;
    }
}