package com.sloths.movie_review_project.auth.entities;

import com.sloths.movie_review_project.entities.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Review> reviews;
}
