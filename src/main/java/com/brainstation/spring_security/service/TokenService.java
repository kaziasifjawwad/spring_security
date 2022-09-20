package com.brainstation.spring_security.service;

import com.brainstation.spring_security.models.JwtToken;
import com.brainstation.spring_security.pojo.LoginUserDto;
import com.brainstation.spring_security.repository.JwtTokenRepository;
import com.brainstation.spring_security.security.customJWTRquestFilterTools.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

@Service
public class TokenService {
    private AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private JwtTokenRepository jwtTokenRepository;

    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    public void setJwtTokenRepository(JwtTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String getAuthentication(LoginUserDto loginUser) throws BadCredentialsException, InternalAuthenticationServiceException {

        return generateToken(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUser.getEmail(), loginUser.getPassword())
        ));
//        return token;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        Instant expiredTime = now.plus(1, ChronoUnit.HOURS);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .issuer("self")
                .expiresAt(expiredTime)
                .subject(authentication.getName())
                .build();
        String generatedToken = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        this.jwtTokenRepository.save(
                new JwtToken()
                        .setToken(generatedToken)
                        .setUsername(
                                authentication.getName()
                        )
                        .setExpiredDate(expiredTime)
        );
        return generatedToken;
    }

    public void deleteToken(String token)  {
        JwtToken jwtToken = jwtTokenRepository.findByToken(token);
        System.out.println(jwtToken);
        if (jwtToken == null) throw new NoSuchElementException();
        System.out.println(this.jwtTokenUtil.getUsernameFromToken(token));
        System.out.println(jwtToken.getUsername());
        if (!this.jwtTokenUtil.getUsernameFromToken(token).equals(jwtToken.getUsername()))
            throw new BadCredentialsException("Wrong user credential");

        this.jwtTokenRepository.delete(jwtToken);
    }
}
