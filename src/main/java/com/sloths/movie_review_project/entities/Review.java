package com.sloths.movie_review_project.entities;

import com.sloths.movie_review_project.auth.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String review;

    private Date lastEditDate;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "review")
    private Set<Comment> comments;
}
