package com.sloths.movei_review_project.controllerAdvices;

import com.sloths.movei_review_project.exceptions.MovieNotFoundException;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntityFail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MovieControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<CustomResponseEntityFail> handleMovieNotFoundException(MovieNotFoundException ex) {
        return ResponseEntity.badRequest().body(new CustomResponseEntityFail(ex.getMessage()));
    }

}
