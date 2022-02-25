package com.sloths.movei_review_project.auth.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class AuthenticationRequest implements Serializable {

    private final String username;
    private final String password;

}
