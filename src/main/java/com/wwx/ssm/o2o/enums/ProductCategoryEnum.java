package com.wwx.ssm.o2o.enums;

public enum ProductCategoryEnum {
    SUCCESS(1,"操作成功"),ERROR(-1001,"系统出错"),EMPTY_LIST(-1002,"添加数量小于1");
    private Integer status;

    private String statusInfo;

    ProductCategoryEnum() {
    }

    ProductCategoryEnum(Integer status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
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

    public static ProductCategoryEnum stateOf(Integer index){
        for(ProductCategoryEnum categoryEnum : values()){
            if(categoryEnum.getStatus().equals(index)){
                return categoryEnum;
            }
        }
        return null;
    }
}
