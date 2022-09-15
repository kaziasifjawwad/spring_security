package com.brainstation.spring_security.initializer;

import com.brainstation.spring_security.models.Role;
import com.brainstation.spring_security.pojo.RegistrationForm;
import com.brainstation.spring_security.service.RoleService;
import com.brainstation.spring_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {
  private final RoleService roleService;

  private final UserService userService;

  private static final String ADMIN_ROLE_NAME = "Admin";
  private static final String USER_ROLE_NAME = "user";
  @Override
  public void run(String... args) throws Exception {

    var adminRole = new Role().setName(ADMIN_ROLE_NAME);
    var userRole = new Role().setName(USER_ROLE_NAME);
    roleService.saveRole(adminRole);
    roleService.saveRole(userRole);
    userService.saveUser(new RegistrationForm("asif@gmail.com","1234"));
  }
}
