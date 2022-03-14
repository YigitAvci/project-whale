package com.sloths.movie_review_project.helpers.responseHelpers;

public class CustomResponseEntitySuccess<T> extends CustomResponseEntity<T>{

    public CustomResponseEntitySuccess() {
        super();
    }

    public CustomResponseEntitySuccess(T data, String message) {
        super(data, message, true);
    }

    public CustomResponseEntitySuccess(T data) {
        super(data, true);
    }

    public CustomResponseEntitySuccess(String message) {
        super(message, true);
    }
}
