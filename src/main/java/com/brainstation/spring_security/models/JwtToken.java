package com.brainstation.spring_security.models;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class JwtToken {

    @Id
    @GeneratedValue
    private long _id;
    @Column(length = 1024)
    private String token;
    private String username;
    private Instant expiredDate;
}