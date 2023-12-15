package cn.sfcoder.po.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 19:46
 * @Version: 1.0
 */
@Getter
public enum WorkStatus {
    TO_FINISH(0, "待完成"), FINISH(1, "完成"), FAIL(2, "失败");

    WorkStatus(int code, String status) {
        this.code = code;
        this.status = status;
    }

    @EnumValue
    private final int code;

    @JsonValue
    private final String status;
}