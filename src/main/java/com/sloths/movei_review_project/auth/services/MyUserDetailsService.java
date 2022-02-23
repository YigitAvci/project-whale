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

    private final UserDataAccess userDataAccess;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MyUserDetailsService(UserDataAccess userDataAccess, BCryptPasswordEncoder passwordEncoder) {
        this.userDataAccess = userDataAccess;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username != null && !username.isEmpty() && !username.isBlank()) {
            User user = userDataAccess.findByUsername(username);
            user.setPassword(user.getPassword());
            return new MyUserDetails(user);
        }
        return null;
    }

}
