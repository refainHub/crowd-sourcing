package cn.sfcoder.annotation;

import java.lang.annotation.*;

/**
 * @Author: refain
 * @Description:  自定义判断任务类型的注解
 * @Date: 2023/11/17 22:57
 * @Version: 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Judge {
    double mul() default 1;

    double add() default 0;

    double pow() default 1;

    String desc() default "无说明";
}