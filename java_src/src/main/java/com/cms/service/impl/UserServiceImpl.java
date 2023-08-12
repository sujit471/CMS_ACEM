package com.cms.service.impl;

import com.cms.builder.ResponseBuilder;
import com.cms.constant.ResponseMessageConstant;
import com.cms.entity.User;
import com.cms.repository.UserRepository;
import com.cms.request.UserSaveRequest;
import com.cms.request.UserUpdateRequest;
import com.cms.request.mapper.UserMapperUtil;
import com.cms.response.Response;
import com.cms.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapperUtil userMapperUtil;

    public UserServiceImpl(UserRepository userRepository, UserMapperUtil userMapperUtil) {
        this.userRepository = userRepository;
        this.userMapperUtil = userMapperUtil;
    }

    @Override
    public Response getAll() {
        List<User> users = userRepository.findAll();
        Response responseBody = null;
        if (!users.isEmpty()) {
            responseBody = ResponseBuilder.success(ResponseMessageConstant.User.ALL, users);
        } else {
            responseBody = ResponseBuilder.notFound(ResponseMessageConstant.User.NOT_FOUND);
        }
        return responseBody;
    }

    @Override
    public Response save(UserSaveRequest request) {
        User user = userMapperUtil.mapStudent(request);
        user.setStatus(true);
        Response responseBody = null;
        try {
            userRepository.save(user);
            responseBody = ResponseBuilder.success(ResponseMessageConstant.User.SAVED);
        } catch (Exception exception) {
            responseBody = ResponseBuilder.notFound(ResponseMessageConstant.User.NOT_SAVED);
        }
        return responseBody;
    }

    @Override
    public Response update(UserUpdateRequest request) {
        User mappedUser = userMapperUtil.mapStudent(request);
        Response responseBody = null;
        try {
            if(userRepository.getReferenceById(request.getId()).getId()!=null){
                mappedUser.setStatus(true);
                userRepository.save(mappedUser);
            }
            responseBody = ResponseBuilder.success(ResponseMessageConstant.User.UPDATED);
        } catch (Exception exception) {
            responseBody = ResponseBuilder.notFound(ResponseMessageConstant.User.NOT_UPDATED);
        }
        return responseBody;
    }

    @Override
    public Response delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        Response responseBody = null;
        try {
            userRepository.deleteById(id);
            responseBody = ResponseBuilder.success(ResponseMessageConstant.Subject.DELETED,user);
        } catch (Exception exception) {
            responseBody = ResponseBuilder.notFound(ResponseMessageConstant.Subject.NOT_DELETED);
        }
        return responseBody;
    }
}
