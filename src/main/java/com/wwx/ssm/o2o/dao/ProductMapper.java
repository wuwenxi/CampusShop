package com.wwx.ssm.o2o.dao;

import com.wwx.ssm.o2o.entity.Product;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {

    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}