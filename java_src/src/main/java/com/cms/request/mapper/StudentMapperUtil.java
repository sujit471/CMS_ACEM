package com.cms.request.mapper;

import com.cms.model.Student;
import com.cms.request.StudentSaveRequest;
import com.cms.request.StudentUpdateRequest;
import com.cms.utils.ModalMapperUtil;

public class StudentMapperUtil {

    public static Student mapStudent(StudentSaveRequest studentSaveRequest) {
        return ModalMapperUtil.map(studentSaveRequest, Student.class);
    }

    public static Student mapStudent(StudentUpdateRequest studentUpdateRequest) {
        return ModalMapperUtil.map(studentUpdateRequest, Student.class);
    }
}
