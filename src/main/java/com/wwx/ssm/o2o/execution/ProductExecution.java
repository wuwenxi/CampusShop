package com.wwx.ssm.o2o.execution;

import com.wwx.ssm.o2o.entity.Product;
import com.wwx.ssm.o2o.enums.ProductEnum;

import java.util.List;

public class ProductExecution {
    //状态
    private Integer state;
    //状态信息
    private String stateInfo;
    //商品数量
    private Integer count;
    //商品
    private Product product;
    //商品列表
    private List<Product> productList;

    public ProductExecution() {
    }

    public ProductExecution(ProductEnum productEnum) {
        this.state = productEnum.getStatus();
        this.stateInfo = productEnum.getStatusInfo();
    }

    public ProductExecution(ProductEnum productEnum,Product product){
        this.state = productEnum.getStatus();
        this.stateInfo = productEnum.getStatusInfo();
        this.product = product;
    }

    public ProductExecution(ProductEnum productEnum, List<Product> productList) {
        this.state = productEnum.getStatus();
        this.stateInfo = productEnum.getStatusInfo();
        this.productList = productList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
