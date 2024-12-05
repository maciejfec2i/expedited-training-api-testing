package com.bestbuy.data.argumentproviders;

import com.bestbuy.data.DataFaker;
import com.bestbuy.data.api.StatusCode;
import com.bestbuy.data.api.request.models.Product;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class PostProductStatusCodeTestArgsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(
                        "payload with all fields populated",
                        new Product.Builder()
                                .setName(DataFaker.get().boardgame().name())
                                .setType(DataFaker.get().boardgame().category())
                                .setPrice((double) Math.round(DataFaker.get().random().nextDouble() * 100) / 100)
                                .setShipping(0)
                                .setUpc(DataFaker.getUpc())
                                .setDescription(DataFaker.get().boardgame().mechanic())
                                .setManufacturer(DataFaker.get().boardgame().publisher())
                                .setModel(DataFaker.get().boardgame().subdomain())
                                .setUrl("some url")
                                .setImage("some image")
                                .build(),
                        StatusCode.CREATED
                ),
                Arguments.of(
                        "payload with only mandatory fields populated",
                        new Product.Builder()
                                .setName(DataFaker.get().boardgame().name())
                                .setType(DataFaker.get().boardgame().category())
                                .setUpc(DataFaker.getUpc())
                                .setDescription(DataFaker.get().boardgame().mechanic())
                                .setModel(DataFaker.get().boardgame().subdomain())
                                .build(),
                        StatusCode.CREATED
                ),
                Arguments.of(
                        "payload with a mandatory field missing",
                        new Product.Builder()
                                .setName(DataFaker.get().boardgame().name())
                                .setPrice((double) Math.round(DataFaker.get().random().nextDouble() * 100) / 100)
                                .setShipping(0)
                                .setUpc(DataFaker.getUpc())
                                .setDescription(DataFaker.get().boardgame().mechanic())
                                .setManufacturer(DataFaker.get().boardgame().publisher())
                                .setModel(DataFaker.get().boardgame().subdomain())
                                .setUrl("some url")
                                .setImage("some image")
                                .build(),
                        StatusCode.BAD_REQUEST
                ),
                Arguments.of(
                        "payload with price set to a number with more than 2 decimal places",
                        new Product.Builder()
                                .setName(DataFaker.get().boardgame().name())
                                .setType(DataFaker.get().boardgame().category())
                                .setPrice(DataFaker.get().random().nextDouble())
                                .setShipping(0)
                                .setUpc(DataFaker.getUpc())
                                .setDescription(DataFaker.get().boardgame().mechanic())
                                .setManufacturer(DataFaker.get().boardgame().publisher())
                                .setModel(DataFaker.get().boardgame().subdomain())
                                .setUrl("some url")
                                .setImage("some image")
                                .build(),
                        StatusCode.BAD_REQUEST
                )
        );
    }
}
