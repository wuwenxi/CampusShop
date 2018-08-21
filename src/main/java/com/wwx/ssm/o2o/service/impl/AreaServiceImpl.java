package com.wwx.ssm.o2o.service.impl;

import com.wwx.ssm.o2o.dao.AreaMapper;
import com.wwx.ssm.o2o.entity.Area;
import com.wwx.ssm.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaMapper mapper;

    public List<Area> getAreaList() {
        return mapper.queryAllArea();
    }
}
