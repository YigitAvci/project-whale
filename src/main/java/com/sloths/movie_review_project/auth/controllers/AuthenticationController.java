package com.sloths.movie_review_project.auth.controllers;

import com.sloths.movie_review_project.auth.entities.AuthenticationRequest;
import com.sloths.movie_review_project.auth.entities.AuthenticationResponse;
import com.sloths.movie_review_project.auth.entities.MyUserDetails;
import com.sloths.movie_review_project.auth.entities.UserDto;
import com.sloths.movie_review_project.auth.services.MyUserDetailsService;
import com.sloths.movie_review_project.auth.utils.JwtUtil;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntityFail;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntitySuccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authentication")
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public CustomResponseEntity getAuthenticationToken(@RequestBody AuthenticationRequest request) {
        log.info("request has come to the controller");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            MyUserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtUtil.createToken(userDetails);
            AuthenticationResponse authenticationResponse = new AuthenticationResponse(token, new UserDto(userDetails.getId(), userDetails.getUsername()));
            log.info("authentication token is returned: [{}]", authenticationResponse.getToken());
            return new CustomResponseEntitySuccess(authenticationResponse);

        }catch (BadCredentialsException exception) {
            return new CustomResponseEntityFail("Incorrect username or password: " + exception.getMessage());
        }
    }

}