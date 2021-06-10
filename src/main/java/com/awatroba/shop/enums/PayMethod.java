package com.awatroba.shop.enums;

import pay.PayCreditCard;
import pay.PayPayPal;
import pay.PayStrategy;

import java.util.stream.Stream;

/**
 * @author Angelika
 * enum for pay method
 */
public enum PayMethod {
    CREDIT_CARD("Credit Cart",new PayCreditCard()),
    PAY_PAL("Pay pal",new PayPayPal());
    private String displayName;
    private PayStrategy payStrategy;

    PayMethod(String displayName,PayStrategy payStrategy) {
        this.displayName = displayName;
        this.payStrategy = payStrategy;
    }

    public PayStrategy getPayStrategy() {
        return payStrategy;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Stream<PayMethod> stream() {
        return Stream.of(PayMethod.values());
    }
}