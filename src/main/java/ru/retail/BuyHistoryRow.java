package ru.retail;

import java.io.Serializable;
import java.util.HashMap;

public class BuyHistoryRow implements Serializable {
    private int orderId;
    private float sum;
    private String address;
    private String comment;
    private String status;
    private HashMap<Integer,BasketRow> products;

    public BuyHistoryRow(int orderId, float sum, String address, String comment, String status, HashMap<Integer, BasketRow> products) {
        this.orderId = orderId;
        this.sum = sum;
        this.address = address;
        this.comment = comment;
        this.status = status;
        this.products = products;
    }

    public BuyHistoryRow() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<Integer, BasketRow> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Integer, BasketRow> products) {
        this.products = products;
    }
}