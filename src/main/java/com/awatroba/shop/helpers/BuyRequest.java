package com.awatroba.shop.helpers;

import com.awatroba.shop.enums.DeliveryMethod;
import com.awatroba.shop.enums.PayMethod;

public class BuyRequest {
    PayMethod payMethod;
    DeliveryMethod deliveryMethod;

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }
    public void setPayMethod(String payMethod) {
        this.payMethod = PayMethod.valueOf(payMethod);
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = DeliveryMethod.valueOf(deliveryMethod);
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public BuyRequest() {
    }

    public BuyRequest(PayMethod payMethod, DeliveryMethod deliveryMethod) {
        this.payMethod = payMethod;
        this.deliveryMethod = deliveryMethod;
    }
}
