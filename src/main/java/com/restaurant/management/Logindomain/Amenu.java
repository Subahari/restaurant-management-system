package com.restaurant.management.Logindomain;

import javax.persistence.*;

@Entity
@Table(name = "menus")
public class Amenu {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private int calories;
    private String name;
    private double price;
    private String type;
    private  String img_location;

    public Amenu(String img_location) {
        this.img_location = img_location;
    }

    public String getImg_location() {
        return img_location;
    }

    public void setImg_location(String img_location) {
        this.img_location = img_location;
    }

    public Amenu() {

    }

    public Amenu(Long id, int calories, String name, double price, String type) {
        this.id = id;
        this.calories = calories;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}