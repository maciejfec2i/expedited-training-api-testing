# Expedited Training - API Testing

Demo API tests on the <a href = "https://github.com/BestBuy/api-playground">Best Buy API Playground</a> API for 
the API Testing Expedited Training Module. The Best Buy API Playground can very easily be set up locally
using Docker.

## Requirements

- Best Buy API Playground set up and running
- Java 11 or Higher

If using a Java version different from 17, update lines 147 and 148 in the pom.xml to the version in use.

## Running the Tests

- To run all the tests run the command `mvnw clean verify` <br>
- To run a subset of tests use the command `mvnw clean verify -Dgroups=<existing-tag>`

## Tag Guide

| Tag                         | Description                                                                       |
|-----------------------------|-----------------------------------------------------------------------------------|
| products-endpoint-tests     | Executes all tests related to the products endpoint                               |
| get-product-tests           | Executes all tests related to the GET by id method of the products endpoint       |
| get-product-test-<test-id>  | Executes a specific test related to the GET by id method of the products endpoint |
| post-product-tests          | Executes all tests related to the POST method of the products endpoint            |
| post-product-test-<test-id> | Executes a specific test related to the POST method of the products endpoint      |

## Viewing the Serenity reports

Run the `mvnw clean verify` will produce a Serenity test report in the `target/site/serenity` directory.
