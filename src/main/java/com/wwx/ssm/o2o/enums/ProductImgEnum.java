package com.wwx.ssm.o2o.enums;

public enum ProductImgEnum {

    SUCCESS(1,"操作成功"),INNER_ERROR(-1001,"发生异常"),ERROR(-1002,"操作失败");

    private Integer status;

    private String statusInfo;

    ProductImgEnum() {
    }

    ProductImgEnum(Integer status, String statusInfo) {
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

    public static ProductImgEnum statusOf(Integer status){
        for(ProductImgEnum productImgEnum:values()){
            if(productImgEnum.status.equals(status)){
                return productImgEnum;
            }
        }
        return null;
    }
}
