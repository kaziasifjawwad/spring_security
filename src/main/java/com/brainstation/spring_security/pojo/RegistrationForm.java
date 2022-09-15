package com.brainstation.spring_security.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationForm {
    private String email;
    private String password;
    private Long roleId;
}
