package com.brainstation.spring_security.controller;

import com.brainstation.spring_security.models.User;
import com.brainstation.spring_security.pojo.*;
import com.brainstation.spring_security.service.TokenService;
import com.brainstation.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")

public class UserController {

    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createNewUser(@Valid @RequestBody RegistrationForm registrationForm) {
        return new ResponseEntity<>(
                this.userService.saveUser(registrationForm), HttpStatus.OK
        );
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<?>> login(@Valid @RequestBody LoginUserDto loginUser) {
        try {
            return new ResponseEntity<>(
                    new APIResponse<>(
                            new JWTTokenResponse(
                                    this.tokenService.getAuthentication(loginUser)
                            )
                    ), HttpStatus.OK);
        } catch (BadCredentialsException badCredentialsException) {
            return new ResponseEntity<>(
                    new APIResponse<>(new ResponseMessage("Wrong user credential")), HttpStatus.BAD_REQUEST);
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            return new ResponseEntity<>(
                    new APIResponse<>(new ResponseMessage("No user with this email found")), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<APIResponse<?>> logout(@RequestBody LogOutDto logOutDto) {
        try {
            this.tokenService.deleteToken(logOutDto.getJwtToken());
            return new ResponseEntity<>(
                    new APIResponse<>(new ResponseMessage("user logged out")), HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(
                    new APIResponse<>(new ResponseMessage("Wrong JWT token")), HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException badCredentialsException) {
            return new ResponseEntity<>(
                    new APIResponse<>(new ResponseMessage("You are not allowed to do this job")), HttpStatus.BAD_REQUEST);
        }
    }
}
