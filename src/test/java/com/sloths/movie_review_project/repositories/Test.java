package com.sloths.movie_review_project.repositories;

import com.sloths.movie_review_project.JPAConfig;
import com.sloths.movie_review_project.entities.Actor;
import com.sloths.movie_review_project.entities.Movie;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(
        classes = JPAConfig.class,
        loader = AnnotationConfigContextLoader.class)
@DataJpaTest
public class Test {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    ActorRepository actorRepository;

    private final String movieName = "the lies of ucy";
    private final Date movieDate = new Date();
    private final double movieRate = 2.5;

    @org.junit.jupiter.api.Test
    void should_returnMovie_when_movieAdded() {
        Movie createdMovie = movieRepository.save(new Movie(0, movieName, movieDate, movieRate));
        assertEquals(1L, createdMovie.getId());
        assertEquals(movieName, createdMovie.getName());
        assertEquals(movieDate, createdMovie.getReleaseDate());
        assertEquals(movieRate, createdMovie.getRate());
    }

    @org.junit.jupiter.api.Test
    void should_returnNonEmptyList_when_moviesExist() {
        movieRepository.save(new Movie(0, movieName, movieDate, movieRate));
        movieRepository.save(new Movie(0, movieName, movieDate, movieRate));
        assertEquals(2, movieRepository.findAll().size());
    }

    @org.junit.jupiter.api.Test
    void should_returnActor_when_actorAdded() {
        Actor createdActor = actorRepository.save(
                new Actor(
                        0,
                        "uyc allyson",
                        "uz",
                        (new GregorianCalendar(1999, Calendar.JANUARY, 20)).getTime(),
                        List.of()));

        assertEquals(1L, createdActor.getId());
    }
}
