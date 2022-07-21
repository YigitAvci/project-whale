package com.sloths.movie_review_project.services;

import com.sloths.movie_review_project.entities.Comment;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntitySuccess;
import com.sloths.movie_review_project.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentDataAccess;

    public CustomResponseEntity add(Comment comment) {
        comment.setLastEditDate(new Date());
        return new CustomResponseEntitySuccess(commentDataAccess.save(comment), "the comment has been saved, successfully!");
    }

    public CustomResponseEntity update(Comment comment) {
        Comment commentToBeUpdate = commentDataAccess.getById(comment.getId());
        commentToBeUpdate.setComment(comment.getComment());
        commentToBeUpdate.setLastEditDate(new Date());
        return new CustomResponseEntitySuccess(commentToBeUpdate, "the comment has been updated, successfully!");
    }

    public CustomResponseEntity deleteById(long id) {
        commentDataAccess.deleteById(id);
        return new CustomResponseEntitySuccess("the comment has been deleted, successfully!");
    }
}
