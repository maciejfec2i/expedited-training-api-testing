package com.bestbuy.actor.actions.api;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetProduct implements Performable {

    private final String productId;
    private int numOfRequests = 1;

    public GetProduct(String productId) {
        this.productId = productId;
    }

    public static GetProduct withIdOf(String productId) {
        return Tasks.instrumented(GetProduct.class, productId);
    }

    public static GetProduct withIdOf(long productId) {
        return Tasks.instrumented(GetProduct.class, String.valueOf(productId));
    }

    public GetProduct sendingTheRequest(int numOfRequests) {
        this.numOfRequests = numOfRequests;
        return this;
    }

    public Performable times() {
        return this;
    }

    @Step("{0} attempts to GET product with id of #productId sending the request #numOfRequests times")
    @Override
    public <T extends Actor> void performAs(T actor) {
        for(int i = 0; i < numOfRequests; i++) {
            actor.attemptsTo(Get.resource("/products/{id}").with(request -> request.pathParam("id", productId)));
        }
    }
}
