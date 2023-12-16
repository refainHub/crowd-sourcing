package cn.sfcoder.util.strategy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.io.File;
import cn.sfcoder.util.Md5Util;
/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 19:54
 * @Version: 1.0
 */
@Qualifier
@Component
public class MD5Strategy implements PathEncryptStrategy {
    @Override
    public String getPath(Object... path) {
        StringBuilder s = new StringBuilder();
        for (Object o : path) {
            s.append(o);
        }
        return Md5Util.md5(s.toString()) + File.separator;
    }
}