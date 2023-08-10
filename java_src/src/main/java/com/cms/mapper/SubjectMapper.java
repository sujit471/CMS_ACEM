package com.cms.mapper;

import com.cms.entity.Subject;
import com.cms.response.SubjectResponse;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {


    public SubjectResponse map(Subject subject) {
        SubjectResponse.SubjectResponseBuilder subjectResponseBuilder = SubjectResponse
                .builder()
                .name(subject.getName())
                .code(subject.getCode());

        SubjectResponse subjectResponse = subjectResponseBuilder.build();
        return subjectResponse;
    }
}
