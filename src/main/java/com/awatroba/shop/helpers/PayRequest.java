package com.awatroba.shop.helpers;


import java.util.Map;
/**
 * @author Angelika
 * interface of pay request
 */
public interface PayRequest {
    Map<String, Object> getData();
    void setData();
    boolean verify(PayRequest request);

}
