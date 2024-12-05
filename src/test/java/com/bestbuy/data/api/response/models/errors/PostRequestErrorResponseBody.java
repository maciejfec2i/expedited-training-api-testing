package com.bestbuy.data.api.response.models.errors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostRequestErrorResponseBody {
    private String name;
    private String message;
    private int code;
    private String className;
    private Object data;
    private List<String> errors;

    @JsonCreator
    public PostRequestErrorResponseBody(
            @JsonProperty(required = true, value = "name") String name,
            @JsonProperty(required = true, value = "message") String message,
            @JsonProperty(required = true, value = "code") int code,
            @JsonProperty(required = true, value = "className") String className,
            @JsonProperty(required = true, value = "data") Object data,
            @JsonProperty(required = true, value = "errors") List<String> errors
    ) {
        this.name = name;
        this.message = message;
        this.code = code;
        this.className = className;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors;
    }
}
