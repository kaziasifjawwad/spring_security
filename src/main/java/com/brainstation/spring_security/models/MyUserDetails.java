package com.brainstation.spring_security.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private final String userName;
    private final String password;
    private final boolean active;
    private final List<GrantedAuthority> authorities;

    //TODO
    /*  convert it with stream  */
    public MyUserDetails(User user) {
        this.userName = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = new LinkedList<>();
        for(Role role : user.getRoles()){
            this.authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

//        this.authorities = Arrays.stream(user.getRoles().split(","))
//                    .map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
