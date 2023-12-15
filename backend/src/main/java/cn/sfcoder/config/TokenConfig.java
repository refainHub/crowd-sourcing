package cn.sfcoder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/14 17:53
 * @Version: 1.0
 */


@Component
@ConfigurationProperties(prefix = "token")
@Data
public class TokenConfig {

    private String secretKey = "12345678";

    private long ttl = 1800000L;

}