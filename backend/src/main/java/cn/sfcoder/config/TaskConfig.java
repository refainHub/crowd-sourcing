package cn.sfcoder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 19:51
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "task")
@Data
public class TaskConfig {

    private int limit = 1000;

    private int filter = 200;

}