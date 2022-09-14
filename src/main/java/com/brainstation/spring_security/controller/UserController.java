package com.brainstation.spring_security.controller;

import com.brainstation.spring_security.models.User;
import com.brainstation.spring_security.pojo.AuthenticationRequest;
import com.brainstation.spring_security.pojo.AuthenticationResponse;
import com.brainstation.spring_security.pojo.RegistrationForm;
import com.brainstation.spring_security.security.jwt.JwtUtil;
import com.brainstation.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    private JwtUtil jwtUtil;

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

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

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> getJwtToken(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws Exception {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );

        } catch (BadCredentialsException exception) {
            throw new Exception("Incorrect Username or Password", exception);
        }
        final UserDetails userDetails  = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
