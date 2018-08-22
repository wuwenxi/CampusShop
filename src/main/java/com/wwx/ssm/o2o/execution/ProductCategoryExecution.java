package com.wwx.ssm.o2o.execution;

import com.wwx.ssm.o2o.entity.ProductCategory;
import com.wwx.ssm.o2o.enums.ProductCategoryEnum;

import java.util.List;

public class ProductCategoryExecution {
    //状态
    private Integer status;
    //状态标识
    private String statusInfo;
    //需要操作的商品类别
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution(){}

    //操作失败
    public ProductCategoryExecution(ProductCategoryEnum categoryEnum){
        this.status = categoryEnum.getStatus();
        this.statusInfo = categoryEnum.getStatusInfo();
    }

    //操作成功
    public ProductCategoryExecution(ProductCategoryEnum categoryEnum,
                                    List<ProductCategory> productCategoryList){
        this.status = categoryEnum.getStatus();
        this.statusInfo = categoryEnum.getStatusInfo();
        this.productCategoryList = productCategoryList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
