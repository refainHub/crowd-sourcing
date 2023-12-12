package cn.sfcoder.annotation;

import cn.sfcoder.po.enums.UserIdentity;

import java.lang.annotation.*;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/6 19:56
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckStatus {
    /**
     * 检验用户身份
     * @return
     */
    UserIdentity identity() default UserIdentity.ALL;

    /**
     * 决定用户userId注入方式
     * @return
     */
    InsertMode insert() default InsertMode.ENTITY;

    /**
     * NONE: 不注入，
     * ENTITY: 注入controller层参数，
     * THREAD：注入ThreadLocal
     */
    enum InsertMode {
        NONE, ENTITY, THREAD
    }
}