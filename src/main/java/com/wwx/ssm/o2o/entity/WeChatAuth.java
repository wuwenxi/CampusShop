package com.wwx.ssm.o2o.entity;

import java.util.Date;


/**
 *  微信账号
 */
public class WeChatAuth {
    //微信编号
    private Integer wechatAuthId;
    //关联用户
    private PersonInfo personInfo;
    //微信号与公众号绑定的标识
    private String openId;
    //创建时间
    private Date createTime;

    public Integer getWechatAuthId() {
        return wechatAuthId;
    }

    public void setWechatAuthId(Integer wechatAuthId) {
        this.wechatAuthId = wechatAuthId;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}