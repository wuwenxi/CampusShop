package com.wwx.ssm.o2o.service.impl;

import com.wwx.ssm.o2o.dao.PersonInfoMapper;
import com.wwx.ssm.o2o.entity.PersonInfo;
import com.wwx.ssm.o2o.enums.PersonInfoEnum;
import com.wwx.ssm.o2o.execution.PersonInfoExecution;
import com.wwx.ssm.o2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    PersonInfoMapper mapper;

    public PersonInfoExecution getPersonInfo(Integer id) {
        if(id > 0){
            try {
                PersonInfo personInfo = mapper.queryPersonInfoById(id);
                if(personInfo!=null){
                    return new PersonInfoExecution(PersonInfoEnum.SUCCESS,personInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new PersonInfoExecution(PersonInfoEnum.EMPTY);
    }
}
