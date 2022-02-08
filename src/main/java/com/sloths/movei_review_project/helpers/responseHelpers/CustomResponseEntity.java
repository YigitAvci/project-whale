package com.sloths.movei_review_project.helpers.responseHelpers;


import lombok.Getter;

public class CustomResponseEntity<T> {

    @Getter
    private T data;
    @Getter
    private String message;
    @Getter
    private boolean success;

    public CustomResponseEntity() {
    }

    public CustomResponseEntity(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public CustomResponseEntity(T data, boolean success) {
        this.data = data;
        this.message = null;
        this.success = success;
    }

    public CustomResponseEntity(String message, boolean success) {
        this.data = null;
        this.message = message;
        this.success = success;
    }
}
