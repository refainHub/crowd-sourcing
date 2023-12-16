package cn.sfcoder.vo.http;

import lombok.Getter;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:11
 * @Version: 1.0
 */
@Getter
public enum WorkHttpsStatus implements HttpStatus {

    WORK_DUPLICATE_PART(6001, "工作重复参加"), TASK_OVERTIME(6002, "任务已过期"),
    WORK_NEVER_PART(6003,"未参加该工作");

    WorkHttpsStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;
}