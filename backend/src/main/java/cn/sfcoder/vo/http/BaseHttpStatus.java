package cn.sfcoder.vo.http;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/11/17 20:55
 * @Version: 1.0
 */
import lombok.Getter;

@Getter
public enum BaseHttpStatus implements HttpStatus {
    COMMON_OK(4000, "ok"),
    FILE_PATH_ERROR(6001, "文件路径错误"),
    FILE_CANNOT_LOAD(6002, "文件读取失败"),
    FILE_CANNOT_SAVE(6003, "文件写入失败");

    BaseHttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;
}