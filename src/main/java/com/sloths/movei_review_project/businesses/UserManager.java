package com.sloths.movei_review_project.businesses;

import com.sloths.movei_review_project.auth.entities.User;
import com.sloths.movei_review_project.dataAccesses.UserDataAccess;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntityFail;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntitySuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final UserDataAccess userDataAccess;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserManager(UserDataAccess userDataAccess, BCryptPasswordEncoder passwordEncoder) {
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
