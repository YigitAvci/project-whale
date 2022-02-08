package com.sloths.movei_review_project.auth.services;

import com.sloths.movei_review_project.auth.entities.User;
import com.sloths.movei_review_project.auth.helper.MyUserDetails;
import com.sloths.movei_review_project.dataAccesses.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserDataAccess userDataAccess;

    @Autowired
    public MyUserDetailsService(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username != null && !username.isEmpty() && !username.isBlank()) {
            User user = userDataAccess.findByUsername(username);
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return new MyUserDetails(user);
        }
        return null;
    }

    /*

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDataAccess.findByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.roles(user.getRole());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }

     */
}
