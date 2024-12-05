package com.bestbuy.data.api.response.models.errors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetRequestErrorResponseBody {

    private String name;
    private String message;
    private int code;
    private String className;
    private Object errors;

    @JsonCreator
    public GetRequestErrorResponseBody(
            @JsonProperty(required = true, value = "name") String name,
            @JsonProperty(required = true, value = "message") String message,
            @JsonProperty(required = true, value = "code") int code,
            @JsonProperty(required = true, value = "className") String className,
            @JsonProperty(required = false, value = "errors") Object errors
    ) {
        this.name = name;
        this.message = message;
        this.code = code;
        this.className = className;
        this.errors = errors;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getClassName() {
        return className;
    }

    public Object getErrors() {
        return errors;
    }
}
