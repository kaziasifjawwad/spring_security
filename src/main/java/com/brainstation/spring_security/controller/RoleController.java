package com.brainstation.spring_security.controller;

import com.brainstation.spring_security.models.Role;
import com.brainstation.spring_security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/saveRole")
    public ResponseEntity<Role> saveRole(@Valid @RequestBody Role role) {
        return new ResponseEntity<>(this.roleService.saveRole(role), HttpStatus.OK);
    }
}
