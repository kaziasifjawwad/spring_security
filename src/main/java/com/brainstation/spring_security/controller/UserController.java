package com.brainstation.spring_security.controller;

import com.brainstation.spring_security.models.User;
import com.brainstation.spring_security.pojo.RegistrationForm;
import com.brainstation.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createNewUser(@RequestBody RegistrationForm registrationForm) {
        return new ResponseEntity<>(
                this.userService.saveUser(registrationForm), HttpStatus.OK
        );
    }
}
