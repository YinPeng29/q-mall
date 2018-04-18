package com.bays.model;

import java.io.Serializable;

public class ItemInfo implements Serializable {
    private String id;
    private String number;
    private String logo;
    private String name;
    private String type;
    private String size;
    private String is_online;
    private String sex;
    private String price;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getIs_online() {
        return is_online;
    }

    public void setIs_online(String is_online) {
        this.is_online = is_online;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "itemInfo{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", logo='" + logo + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", size='" + size + '\'' +
                ", is_online=" + is_online +
                ", sex=" + sex +
                ", description='" + description + '\'' +
                '}';
    }
}