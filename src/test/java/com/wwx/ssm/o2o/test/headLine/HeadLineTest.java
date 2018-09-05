package com.wwx.ssm.o2o.test.headLine;

import com.wwx.ssm.o2o.dao.HeadLineMapper;
import com.wwx.ssm.o2o.entity.HeadLine;
import com.wwx.ssm.o2o.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HeadLineTest extends BaseTest {

    @Autowired
    HeadLineMapper mapper;

    @Test
    public void testQueryHeadLine(){
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(1);
        List<HeadLine> lineList = mapper.queryHeadLineList(headLine);
        System.out.println(lineList.size());
    }
}
