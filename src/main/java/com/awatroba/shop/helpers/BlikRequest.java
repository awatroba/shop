package com.awatroba.shop.helpers;

import com.awatroba.shop.enums.PayMethod;
import pay.PayStrategy;

import java.util.HashMap;
import java.util.Map;

public class BlikRequest implements PayRequest {
    Map<String, Object> data;
    PayStrategy payStrategy = PayMethod.BLIK.getPayStrategy();

    public BlikRequest() {
        data = new HashMap<>();
        setData();
    }

    @Override
    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public void setData() {
        data.put("code", "");
    }

    @Override
    public boolean verify(PayRequest request) {
        return payStrategy.verify(request);
    }
}
