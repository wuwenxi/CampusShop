package com.wwx.ssm.o2o.entity;

import java.util.Date;

/**
 *   商铺图片
 * */
public class  ProductImg {
    //商品图片id
    private Integer productImgId;
    //图片地址
    private String imgAddress;
    //图片详情
    private String imgDesc;
    //权值
    private Integer priority;
    //创建时间
    private Date createTime;
    //商品id
    private Integer productId;

    public ProductImg() {
    }

    public ProductImg(Integer productImgId, String imgAddress,
                      String imgDesc, Integer priority, Date createTime, Integer productId) {
        this.productImgId = productImgId;
        this.imgAddress = imgAddress;
        this.imgDesc = imgDesc;
        this.priority = priority;
        this.createTime = createTime;
        this.productId = productId;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public Integer getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Integer productImgId) {
        this.productImgId = productImgId;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc == null ? null : imgDesc.trim();
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}