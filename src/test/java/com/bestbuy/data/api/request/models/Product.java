package com.bestbuy.data.api.request.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Product {

    private final String name;
    private final String type;
    private final double price;
    private final double shipping;
    private final String upc;
    private final String description;
    private final String manufacturer;
    private final String model;
    private final String url;
    private final String image;

    private Product(Product.Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.price = builder.price;
        this.shipping = builder.shipping;
        this.upc = builder.upc;
        this.description = builder.description;
        this.manufacturer = builder.manufacturer;
        this.model = builder.model;
        this.url = builder.url;
        this.image = builder.image;
    }

    // Getters for all fields
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public double getShipping() {
        return shipping;
    }

    public String getUpc() {
        return upc;
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

    public static class Builder {

        private String name;
        private String type;
        private double price;
        private double shipping;
        private String upc;
        private String description;
        private String manufacturer;
        private String model;
        private String url;
        private String image;

        public Product.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Product.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Product.Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Product.Builder setShipping(double shipping) {
            this.shipping = shipping;
            return this;
        }

        public Product.Builder setUpc(String upc) {
            this.upc = upc;
            return this;
        }

        public Product.Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Product.Builder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Product.Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Product.Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Product.Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
