package com.aanda.model;

import lombok.Data;

/**
 * @author AANDA
 * @date 2023/9/19 0:25
 * @description
 */
@Data
public class ScheduleConfig {

    private String taskType;

    private Integer scheduleLimit;

    private Integer scheduleInterval;

    private Integer maxProcessingTime;

    private Integer maxRetryNum;

    private Integer retryInterval;

    private Long createTime;

    private Long modifyTime;
}
