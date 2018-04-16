package com.bays.model;

import java.io.Serializable;

public class itemInfo implements Serializable {
    private int id;
    private String number;
    private String logo;
    private String name;
    private int type;
//    private String size;
    private int is_online;
    private int sex;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

//    public String getSize() {
//        return size;
//    }
//
//    public void setSize(String size) {
//        this.size = size;
//    }

    public int getIs_online() {
        return is_online;
    }

    public void setIs_online(int is_online) {
        this.is_online = is_online;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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
//                ", size='" + size + '\'' +
                ", is_online=" + is_online +
                ", sex=" + sex +
                ", description='" + description + '\'' +
                '}';
    }
}
