package com.bestbuy.actor.actions.api;

import com.bestbuy.utils.string.Humanise;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class VerifyTheResponseBody {

     private final LastResponse lastResponse;

    public VerifyTheResponseBody(LastResponse lastResponse) {
        this.lastResponse = lastResponse;
    }

    public static VerifyTheResponseBody ofThe(LastResponse lastResponse) {
        return new VerifyTheResponseBody(lastResponse);
    }

    public Performable matchesThe(Class<?> expectedPojoModel) {
        return Task.where(
                String.format("{0} verifies the response body of the last response matches the %s model", Humanise.theString(expectedPojoModel.getSimpleName())),
                actor -> lastResponse.answeredBy(actor).getBody().as(expectedPojoModel)
        );
    }
}
