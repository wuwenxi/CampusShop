package com.wwx.ssm.o2o.test.personInfo;

import com.wwx.ssm.o2o.dao.PersonInfoMapper;
import com.wwx.ssm.o2o.entity.PersonInfo;
import com.wwx.ssm.o2o.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonInfoTest extends BaseTest {


    @Autowired
    PersonInfoMapper mapper;
    @Test


    public void test01(){
        PersonInfo info = mapper.queryPersonInfoById(1);
        System.out.println(info);
    }
}
