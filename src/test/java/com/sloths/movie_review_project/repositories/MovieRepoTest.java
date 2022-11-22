package com.sloths.movie_review_project.repositories;

import com.sloths.movie_review_project.MovieJPAConfig;
import com.sloths.movie_review_project.entities.Movie;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { MovieJPAConfig.class },
        loader = AnnotationConfigContextLoader.class)
@DataJpaTest
public class MovieRepoTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void should_returnMovie_when_movieAdded() {
        String movieName = "the lies of yusra";
        Date movieDate = new Date();
        double movieRate = 2.5;
        Movie createdMovie = movieRepository.save(new Movie(0, movieName, movieDate, movieRate));
        assertEquals(1L, createdMovie.getId());
        assertEquals(movieName, createdMovie.getName());
        assertEquals(movieDate, createdMovie.getReleaseDate());
        assertEquals(movieRate, createdMovie.getRate());
    }
}
