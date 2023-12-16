package cn.sfcoder.util.strategy;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 19:58
 * @Version: 1.0
 */
public interface PathEncryptStrategy {
    /**
     * 将路径拼接为加密路径
     * @param path，多个路径
     * @return 加密后的路径
     */
    String getPath(Object... path);
}