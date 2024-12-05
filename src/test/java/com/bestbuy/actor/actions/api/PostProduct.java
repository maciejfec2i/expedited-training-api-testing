package com.bestbuy.actor.actions.api;

import com.bestbuy.actor.questions.api.TheResponseBody;
import com.bestbuy.actor.questions.api.TheResponseStatusCode;
import com.bestbuy.data.ConvertToJSON;
import com.bestbuy.data.RememberedValue;
import com.bestbuy.data.api.request.models.Product;
import com.bestbuy.data.api.response.models.product.PostProductResponseBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.RememberThat;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PostProduct implements Performable {

    private final Product productInstance;
    private int numOfRequests = 1;

    public PostProduct(Product productInstance) {
        this.productInstance = productInstance;
    }

    public static PostProduct withPayloadGeneratedFrom(Product productInstance) {
        return Tasks.instrumented(PostProduct.class, productInstance);
    }

    public PostProduct sendingTheRequest(int numOfRequests) {
        this.numOfRequests = numOfRequests;
        return this;
    }

    public Performable times() {
        return this;
    }

    @Step("{0} attempts to POST product sending the request #numOfRequests times")
    @Override
    public <T extends Actor> void performAs(T actor) {
        String jsonPayload = ConvertToJSON.the(productInstance);

        for(int i = 0; i < numOfRequests; i++) {
            actor.attemptsTo(Post.to("/products").with(request -> request.body(jsonPayload)));
        }

        if(TheResponseStatusCode.ofThe(LastResponse.received()).answeredBy(actor) == 201) {
            PostProductResponseBody product = TheResponseBody.ofThe(LastResponse.received()).answeredBy(actor).as(PostProductResponseBody.class);
            Serenity.setSessionVariable(RememberedValue.LAST_SUCCESSFULLY_POSTED_PRODUCT_ID).to(product.getId());
        }
    }
}
