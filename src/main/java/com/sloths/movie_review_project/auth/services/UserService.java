package com.sloths.movie_review_project.auth.services;

import com.sloths.movie_review_project.auth.entities.User;
import com.sloths.movie_review_project.auth.repositories.UserRepository;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntityFail;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntitySuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userDataAccess;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userDataAccess, BCryptPasswordEncoder passwordEncoder) {
        this.userDataAccess = userDataAccess;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomResponseEntity add(User user) {
        if(user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return new CustomResponseEntitySuccess(userDataAccess.save(user), "user has been saved, successfully!");
        }
        return new CustomResponseEntityFail("an error occurred during storing the user! try again, please...");
    }
}
