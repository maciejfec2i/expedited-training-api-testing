package com.bestbuy.actor.questions.api;

import io.restassured.response.ResponseBody;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class TheResponseBody {

    public static Question<ResponseBody> ofThe(LastResponse lastResponse) {
        return Question
                .about("")
                .answeredBy(actor -> lastResponse.answeredBy(actor).getBody());
    }
}
