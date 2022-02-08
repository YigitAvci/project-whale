package com.sloths.movei_review_project.businesses;

import com.sloths.movei_review_project.auth.entities.User;
import com.sloths.movei_review_project.dataAccesses.UserDataAccess;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntityFail;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntitySuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private UserDataAccess userDataAccess;

    @Autowired
    public UserManager(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    public CustomResponseEntity add(User user) {
        if(user != null) {
            return new CustomResponseEntitySuccess(userDataAccess.save(user), "user has been saved, successfully!");
        }
        return new CustomResponseEntityFail("an error occured during storing the user! try again, please...");
    }
}
