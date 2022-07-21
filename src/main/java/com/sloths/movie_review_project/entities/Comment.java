package com.sloths.movie_review_project.entities;

import com.sloths.movie_review_project.auth.entities.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User author;

    private String comment;

    private Date lastEditDate;

    @ManyToOne
    private Review review;

}
