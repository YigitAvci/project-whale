package com.sloths.movie_review_project.auth.controllers;

import com.sloths.movie_review_project.auth.entities.AuthenticationRequest;
import com.sloths.movie_review_project.auth.entities.AuthenticationResponse;
import com.sloths.movie_review_project.auth.entities.MyUserDetails;
import com.sloths.movie_review_project.auth.services.MyUserDetailsService;
import com.sloths.movie_review_project.auth.services.utils.JwtUtil;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntityFail;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntitySuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public CustomResponseEntity getAuthenticationToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            MyUserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtUtil.createToken(userDetails);
            return new CustomResponseEntitySuccess(new AuthenticationResponse(token));

        }catch (BadCredentialsException exception) {
            return new CustomResponseEntityFail("Incorrect username or password: " + exception.getMessage());
        }
    }

}