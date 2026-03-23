package com.ecommerce.ecommerce_backend.dto;

public class CartResponse {

    private String userName;
    private String productName;
    private int quantity;

    // ✅ GETTERS
    public String getUserName() {
        return userName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    // ✅ SETTERS
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}