package com.sloths.movie_review_project.services;

import com.sloths.movie_review_project.entities.Review;
import com.sloths.movie_review_project.entities.ReviewDTO;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntitySuccess;
import com.sloths.movie_review_project.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public CustomResponseEntity getAll() {
        return new CustomResponseEntitySuccess(reviewRepository.findAll(), "all reviews have been listed");
    }

    public CustomResponseEntity add(Review review) {
        review.setLastEditDate(new Date());
        return new CustomResponseEntitySuccess(reviewRepository.save(review), "your review has been saved, successfully");
    }

    public CustomResponseEntity getById(long id) {
        return new CustomResponseEntitySuccess(new ReviewDTO(reviewRepository.getById(id)), String.format("the review whose id is %d", id));
    }
}
