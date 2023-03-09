package com.sloths.movie_review_project.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReviewDTO {
    private long id;

    private String review;

    private Date lastEditDate;

    private long movieId;

    private String author;

    private List<CommentDTO> comments;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.review = review.getReview();
        this.lastEditDate = review.getLastEditDate();
        this.movieId = review.getMovie().getId();
        this.author = review.getAuthor().getUsername();
        this.comments = review.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());
    }
}
