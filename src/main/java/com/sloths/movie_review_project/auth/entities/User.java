package com.sloths.movie_review_project.auth.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Getter
    @Column(unique = true, nullable = false)
    private String email;

    @Setter
    @Getter
    @Column(unique = true, nullable = false)
    private String username;

    @Setter
    @Getter
    @Column(nullable = false)
    private String password;


    @Setter
    @Getter
    @Column(nullable = false)
    private String role;

    @Setter
    @Getter
    @Column
    private String photo;
}
