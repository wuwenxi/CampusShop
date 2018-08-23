package com.wwx.ssm.o2o.execution;

import com.wwx.ssm.o2o.entity.ProductCategory;
import com.wwx.ssm.o2o.enums.ProductCategoryEnum;

import java.util.List;

public class ProductCategoryExecution {
    //状态
    private Integer state;
    //状态标识
    private String stateInfo;
    //需要操作的商品类别
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution(){}

    //操作失败
    public ProductCategoryExecution(ProductCategoryEnum categoryEnum){
        this.state = categoryEnum.getStatus();
        this.stateInfo = categoryEnum.getStatusInfo();
    }

    //操作成功
    public ProductCategoryExecution(ProductCategoryEnum categoryEnum,
                                    List<ProductCategory> productCategoryList){
        this.state = categoryEnum.getStatus();
        this.stateInfo = categoryEnum.getStatusInfo();
        this.productCategoryList = productCategoryList;
    }

    public Integer getStatus() {
        return state;
    }

    public void setStatus(Integer status) {
        this.state = status;
    }

    public String getStatusInfo() {
        return stateInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.stateInfo = statusInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
