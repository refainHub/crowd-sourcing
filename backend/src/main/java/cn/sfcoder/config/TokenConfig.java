package cn.sfcoder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/11/18 16:38
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "token")
@Data
public class TokenConfig {

    /**
     * 令牌密钥
     */
    private String secretKey = "12345678";

    /**
     * 失效时间
     */
    private long ttl = 1800000L;
}