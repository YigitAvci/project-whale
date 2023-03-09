package com.sloths.movie_review_project.repositories;

import com.sloths.movie_review_project.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /*@Query(value = "select * from reviews as r where r.movieId=:movieId", nativeQuery = true)
    List<Review> getReviewsByMovieId(@Param("movieId") long movieId);*/

    List<Review> getReviewsByMovieId(Long movieId);
}
