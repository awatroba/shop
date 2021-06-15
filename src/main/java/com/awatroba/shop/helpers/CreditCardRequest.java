package com.awatroba.shop.helpers;

import com.awatroba.shop.enums.PayMethod;
import pay.PayStrategy;

import java.util.HashMap;
import java.util.Map;

public class CreditCardRequest implements PayRequest {
    Map<String, Object> data;
    PayStrategy payStrategy = PayMethod.CREDIT_CARD.getPayStrategy();

    public CreditCardRequest() {
        data = new HashMap<>();
        setData();
    }

    @Override
    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public void setData() {
        data.put("amount", "");
        data.put("number", "");
        data.put("date", "");
        data.put("cvv", "");
    }

    @Override
    public boolean verify(PayRequest request) {
        return payStrategy.verify(request);
    }
}
