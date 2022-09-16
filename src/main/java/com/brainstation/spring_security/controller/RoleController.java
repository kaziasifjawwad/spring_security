package com.brainstation.spring_security.controller;

import com.brainstation.spring_security.models.Role;
import com.brainstation.spring_security.pojo.APIResponse;
import com.brainstation.spring_security.pojo.ErrorMessage;
import com.brainstation.spring_security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    private OncePerRequestFilter oncePerRequestFilter;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/saveRole")
    public ResponseEntity<APIResponse<?>> saveRole(@Valid @RequestBody Role role) {
        try {
            return new ResponseEntity<>(
                    new APIResponse<>(this.roleService.saveRole(role)), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new APIResponse<>(
                    new ErrorMessage(role.getName()+" is already exists")
            ), HttpStatus.BAD_REQUEST);
        }
    }
}
