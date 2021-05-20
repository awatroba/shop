package com.awatroba.shop.helpers;

public class Response<T> {

    private String status;
    private T data;

    public Response(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public Response() {
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}