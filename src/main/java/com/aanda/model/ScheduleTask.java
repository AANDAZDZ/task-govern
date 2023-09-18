package com.aanda.model;

import lombok.Data;

/**
 * @author AANDA
 * @date 2023/9/19 0:21
 * @description
 */
@Data
public class ScheduleTask {

    private String id;

    private String userId;

    private String taskType;

    private String taskStage;

    private Integer status;

    private Integer currentRetryNum;

    private Integer maxRetryNum;

    private Integer retryInterval;

    private Long orderTime;

    private Integer priority;

    private String scheduleLog;

    private String taskContext;

    private Long createTime;

    private Long modifyTime;

    public static String getTableName(String taskType, Integer idx) {
        return "task_" + taskType.toLowerCase() + "_" + idx;
    }
}
