package cn.sfcoder.vo.http;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/11/17 20:51
 * @Version: 1.0
 */
import lombok.Getter;

@Getter
public enum UserHttpStatus implements HttpStatus {

    USER_REPEAT(4001, "邮箱已存在"),

    USER_NOT_EXIST(4002, "用户不存在"),

    PASSWD_ERROR(4003, "密码错误"),

    DATA_ERROR(4004, "数据格式错误"),

    CODE_ERROR(4005, "激活码错误"),
    USER_TOKEN_ERROR(4006, "未登录"),

    USER_PRIVILEGE_ERROR(4007, "无操作权限");

    UserHttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;

}