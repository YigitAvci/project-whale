package com.sloths.movie_review_project.helpers.responseHelpers;


import lombok.Getter;

public class CustomResponseEntity<T> {

    @Getter
    private T data;
    @Getter
    private String message;
    @Getter
    private boolean isSuccess;

    public CustomResponseEntity() {
    }

    public CustomResponseEntity(T data, String message, boolean isSuccess) {
        this.data = data;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public CustomResponseEntity(T data, boolean isSuccess) {
        this.data = data;
        this.message = null;
        this.isSuccess = isSuccess;
    }

    public CustomResponseEntity(String message, boolean isSuccess) {
        this.data = null;
        this.message = message;
        this.isSuccess = isSuccess;
    }
}
