package com.sloths.movie_review_project.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDto {
    private long id;
    private String username;
}
