package com.brainstation.spring_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;


public class JwtTokenUtil implements Serializable {

    JwtDecoder jwtDecoder;

    @Autowired
    public void setJwtDecoder(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token).get("sub").toString();
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        DateTimeFormatter fmt = DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC);
        return Date.from(Instant.from(fmt.parse(getClaimFromToken(token).get("exp").toString())));
    }

    public Map<String, Object> getClaimFromToken(String token) {
        return jwtDecoder.decode(token).getClaims();
    }


    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}