package com.wwx.ssm.o2o.execution;

import com.wwx.ssm.o2o.entity.PersonInfo;
import com.wwx.ssm.o2o.enums.PersonInfoEnum;

import java.util.List;

public class PersonInfoExecution {

    private Integer state;

    private String stateInfo;

    private PersonInfo personInfo;

    private List<PersonInfo> personInfoList;

    public PersonInfoExecution(PersonInfoEnum infoEnum){
        this.stateInfo = infoEnum.getStateInfo();
        this.state = infoEnum.getState();
    }

    public PersonInfoExecution(PersonInfoEnum infoEnum,PersonInfo personInfo){
        this.stateInfo = infoEnum.getStateInfo();
        this.state = infoEnum.getState();
        this.personInfo = personInfo;
    }

    public PersonInfoExecution(PersonInfoEnum infoEnum,List<PersonInfo> personInfoList){
        this.stateInfo = infoEnum.getStateInfo();
        this.state = infoEnum.getState();
        this.personInfoList = personInfoList;
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

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public List<PersonInfo> getPersonInfoList() {
        return personInfoList;
    }

    public void setPersonInfoList(List<PersonInfo> personInfoList) {
        this.personInfoList = personInfoList;
    }
}
