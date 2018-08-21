package com.wwx.ssm.o2o.utils;

/**
 *   转换页码
 */
public class PageCalculator {
    //将页码转换为行数
    public static int calculatorRowIndex(int pageIndex,int pageSize){
        //(pageIndex-1) * pageSize 表示在数据库中查询行数的起始位置
        //如：若pageIndex 为1，(pageIndex-1) * pageSize = 0 ，从第一条数据开始查
        // 若pageIndex 为2，(pageIndex-1) * pageSize = 5 ，从第六条数据开始查  依次类推
        return (pageIndex > 0) ? (pageIndex-1) * pageSize : 0;
    }

}
