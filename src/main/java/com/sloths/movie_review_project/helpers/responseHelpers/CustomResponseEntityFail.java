package com.sloths.movie_review_project.helpers.responseHelpers;

public class CustomResponseEntityFail extends CustomResponseEntity{

    public CustomResponseEntityFail() {
        super();
    }

    public CustomResponseEntityFail(String message) {
        super(null, message, false);
    }
}
