package com.bestbuy.actor.questions.api;

import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class TheResponseStatusCode implements Question<Integer> {

    private final LastResponse lastResponse;

    public TheResponseStatusCode(LastResponse lastResponse) {
        this.lastResponse = lastResponse;
    }

    public static Question<Integer> ofThe(LastResponse lastResponse) {
        return Question.about("the response status code").answeredBy(new TheResponseStatusCode(lastResponse));
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return lastResponse.answeredBy(actor).statusCode();
    }
}
