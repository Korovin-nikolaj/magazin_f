package ru.retail;

import java.io.Serializable;

public class BasketRow implements Serializable {
    private int productId;
    private String productName;
    private float quantity;
    private float price;

    public BasketRow(int productId, String productName, float quantity, float price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public BasketRow() {
        this.productId = 0;
        this.productName = "";
        this.quantity = 0;
        this.price = 0;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductId(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}