package com.bestbuy.data.api;

public enum StatusCode {

    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    TOO_MANY_REQUESTS(429);

    private final int statusCode;

    StatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int statusCode() {
        return this.statusCode;
    }
}
