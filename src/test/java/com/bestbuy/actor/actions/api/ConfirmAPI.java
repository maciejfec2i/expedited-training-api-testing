package com.bestbuy.actor.actions.api;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.questions.TheResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfirmAPI implements Performable {

    private final Logger LOGGER = LoggerFactory.getLogger(ConfirmAPI.class);

    public static ConfirmAPI isHealthy() {
        return Tasks.instrumented(ConfirmAPI.class);
    }

    @Step("{0} attempts to confirm the API is healthy")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/healthcheck"),
                Ensure.that(TheResponse.statusCode()).isEqualTo(200)
        );
    }
}
