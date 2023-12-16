package cn.sfcoder.util.strategy;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:40
 * @Version: 1.0
 */
@Component
public class BuilderStrategy implements PathEncryptStrategy {
    @Override
    public String getPath(Object... path) {
        StringBuilder s = new StringBuilder();
        for (Object o : path) {
            s.append(o).append(File.separator);
        }
        return s.toString();
    }
}