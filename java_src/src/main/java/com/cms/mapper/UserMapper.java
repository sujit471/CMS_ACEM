package com.cms.mapper;

import com.cms.entity.User;
import com.cms.response.UserListResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public static UserListResponse map(User user){
        UserListResponse.UserListResponseBuilder userListResponseBuilder = UserListResponse
                .builder()
                .firstName(user.getName().getFirstName())
                .middleName(user.getName().getMiddleName())
                .lastName(user.getName().getLastName())
                .email(user.getEmail())
                .contactNo(user.getContactNo());

        UserListResponse userListResponse = userListResponseBuilder.build();
        return  userListResponse;
    }
}
