package cn.sfcoder.exception;

import cn.sfcoder.vo.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 20:06
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class MyException extends RuntimeException {
    HttpStatus httpStatus;
}