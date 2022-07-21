package com.sloths.movie_review_project;

import com.sloths.movie_review_project.services.MovieService;
import com.sloths.movie_review_project.entities.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieReviewProjectApplicationTests {

    private final MovieService movieManager;

    @Autowired
    public MovieReviewProjectApplicationTests(MovieService movieManager) {
        this.movieManager = movieManager;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testAddFunction() {
        Movie movieToBeAdded = new Movie(0, "Troy", null, 8.5); //state is transient
        movieManager.add(movieToBeAdded); //state is persist
        Assertions.assertThat(movieToBeAdded.getId()).isEqualTo(18);
    }

    @Test
    public void testDeleteFunction() {
        int id = 19;
        Movie movieToBeDeleted = (Movie) movieManager.getById(19).getData();
        String movieName = movieToBeDeleted.getName();
        Assertions.assertThat(movieName).isEqualTo("Troy");
    }

}
