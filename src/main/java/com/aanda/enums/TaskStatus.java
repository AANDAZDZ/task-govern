package com.aanda.enums;

import lombok.Getter;

/**
 * @author AANDA
 * @date 2023/9/19 21:33
 * @description
 */
@Getter
public enum TaskStatus {

    PENDING(0),

    EXECUTING(1),

    SUCCESS(2),

    FAIL(3),
    ;

    private Integer status;

    private TaskStatus(Integer status) {
        this.status = status;
    }
}
