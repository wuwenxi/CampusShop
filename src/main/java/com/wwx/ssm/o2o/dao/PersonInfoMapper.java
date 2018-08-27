package com.wwx.ssm.o2o.dao;


import com.wwx.ssm.o2o.entity.PersonInfo;

public interface PersonInfoMapper {
    /**
     *
     *    根据用户id 获取用户信息
     * @param id
     * @return
     */
    PersonInfo queryPersonInfoById(Integer id);
}