package com.blogposts.exception;

/**
 * This class is a simple POJO, used for creating a custom exception object.
 */
public class ApiException {

    private final String error;

    ApiException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
