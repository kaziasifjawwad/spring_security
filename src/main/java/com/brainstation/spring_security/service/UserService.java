package com.brainstation.spring_security.service;

import com.brainstation.spring_security.models.User;
import com.brainstation.spring_security.pojo.RegistrationForm;
import com.brainstation.spring_security.repository.RoleRepository;
import com.brainstation.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  private UserRepository userRepository;

  private RoleRepository roleRepository;

  @Autowired
  public void setRoleRepository(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  private PasswordEncoder passwordEncoder;

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Autowired
  public void setbCryptPasswordEncoder(PasswordEncoder bCryptPasswordEncoder) {
    this.passwordEncoder = bCryptPasswordEncoder;
  }

  public User saveUser(RegistrationForm registrationForm) {
    User user = new User()
      .setEmail(registrationForm.getEmail())
      .setPassword(passwordEncoder.encode(registrationForm.getPassword()));
    var role = roleRepository.findById(registrationForm.getRoleId()).get();
    var roles = List.of(role);
    user.setRoles(roles);
    return this.userRepository.save(user);
  }
}
