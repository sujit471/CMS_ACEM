package com.cms.controller;

import com.cms.request.UserSaveRequest;
import com.cms.request.UserUpdateRequest;
import com.cms.response.Response;
import com.cms.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response users() {
        return userService.getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response save(@RequestBody @Validated UserSaveRequest request) {
        return userService.save(request);
    }

    @PutMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response update(@RequestBody @Validated UserUpdateRequest request){
        return userService.update(request);
    }

    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }
}
