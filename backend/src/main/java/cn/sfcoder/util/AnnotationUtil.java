package cn.sfcoder.util;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:00
 * @Version: 1.0
 */
public class AnnotationUtil {
    public static String convert(String s) {
        if (s == null) {
            return "//";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('/');
        for (char c : s.toCharArray()) {
            if (c == '\n') {
                if (stringBuilder.charAt(stringBuilder.length() - 1) != '/') {
                    stringBuilder.append('/');
                }
            } else {
                stringBuilder.append(c);
            }
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) != '/') {
            stringBuilder.append('/');
        }
        return stringBuilder.toString();
    }
}