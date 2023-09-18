package com.aanda.service.impl;

import com.aanda.mapper.ScheduleTaskConfigMapper;
import com.aanda.service.TaskGovernService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AANDA
 * @date 2023/9/19 0:27
 * @description
 */
@Service
@Slf4j
public class TaskGovernServiceImpl implements TaskGovernService {

    @Autowired
    private ScheduleTaskConfigMapper scheduleTaskConfigMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void governTimeoutTask(String taskType) {
        if (taskType == null) {
            log.error("任务类型不能为空");
        }
        Integer maxProcessTime = scheduleTaskConfigMapper.getMaxProcessTimeByType(taskType);
        if (maxProcessTime == null) {
            log.error("任务类型不存在");
        }
    }
}
