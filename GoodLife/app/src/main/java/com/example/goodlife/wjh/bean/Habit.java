package com.example.goodlife.wjh.bean;

public class Habit {
    private String name;
    private Integer img;
    private Integer color;

    public Habit(String name, Integer img, Integer color) {
        this.name = name;
        this.img = img;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}
