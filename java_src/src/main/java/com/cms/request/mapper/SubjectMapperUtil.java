package com.cms.request.mapper;

import com.cms.entity.Subject;
import com.cms.request.SubjectSaveRequest;
import com.cms.request.SubjectUpdateRequest;
import com.cms.utils.ModelMapperUtil;

public class SubjectMapperUtil {
    public static Subject mapSubject(SubjectSaveRequest subjectSaveRequest) {
        return ModelMapperUtil.map(subjectSaveRequest, Subject.class);
    }

    public static Subject mapSubject(SubjectUpdateRequest subjectUpdateRequest) {
        return ModelMapperUtil.map(subjectUpdateRequest, Subject.class);
    }
}
