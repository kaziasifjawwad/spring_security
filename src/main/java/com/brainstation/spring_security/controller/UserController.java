package com.brainstation.spring_security.controller;

import com.brainstation.spring_security.models.User;
import com.brainstation.spring_security.pojo.LoginUserDto;
import com.brainstation.spring_security.pojo.RegistrationForm;
import com.brainstation.spring_security.service.TokenService;
import com.brainstation.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class UserController {

    private UserService userService;

    @Autowired
    private TokenService tokenService;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto loginUser) {
        try{
            return new ResponseEntity<>(this.tokenService.getAuthentication(loginUser),HttpStatus.OK);
        }catch (BadCredentialsException badCredentialsException){
            return new ResponseEntity<>("Wrong information",HttpStatus.BAD_REQUEST);
        }
    }

}
