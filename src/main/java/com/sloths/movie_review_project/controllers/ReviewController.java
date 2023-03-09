package com.sloths.movie_review_project.controllers;

import com.sloths.movie_review_project.entities.Review;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movie_review_project.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewManager;

    @PostMapping()
    public CustomResponseEntity add(@RequestBody Review review) {
        return reviewManager.add(review);
    }

    @GetMapping()
    public CustomResponseEntity getAll(){
        return reviewManager.getAll();
    }

    @GetMapping("/review")
    public CustomResponseEntity getById(@RequestParam long id) {
        return reviewManager.getById(id);
    }

    @GetMapping("/reviewsByMovieId")
    public CustomResponseEntity getByMovieId(@RequestParam long movieId) {
        return reviewManager.getByMovieId(movieId);
    }
}
