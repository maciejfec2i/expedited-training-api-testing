package com.bestbuy.actor.actions.api;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteProduct implements Performable {

    private final String productId;
    private int numOfRequests = 1;

    public DeleteProduct(String productId) {
        this.productId = productId;
    }

    public static DeleteProduct withIdOf(String productId) {
        return Tasks.instrumented(DeleteProduct.class, productId);
    }

    public static DeleteProduct withIdOf(long productId) {
        return Tasks.instrumented(DeleteProduct.class, String.valueOf(productId));
    }

    public DeleteProduct sendingTheRequest(int numOfRequests) {
        this.numOfRequests = numOfRequests;
        return this;
    }

    public Performable times() {
        return this;
    }

    @Step("{0} attempts to DELETE product with id of #productId sending the request #numOfRequests times")
    @Override
    public <T extends Actor> void performAs(T actor) {
        for(int i = 0; i < numOfRequests; i++) {
            actor.attemptsTo(Delete.from("/products/{id}").with(request -> request.pathParam("id", productId)));
        }
    }
}
