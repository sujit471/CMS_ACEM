package com.cms.request.mapper;

import com.cms.entity.Name;
import com.cms.entity.Role;
import com.cms.entity.User;
import com.cms.request.UserSaveRequest;
import com.cms.request.UserUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapperUtil {

    public User mapStudent(UserSaveRequest request) {
        User user = new User();
        user.setName(new Name(request.getFirstName(), request.getMiddleName(), request.getLastName()));
        user.setEmail(request.getEmail());
        user.setContactNo(request.getContactNo());
        return user;
    }

    public User mapStudent(UserUpdateRequest request) {
        User user = new User();
        user.setId(request.getId());
        user.setName(new Name(request.getFirstName(), request.getMiddleName(), request.getLastName()));
        user.setEmail(request.getEmail());
        user.setContactNo(request.getContactNo());
        return user;
    }
}
