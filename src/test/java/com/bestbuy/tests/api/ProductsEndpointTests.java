package com.bestbuy.tests.api;

import com.bestbuy.actor.actions.api.*;
import com.bestbuy.actor.questions.api.TheResponseStatusCode;
import com.bestbuy.data.RememberedValue;
import com.bestbuy.data.api.request.models.Product;
import com.bestbuy.data.argumentproviders.GetProductResponseBodyTestArgsProvider;
import com.bestbuy.data.argumentproviders.GetProductStatusCodeTestArgsProvider;
import com.bestbuy.data.api.StatusCode;
import com.bestbuy.data.argumentproviders.PostProductResponseBodyTestArgsProvider;
import com.bestbuy.data.argumentproviders.PostProductStatusCodeTestArgsProvider;
import com.bestbuy.utils.junit.extensions.ActorInjector;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.bestbuy.data.api.StatusCode.*;
import static net.serenitybdd.screenplay.GivenWhenThen.*;

@ExtendWith({SerenityJUnit5Extension.class, ActorInjector.class})
@Tag("products-endpoint-tests")
public class ProductsEndpointTests {

    private Actor bestBuyApiConsumer;

    /**
     * Sets up the actor instance injected by the {@link ActorInjector} class. The actors name is derived from the name
     * of the parameter.
     *
     * @param bestBuyApiConsumer The {@link Actor} instance injected by the {@link ActorInjector} class.
     */
    @BeforeEach
    public void setActor(Actor bestBuyApiConsumer) {
        this.bestBuyApiConsumer = bestBuyApiConsumer;
    }

    @Nested
    @Tag("get-product-tests")
    @DisplayName("When getting a product")
    public class WhenGettingAProduct {

        @ParameterizedTest(name = "{index} - The {1} status code should be returned when providing product id {0}")
        @ArgumentsSource(GetProductStatusCodeTestArgsProvider.class)
        @Tag("get-product-test-1")
        @DisplayName("The expected status code should be returned for valid and invalid product ids")
        public void theExpectedStatusCodeShouldBeReturnedForValidAndInvalidProductIds(String productId, StatusCode expectedStatusCode) {
            givenThat(bestBuyApiConsumer).wasAbleTo(ConfirmAPI.isHealthy());
            when(bestBuyApiConsumer).attemptsTo(GetProduct.withIdOf(productId));
            then(bestBuyApiConsumer).attemptsTo(Ensure.that(TheResponseStatusCode.ofThe(LastResponse.received())).isEqualTo(expectedStatusCode.statusCode()));
        }

        @ParameterizedTest(name = "{index} - The expected response body should be returned when providing product id {0}")
        @ArgumentsSource(GetProductResponseBodyTestArgsProvider.class)
        @Tag("get-product-test-2")
        @DisplayName("The expected response body should be returned for valid and invalid product ids")
        public void theExpectedResponseBodyShouldBeReturnedForValidAndInvalidProductIds(String productId, Class<?> expectedResponseBodyModel) {
            givenThat(bestBuyApiConsumer).wasAbleTo(ConfirmAPI.isHealthy());
            when(bestBuyApiConsumer).attemptsTo(GetProduct.withIdOf(productId));
            then(bestBuyApiConsumer).attemptsTo(VerifyTheResponseBody.ofThe(LastResponse.received()).matchesThe(expectedResponseBodyModel));
        }

        @Test
        @Tag("get-product-test-3")
        @DisplayName("The expected status code should be returned for too many consecutive GET requests")
        public void theExpectedStatusCodeShouldBeReturnedForTooManyConsecutiveRequests() {
            givenThat(bestBuyApiConsumer).wasAbleTo(ConfirmAPI.isHealthy());
            when(bestBuyApiConsumer).attemptsTo(GetProduct.withIdOf(43900).sendingTheRequest(50).times());
            then(bestBuyApiConsumer).attemptsTo(Ensure.that(TheResponseStatusCode.ofThe(LastResponse.received())).isEqualTo(TOO_MANY_REQUESTS.statusCode()));
        }
    }

    @Nested
    @Tag("post-product-tests")
    @DisplayName("When posting a product")
    public class WhenPostingAProduct {

        @ParameterizedTest(name = "{index} - The {2} status code should be returned for {0}")
        @ArgumentsSource(PostProductStatusCodeTestArgsProvider.class)
        @Tag("post-product-test-1")
        @DisplayName("The expected status code should be returned for valid and invalid payloads")
        public void theExpectedStatusCodeShouldBeReturnedForValidAndInvalidPayloads(
                String testVariant,
                Product productInstance,
                StatusCode expectedStatusCode
        ) {
            givenThat(bestBuyApiConsumer).wasAbleTo(ConfirmAPI.isHealthy());
            when(bestBuyApiConsumer).attemptsTo(PostProduct.withPayloadGeneratedFrom(productInstance));
            then(bestBuyApiConsumer).attemptsTo(Ensure.that(TheResponseStatusCode.ofThe(LastResponse.received())).isEqualTo(expectedStatusCode.statusCode()));
        }


        @ParameterizedTest(name = "{index} - The {2} status code should be returned for {0}")
        @ArgumentsSource(PostProductResponseBodyTestArgsProvider.class)
        @Tag("post-product-test-2")
        @DisplayName("The expected response body should be returned for valid and invalid payloads")
        public void theExpectedResponseBodyShouldBeReturnedForValidAndInvalidPayloads(
                String testVariant,
                Product productInstance,
                Class<?> expectedResponseBodyModel
        ) {
            givenThat(bestBuyApiConsumer).wasAbleTo(ConfirmAPI.isHealthy());
            when(bestBuyApiConsumer).attemptsTo(PostProduct.withPayloadGeneratedFrom(productInstance));
            then(bestBuyApiConsumer).attemptsTo(VerifyTheResponseBody.ofThe(LastResponse.received()).matchesThe(expectedResponseBodyModel));
        }

        @Test
        @Tag("post-product-test-3")
        @DisplayName("The expected status code should be returned for too many consecutive POST requests")
        public void theExpectedStatusCodeShouldBeReturnedForTooManyConsecutiveRequests() {
            givenThat(bestBuyApiConsumer).wasAbleTo(ConfirmAPI.isHealthy());
            when(bestBuyApiConsumer).attemptsTo(PostProduct.withPayloadGeneratedFrom(new Product.Builder().build()).sendingTheRequest(50).times());
            then(bestBuyApiConsumer).attemptsTo(Ensure.that(TheResponseStatusCode.ofThe(LastResponse.received())).isEqualTo(TOO_MANY_REQUESTS.statusCode()));
        }
    }

    /**
     * Clears up any posted products to keep the environment clean if a value is remembered by the actor. A value is
     * remembered for any successful POST request to the products endpoint. See the {@link PostProduct} task.
     */
    @AfterEach
    public void clearAnyPostedProducts() {
        var lastSuccessfullyPostedProductId = Serenity.sessionVariableCalled(RememberedValue.LAST_SUCCESSFULLY_POSTED_PRODUCT_ID);

        if(lastSuccessfullyPostedProductId != null) {
            bestBuyApiConsumer.attemptsTo(DeleteProduct.withIdOf(String.valueOf(lastSuccessfullyPostedProductId)));
        }
    }
}
