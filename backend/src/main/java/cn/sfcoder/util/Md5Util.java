package cn.sfcoder.util;

import org.springframework.util.DigestUtils;

/**
 * @Author: refain
 * @Description:  采用MD5加密方式（信息摘要算法）
 * @Date: 2023/11/18 16:17
 * @Version: 1.0
 */
public class Md5Util {
    public static String md5(String msg) {
        return DigestUtils.md5DigestAsHex((msg).getBytes());
    }

    public static String md5(String msg, String salt) {
        return DigestUtils.md5DigestAsHex((msg + salt).getBytes());
    }

    public static boolean verify(String msg, String salt, String code) {
        return code.equals(md5(msg, salt));
    }

    public static boolean verify(String msg, String code) {
        return code.equals(md5(msg));
    }

}
