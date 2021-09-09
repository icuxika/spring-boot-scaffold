package com.icuxika.scaffold.module.activiti.entity;

import java.io.Serializable;

public class SmallTask implements Serializable {

    private Integer type;

    private Integer number;

    public SmallTask() {
    }

    public SmallTask(Integer type, Integer number) {
        this.type = type;
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
