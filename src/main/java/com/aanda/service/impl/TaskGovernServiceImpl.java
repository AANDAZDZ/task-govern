package com.aanda.service.impl;

import com.aanda.enums.TaskStatus;
import com.aanda.mapper.SchedulePositionMapper;
import com.aanda.mapper.ScheduleTaskConfigMapper;
import com.aanda.mapper.ScheduleTaskMapper;
import com.aanda.model.ScheduleTask;
import com.aanda.service.TaskGovernService;
import com.aanda.utils.TaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author AANDA
 * @date 2023/9/19 0:27
 * @description
 */
@Service
@Slf4j
public class TaskGovernServiceImpl implements TaskGovernService {

    @Resource
    private ScheduleTaskConfigMapper scheduleTaskConfigMapper;

    @Resource
    private SchedulePositionMapper schedulePositionMapper;

    @Resource
    private ScheduleTaskMapper scheduleTaskMapper;

    private static final Integer limitCount = 1;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void governTimeoutTask(String taskType) {
        if (Objects.isNull(taskType)) {
            log.error("任务类型不能为空");
        }
        Integer maxProcessTime = scheduleTaskConfigMapper.getMaxProcessTimeByType(taskType);
        if (Objects.isNull(maxProcessTime)) {
            log.error("任务类型:{}不存在", taskType);
        }
        Integer begin = schedulePositionMapper.getBeginTableIdx(taskType);
        String tableName = TaskUtil.getTableName(taskType, begin);
        List<ScheduleTask> timeoutTasks = scheduleTaskMapper.getTimeoutTasks(TaskStatus.EXECUTING.getStatus(),
                tableName, System.currentTimeMillis() - maxProcessTime.longValue(), limitCount);
        timeoutTasks = timeoutTasks.stream().map(this::handleTimeoutTasks).collect(Collectors.toList());
        Integer cnt = scheduleTaskMapper.batchUpdateGovernTask(tableName, TaskStatus.FAIL.getStatus(),
                TaskStatus.EXECUTING.getStatus(), timeoutTasks);
        log.info("处理{}条超时任务",cnt);
    }

    private ScheduleTask handleTimeoutTasks(ScheduleTask task) {
        Long now = System.currentTimeMillis();
        if (task.getCurrentRetryNum().equals(task.getMaxRetryNum())) {
            task.setStatus(TaskStatus.FAIL.getStatus());
            task.setModifyTime(now);
        } else {
            task.setStatus(TaskStatus.PENDING.getStatus());
            task.setCurrentRetryNum(task.getCurrentRetryNum() + 1);
            task.setOrderTime(TaskUtil.calOrderTime(now, task.getRetryInterval(), task.getCurrentRetryNum()));
            task.setModifyTime(now);
        }
        return task;
    }
}
