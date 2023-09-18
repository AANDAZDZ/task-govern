package com.aanda.service;

/**
 * @author AANDA
 * @date 2023/9/19 0:26
 * @description
 */
public interface TaskGovernService {

    /**
     * 治理超时任务
     * @param taskType 任务类型
     */
    void governTimeoutTask(String taskType);
}
