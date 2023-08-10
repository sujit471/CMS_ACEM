package com.cms.controller;

import com.cms.builder.ResponseBuilder;
import com.cms.constant.ResponseMessageConstant;
import com.cms.request.SubjectSaveRequest;
import com.cms.request.SubjectUpdateRequest;
import com.cms.request.mapper.SubjectMapperUtil;
import com.cms.response.Response;
import com.cms.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects/")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> save(@Validated @RequestBody SubjectSaveRequest subjectSaveRequest){
        Response responseBody = subjectService.save(SubjectMapperUtil.mapSubject(subjectSaveRequest));

        if(responseBody.getSuccess()){
            return new ResponseEntity<>(ResponseBuilder.success(ResponseMessageConstant.Subject.SAVED), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(ResponseBuilder.failure(ResponseMessageConstant.Subject.NOT_SAVED),HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> update(@Validated @RequestBody SubjectUpdateRequest subjectUpdateRequest){
        Response responseBody = subjectService.update(SubjectMapperUtil.mapSubject(subjectUpdateRequest));

        if(responseBody.getSuccess()){
            return new ResponseEntity<>(ResponseBuilder.success(ResponseMessageConstant.Subject.UPDATED),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(ResponseBuilder.failure(ResponseMessageConstant.Subject.NOT_UPDATED),HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response subjects() {
        return subjectService.getAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response subject(@PathVariable("id") Long id) {
        return subjectService.getById(id);
    }

    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response delete(@PathVariable("id") Long id){
        return subjectService.delete(id);
    }



}
