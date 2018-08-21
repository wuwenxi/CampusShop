package com.wwx.ssm.o2o.entity;

import java.util.Date;

/**
 *    店铺类别
 * */
public class ProductCategory {
    //商品Id
    private Integer productCategoryId;
    //商品名
    private String productCategoryName;
    //权值
    private Integer priority;
    //创建时间
    private Date createTime;
    //商铺id
    private Integer shopId;

    public ProductCategory() {
    }

    public ProductCategory(Integer productCategoryId, String productCategoryName,
                           Integer priority, Date createTime, Integer shopId) {
        this.productCategoryId = productCategoryId;
        this.productCategoryName = productCategoryName;
        this.priority = priority;
        this.createTime = createTime;
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "productCategoryId=" + productCategoryId +
                ", productCategoryName='" + productCategoryName + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", shopId=" + shopId +
                '}';
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}