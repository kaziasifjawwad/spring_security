package com.brainstation.spring_security.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtToken {

    @Id
    @GeneratedValue
    private long _id;

    private String token;

}