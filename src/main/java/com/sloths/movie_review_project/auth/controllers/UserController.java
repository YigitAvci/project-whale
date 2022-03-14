package com.sloths.movie_review_project.auth.controllers;

import com.sloths.movie_review_project.auth.entities.User;
import com.sloths.movie_review_project.auth.services.UserService;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userManager;

    @Autowired
    public UserController(UserService userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/addUser")
    public CustomResponseEntity add(@RequestBody User user) {
        return userManager.add(user);
    }
}
