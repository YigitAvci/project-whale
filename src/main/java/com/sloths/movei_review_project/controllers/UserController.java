package com.sloths.movei_review_project.controllers;

import com.sloths.movei_review_project.auth.entities.User;
import com.sloths.movei_review_project.businesses.UserManager;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/addUser")
    public CustomResponseEntity add(@RequestBody User user) {
        return userManager.add(user);
    }
}
