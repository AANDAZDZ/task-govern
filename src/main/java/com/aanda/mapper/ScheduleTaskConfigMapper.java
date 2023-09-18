package com.aanda.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author AANDA
 * @date 2023/9/19 0:28
 * @description
 */
@Mapper
public interface ScheduleTaskConfigMapper {
    Integer getMaxProcessTimeByType(@Param("taskType") String taskType);
}
