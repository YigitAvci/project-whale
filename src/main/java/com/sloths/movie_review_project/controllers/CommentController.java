package com.sloths.movie_review_project.controllers;

import com.sloths.movie_review_project.entities.Comment;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movie_review_project.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentManager;

    @PostMapping
    public CustomResponseEntity add(@RequestBody Comment comment) {
        return commentManager.add(comment);
    }

    @PutMapping
    public CustomResponseEntity update(@RequestBody Comment comment) {
        return commentManager.update(comment);
    }

    @DeleteMapping
    public CustomResponseEntity deleteById(@RequestParam(name = "id") long id) {
        return commentManager.deleteById(id);
    }

    @GetMapping("/by-review-id")
    public CustomResponseEntity getCommentsByReviewId (@RequestParam (name = "reviewId") long reviewId) {
        return commentManager.getCommentsByReviewId(reviewId);
    }
}
