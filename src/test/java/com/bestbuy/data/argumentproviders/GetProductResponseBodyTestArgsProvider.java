package com.bestbuy.data.argumentproviders;

import com.bestbuy.data.api.response.models.errors.GetRequestErrorResponseBody;
import com.bestbuy.data.api.response.models.product.GetProductResponseBody;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class GetProductResponseBodyTestArgsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of("43900", GetProductResponseBody.class),
                Arguments.of("0", GetRequestErrorResponseBody.class),
                Arguments.of("-10", GetRequestErrorResponseBody.class),
                Arguments.of("text", GetRequestErrorResponseBody.class),
                Arguments.of("😎😎😎", GetRequestErrorResponseBody.class),
                Arguments.of("0 OR 1=1", GetRequestErrorResponseBody.class)
        );
    }
}
