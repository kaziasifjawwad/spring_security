package com.brainstation.spring_security;

import com.brainstation.spring_security.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class SpringSecurityJpaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJpaApplication.class, args);
	}

}
