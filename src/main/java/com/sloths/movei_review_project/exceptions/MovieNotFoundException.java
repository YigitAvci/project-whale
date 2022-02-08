package com.sloths.movei_review_project.exceptions;

import javax.persistence.EntityNotFoundException;

public class MovieNotFoundException extends EntityNotFoundException {

    public MovieNotFoundException(int id) {
        super(String.format("there is not such a movie whose id: [%d]", id));
    }
}
