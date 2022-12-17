package com.sloths.movie_review_project.auth.services;

import com.sloths.movie_review_project.auth.entities.User;
import com.sloths.movie_review_project.auth.repositories.UserRepository;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntityFail;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntitySuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if(!isUsernameProper(user.getUsername()) || !isPasswordProper(user.getPassword())) {
            return new CustomResponseEntityFail("username or password does not prove the constraints! try again, please...");
        }
        //User existingUser = userDataAccess.findByUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new CustomResponseEntitySuccess<>(userDataAccess.save(user), "user has been saved, successfully!");
    }

    public CustomResponseEntity<User> delete(User user) {
        if(user == null) {
            return new CustomResponseEntityFail("an error occurred during deleting the user! try again, please...");
        }
        User userToBeDeleted = userDataAccess.getById(user.getId());
        try {
            userDataAccess.delete(user);
        }catch (DataAccessException dataAccessException) {
            return new CustomResponseEntityFail("a database access exception has been occurred!");
        }
        return new CustomResponseEntitySuccess(userToBeDeleted, "user has been deleted, successfully!");
    }

    private boolean isPasswordProper(String password) {
        if(password.length() < 8 || !password.matches("(.*)[a-zA-Z](.*)") || !password.matches("(.*)[0-9](.*)")) {
            return false;
        }
        return true;
    }

    private boolean isUsernameProper(String username) {
        if(username.length() == 0 || username.contains(" ")) {
            return false;
        }
        return true;
    }
}
