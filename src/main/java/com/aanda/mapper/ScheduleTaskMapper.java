package com.aanda.mapper;

import com.aanda.model.ScheduleTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author AANDA
 * @date 2023/9/19 21:11
 * @description
 */
@Mapper
public interface ScheduleTaskMapper {

    List<ScheduleTask> getTimeoutTasks(@Param("taskStatus") Integer taskStatus,
                                       @Param("tableName") String tableName,
                                       @Param("timeout") Long timeout,
                                       @Param("limitCount") Integer limitCount);

    Integer batchUpdateGovernTask(@Param("tableName") String tableName,
                                  @Param("failStatus") Integer failStatus,
                                  @Param("executingStatus")Integer executingStatus,
                                  @Param("timeoutTasks") List<ScheduleTask> timeoutTasks);
}
