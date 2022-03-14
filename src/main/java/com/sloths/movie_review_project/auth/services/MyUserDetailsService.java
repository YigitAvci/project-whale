package com.sloths.movie_review_project.auth.services;

import com.sloths.movie_review_project.auth.entities.User;
import com.sloths.movie_review_project.auth.entities.MyUserDetails;
import com.sloths.movie_review_project.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userDataAccess;

    @Autowired
    public MyUserDetailsService(UserRepository userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username != null && !username.isEmpty() && !username.isBlank()) {
            User user = userDataAccess.findByUsername(username);
            return new MyUserDetails(user);
        }
        return null;
    }

}
