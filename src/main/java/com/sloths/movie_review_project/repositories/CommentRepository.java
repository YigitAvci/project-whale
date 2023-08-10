package com.sloths.movie_review_project.repositories;

import com.sloths.movie_review_project.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    ArrayList<Comment> getCommentsByReviewId(long reviewId);
    Comment getCommentById(long id);
}
