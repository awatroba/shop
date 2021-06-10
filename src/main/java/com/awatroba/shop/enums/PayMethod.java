package com.awatroba.shop.enums;

import java.util.stream.Stream;

/**
 * @author Angelika
 * enum for pay method
 */
public enum PayMethod {
    CREDIT_CARD("Credit Cart"),
    PAY_PAL("Pay pal"),
    OTHERS("Others");
    private String displayName;

    PayMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Stream<PayMethod> stream() {
        return Stream.of(PayMethod.values());
    }
}