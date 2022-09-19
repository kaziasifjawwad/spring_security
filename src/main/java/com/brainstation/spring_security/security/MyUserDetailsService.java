package com.brainstation.spring_security.security;

import com.brainstation.spring_security.models.MyUserDetails;
import com.brainstation.spring_security.models.User;
import com.brainstation.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user  = this.userRepository.findByEmail(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("User not found");
        return new MyUserDetails(
                user.get()
        );
    }
}
