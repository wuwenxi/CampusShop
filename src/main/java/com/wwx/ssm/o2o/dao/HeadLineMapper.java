package com.wwx.ssm.o2o.dao;

import com.wwx.ssm.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineMapper {

    /**
     *
     * @param headLine
     * @return
     */
    List<HeadLine> queryHeadLineList(@Param("headLine")HeadLine headLine);

    /**
     *
     *   获取单条头条信息
     * @param lineId
     * @return
     */
    HeadLine queryHeadLineById(Integer lineId);
    /**
     *
     *    添加头条信息
     * @param headLine
     * @return
     */
    int addHeadLine(HeadLine headLine);

    /**
     *
     *    更新头条信息
     * @param headLine
     * @return
     */
    int updateHeadLine(HeadLine headLine);

    /**
     *
     *    删除头条信息
     * @param lineId
     * @return
     */
    int deleteHeadLine(Integer lineId);
}