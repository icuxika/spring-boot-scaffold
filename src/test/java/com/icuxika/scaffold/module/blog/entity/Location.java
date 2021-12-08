package com.icuxika.scaffold.module.blog.entity;

import java.io.Serializable;

public class Location implements Serializable {

    private String type = "Point";

    private Double[] coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }
}
