package com.aanda.utils;

/**
 * @author AANDA
 * @date 2023/9/19 22:13
 * @description
 */
public class TaskUtil {

    public static String getTableName(String taskType, Integer idx) {
        return "task_" + taskType.toLowerCase() + "_" + idx;
    }

    public static Long calOrderTime(Long now, Integer maxRetryInterval, Integer retryTime) {
        long delay;
        if (maxRetryInterval > 0) {
            delay = Math.min( (1L << retryTime - 1),maxRetryInterval);
        } else {
            delay = - maxRetryInterval.longValue();
        }
        return now + delay * 1000;
    }
}
