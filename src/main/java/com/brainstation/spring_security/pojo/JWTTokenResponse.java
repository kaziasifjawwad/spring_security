package com.brainstation.spring_security.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class JWTTokenResponse {
    private String token;
}
