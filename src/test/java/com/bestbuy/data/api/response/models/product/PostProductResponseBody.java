package com.bestbuy.data.api.response.models.product;

import com.bestbuy.data.api.response.models.category.GetCategoryResponseBody;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostProductResponseBody {

    private final long id;
    private final String name;
    private final String type;
    private final double price;
    private final String upc;
    private final double shipping;
    private final String description;
    private final String manufacturer;
    private final String model;
    private final String url;
    private final String image;
    private final String createdAt;
    private final String updatedAt;
    private final List<GetCategoryResponseBody> categories;

    @JsonCreator
    public PostProductResponseBody(
            @JsonProperty(required = true, value = "id") long id,
            @JsonProperty(required = true, value = "name") String name,
            @JsonProperty(required = true, value = "type") String type,
            @JsonProperty(required = false, value = "price") double price,
            @JsonProperty(required = true, value = "upc") String upc,
            @JsonProperty(required = false, value = "shipping") double shipping,
            @JsonProperty(required = true, value = "description") String description,
            @JsonProperty(required = false, value = "manufacturer") String manufacturer,
            @JsonProperty(required = true, value = "model") String model,
            @JsonProperty(required = false, value = "url") String url,
            @JsonProperty(required = false, value = "image") String image,
            @JsonProperty(required = true, value = "createdAt") String createdAt,
            @JsonProperty(required = true, value = "updatedAt") String updatedAt,
            @JsonProperty(required = false, value = "categories") List<GetCategoryResponseBody> categories
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.upc = upc;
        this.shipping = shipping;
        this.description = description;
        this.manufacturer = manufacturer;
        this.model = model;
        this.url = url;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categories = categories;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getUpc() {
        return upc;
    }

    public double getShipping() {
        return shipping;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public List<GetCategoryResponseBody> getCategories() {
        return categories;
    }
}
