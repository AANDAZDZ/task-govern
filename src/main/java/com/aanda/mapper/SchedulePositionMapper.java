package com.aanda.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author AANDA
 * @date 2023/9/19 21:07
 * @description
 */
@Mapper
public interface SchedulePositionMapper {

    Integer getBeginTableIdx(@Param("taskType") String taskType);

    Integer getEndTableIdx(@Param("taskType") String taskType);
}
