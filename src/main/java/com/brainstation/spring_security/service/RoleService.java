package com.brainstation.spring_security.service;

import com.brainstation.spring_security.models.Role;
import com.brainstation.spring_security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role) throws DataIntegrityViolationException {
        return this.roleRepository.save(role);
    }
}
