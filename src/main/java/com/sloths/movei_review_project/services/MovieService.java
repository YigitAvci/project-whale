package com.sloths.movei_review_project.services;

import com.sloths.movei_review_project.repositories.MovieRepository;
import com.sloths.movei_review_project.entities.Actor;
import com.sloths.movei_review_project.entities.ActorDTO;
import com.sloths.movei_review_project.entities.Movie;
import com.sloths.movei_review_project.entities.MovieDTO;
import com.sloths.movei_review_project.exceptions.MovieNotFoundException;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntityFail;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntitySuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class MovieService {

    private final MovieRepository movieDataAccess;
    private final ActorService actorManager;

    @Autowired
    public MovieService(MovieRepository movieDataAccess, ActorService actorManager) {
        this.movieDataAccess = movieDataAccess;
        this.actorManager = actorManager;
    }

    public CustomResponseEntity getById(long id) {
        Movie movieToBeGet = movieDataAccess.getById(id);
        if(movieToBeGet.getId() == id) {
            return new CustomResponseEntitySuccess<>(movieDataAccess.getById(id));
        }
        return new CustomResponseEntityFail(String.format("There is no movie which has such an id: [%s]", id));
    }

    public CustomResponseEntity findAll() {
        return new CustomResponseEntitySuccess<>(movieDataAccess.findAll(), "List of all the movies");
    }

    public CustomResponseEntity deleteById(long id) {
        try {
            Movie movieToBeDeleted = movieDataAccess.getById(id);
            movieDataAccess.deleteById(id);
            System.out.println("is there any problem until here?");
            return new CustomResponseEntitySuccess<>(movieToBeDeleted, String.format("The movie whose id [%s] is deleted", movieToBeDeleted));
        }catch (EntityNotFoundException e) {
            throw new MovieNotFoundException(id);
        }
    }

    public CustomResponseEntity add(Movie movie) {
        List<Actor> actors = movie.getCast().stream().
                filter(actor -> actor.getId() > 0).
                filter(actor -> actorManager.isExist(actor.getId())).
                map(actor -> actorManager.getActorById(actor.getId())).
                toList();
        if(!actors.isEmpty()) {
            for (Actor actor : actors) {
                List<Movie> movies = actor.getMovies();
                movies.add(movie);
                actor.setMovies(movies);
            }
            movie.setCast(handleActors(movie, actors));
        }
        movieDataAccess.save(movie);

        List<ActorDTO> actorDTOList = new ArrayList<>();
        for (Actor actor : movie.getCast()) {
            actorDTOList.add(new ActorDTO(actor));
        }
        MovieDTO movieDTO = new MovieDTO(movie, actorDTOList);
        return new CustomResponseEntitySuccess<>(movieDTO, String.format("the movie [%s] is added successfully", movieDTO));
    }

    /**
     *
     * @param movie
     * we will use movie to get its cast
     * @param actorsToHandle
     * according to database records, actorsToHandle parameter includes the actors who are existed in at least one or more movies
     * @return one list that can hold all the actors who are existed in related movie without duplication
     */
    private List<Actor> handleActors(Movie movie, List<Actor> actorsToHandle){
        List<Actor> resultList = movie.getCast().stream().filter(actor -> actor.getId() == 0).toList();
        return Stream.concat(resultList.stream(),actorsToHandle.stream()).toList();
    }

    public CustomResponseEntity update(Movie movie) {
        long movieId = movie.getId();
        if(movieId > 0) {
            try {
                Movie movieToBeUpdate = movieDataAccess.getById(movieId);
                final Movie oldMovie = new Movie(movieToBeUpdate.getId(), movieToBeUpdate.getName(), movieToBeUpdate.getReleaseDate(), movieToBeUpdate.getRate());
                movieToBeUpdate.setFieldsIfNotNull(movie);
                movieDataAccess.save(movieToBeUpdate);
                return new CustomResponseEntitySuccess(String.format("movie: [%s] is updated to movie: [%s]", oldMovie, movieToBeUpdate));
            }catch (EntityNotFoundException ex) {
                return new CustomResponseEntityFail(String.format("there is no such a movie whose id: [%d]", movieId));
            }
        }
        return new CustomResponseEntityFail("update process is not performed");
    }



}
