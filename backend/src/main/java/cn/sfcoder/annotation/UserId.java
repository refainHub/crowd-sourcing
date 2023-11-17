package cn.sfcoder.annotation;

import java.lang.annotation.*;


/**
 * 注解类
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserId {

}