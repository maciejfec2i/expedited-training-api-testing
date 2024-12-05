package com.bestbuy.data;

import net.datafaker.Faker;
import net.datafaker.providers.entertainment.Boardgame;

import java.util.concurrent.ThreadLocalRandom;

public class DataFaker {

    private static Faker faker = new Faker();

    public static Faker get() {
        return faker;
    }

    public static String getUpc() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000000, 999999999));
    }
}
