package com.awatroba.shop.enums;

import java.util.stream.Stream;

/**
 * @author Angelika
 * enum for delivery method
 */
public enum DeliveryMethod {
    PARCEL_LOCKER("Parcel locker");
    //TODO:
   // COURIER("Courier");
    private String displayName;

    DeliveryMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Stream<DeliveryMethod> stream() {
        return Stream.of(DeliveryMethod.values());
    }
}