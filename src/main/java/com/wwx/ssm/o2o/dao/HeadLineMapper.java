package com.wwx.ssm.o2o.dao;

import com.wwx.ssm.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineMapper {

    List<HeadLine> queryHeadLineList(@Param("headLine")HeadLine headLine);
}