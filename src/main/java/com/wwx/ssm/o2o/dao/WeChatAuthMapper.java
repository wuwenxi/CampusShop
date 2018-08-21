package com.wwx.ssm.o2o.dao;

import com.wwx.ssm.o2o.entity.WeChatAuth;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeChatAuthMapper {

    int deleteByPrimaryKey(Integer wechatAuthId);

    int insert(WeChatAuth record);

    int insertSelective(WeChatAuth record);

    WeChatAuth selectByPrimaryKey(Integer wechatAuthId);

    int updateByPrimaryKeySelective(WeChatAuth record);

    int updateByPrimaryKey(WeChatAuth record);
}