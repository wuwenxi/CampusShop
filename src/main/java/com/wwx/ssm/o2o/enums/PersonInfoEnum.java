package com.wwx.ssm.o2o.enums;

public enum PersonInfoEnum {
    SUCCESS(1,"操作成功"),ERROR(-1001,"操作失败"),EMPTY(-1,"获取失败");

    private Integer state;

    private String stateInfo;

    PersonInfoEnum(Integer state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
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

    public static PersonInfoEnum stateOf(Integer index){
        for(PersonInfoEnum infoEnum:values()) {
            if (infoEnum.getState().equals(index)) {
                return infoEnum;
            }
        }
        return null;
    }
}
