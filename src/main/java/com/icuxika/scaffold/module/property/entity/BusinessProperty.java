package com.icuxika.scaffold.module.property.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BusinessProperty {
    private Long id;

    private Date date;

    private Date createTime;

    private Date updateTime;

    private BigDecimal price;

    private Integer isDeleted;

    private Date detailDate;

    private Boolean isAvailable;

    private Integer isUnsigned;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDetailDate() {
        return detailDate;
    }

    public void setDetailDate(Date detailDate) {
        this.detailDate = detailDate;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Integer getIsUnsigned() {
        return isUnsigned;
    }

    public void setIsUnsigned(Integer isUnsigned) {
        this.isUnsigned = isUnsigned;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BusinessProperty{" +
                "id=" + id +
                ", date=" + date +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", price=" + price +
                ", isDeleted=" + isDeleted +
                ", detailDate=" + detailDate +
                ", isAvailable=" + isAvailable +
                ", isUnsigned=" + isUnsigned +
                ", type=" + type +
                '}';
    }
}