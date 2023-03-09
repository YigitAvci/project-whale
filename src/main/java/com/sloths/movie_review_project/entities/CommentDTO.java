package com.sloths.movie_review_project.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentDTO {
    private long id;

    private String author;

    private String comment;

    private Date lastEditDate;

    private long reviewId;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.author = comment.getAuthor().getUsername();
        this.comment = comment.getComment();
        this.lastEditDate = comment.getLastEditDate();
        this.reviewId = comment.getReview().getId();
    }
}
