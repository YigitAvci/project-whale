package com.sloths.movie_review_project.controllers;

import com.sloths.movie_review_project.services.MovieService;
import com.sloths.movie_review_project.entities.Movie;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieManager;

    @Autowired
    public MovieController(MovieService movieManager) {
        this.movieManager = movieManager;
    }

    @GetMapping("/getMovieById/{id}")
    public CustomResponseEntity getById(@PathVariable int id) {
        return movieManager.getById(id);
    }

    @GetMapping("/getAllMovies")
    public CustomResponseEntity getAll() {
        return movieManager.getAll();
    }

    @PostMapping("/addMovie")
    public CustomResponseEntity add(@RequestBody @Valid Movie movie) {
        return movieManager.add(movie);
    }

    @PutMapping("/updateMovie")
    public CustomResponseEntity update(@RequestBody Movie movie) {
        return movieManager.update(movie);
    }

    @DeleteMapping("/deleteMovieById/{id}")
    public CustomResponseEntity deleteById(@PathVariable(value = "id") int id) {
        return movieManager.deleteById(id);
    }

}
