package com.brainstation.spring_security.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationForm {
    @NotNull
    private String email;
    @NotNull
    private String password;

}
