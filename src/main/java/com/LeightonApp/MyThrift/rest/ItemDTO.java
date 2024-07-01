package com.LeightonApp.MyThrift.rest;

import com.LeightonApp.MyThrift.entity.Item;
import com.LeightonApp.MyThrift.entity.Store;

// ItemDTO.java
public class ItemDTO {
    private String name;

    private int id;
    private String desc;
    private String category;

    public ItemDTO(Item item, Store store) {
        //dto.setImageUrl(getImageUrl(username, item.getImagePath()));  // need to add an image path to the item entity
        this.id = item.getId();
        this.name = item.getName();
        this.desc = item.getDescription();
        this.category = item.getCategory().getName();
        this.price = item.getPrice();
        this.imageUrl = store.getUser().getUsername() + '/' + item.getImagePath(); // Assuming imagePath is the correct field
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private double price;
    private String imageUrl;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

