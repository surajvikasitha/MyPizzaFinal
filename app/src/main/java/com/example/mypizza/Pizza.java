package com.example.mypizza;

public class Pizza {
    private int id;
    private String name;
    private String description;
    private Double price;
    private String imgurl;


    public Pizza() {

    }

    public Pizza(int id, String name, String description, Double price, String imgurl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgurl = imgurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}

