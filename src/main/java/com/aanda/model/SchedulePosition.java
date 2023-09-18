package com.aanda.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AANDA
 * @date 2023/9/19 0:24
 * @description
 */
@Data
public class SchedulePosition implements Serializable {

    private Long id;

    private String taskType;

    private Integer scheduleBeginPos;

    private Integer scheduleEndPos;

    private Long createTime;

    private Long modifyTime;
}