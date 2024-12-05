package com.bestbuy.utils.string;

import java.util.List;

public class Humanise {

    public static String theString(String string) {
        List<String> strings = List.of(string.split("(?=[A-Z])"));
        return String.join(" ", strings);
    }
}
