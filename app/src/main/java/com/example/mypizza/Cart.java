package com.example.mypizza;

public class Cart {
    int cartId;
    String imageUrl;
    String name;
    Double price;

    public Cart(int cartId, String imageUrl, String name, double price) {
        this.cartId=cartId;
        this.imageUrl=imageUrl;
        this.name=name;
        this.price=price;

    }


    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
