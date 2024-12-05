package com.bestbuy.data.api.response.models.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCategoryResponseBody {
    private final String id;
    private final String name;
    private final String createdAt;
    private final String updatedAt;

    @JsonCreator
    public GetCategoryResponseBody(
            @JsonProperty(required = true, value = "id") String id,
            @JsonProperty(required = true, value = "name") String name,
            @JsonProperty(required = true, value = "createdAt") String createdAt,
            @JsonProperty(required = true, value = "updatedAt") String updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
