package io.testomat.e2e_tests_light_1.utils;

import org.jetbrains.annotations.NotNull;

public class StringParsers {

    @NotNull
    public static Integer parseIntegerFromString(String targetText) {
        String digitText = targetText.replaceAll("\\D+", "");
        return Integer.parseInt(digitText);
    }

    @NotNull
    public static Double parseDoubleFromString(String targetText) {
        String doubleText = targetText.replaceAll("[^\\d.]", "");
        return Double.parseDouble(doubleText);
    }


}
