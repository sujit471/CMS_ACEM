package com.cms.service;

import com.cms.request.UserSaveRequest;
import com.cms.response.Response;

public interface UserService {

    Response getAll();

    Response save(UserSaveRequest request);

}
