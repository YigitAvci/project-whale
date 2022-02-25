package com.sloths.movei_review_project.auth.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class AuthenticationResponse implements Serializable {

    private final String token;
}
