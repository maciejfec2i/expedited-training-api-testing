package com.bestbuy.data.argumentproviders;

import com.bestbuy.data.api.StatusCode;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class GetProductStatusCodeTestArgsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of("43900", StatusCode.OK),
                Arguments.of("0", StatusCode.NOT_FOUND),
                Arguments.of("-10", StatusCode.NOT_FOUND),
                Arguments.of("text", StatusCode.NOT_FOUND),
                Arguments.of("😎😎😎", StatusCode.NOT_FOUND),
                Arguments.of("0 OR 1=1", StatusCode.NOT_FOUND)
        );
    }
}
