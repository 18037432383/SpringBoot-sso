package com.aaa.zwc.config;/**
 * @Date: 2020-05-09 21:23
 * @Author: 秀仔
 * @Description
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * user : 秀仔
 * Data: 2020/5/9
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties implements Serializable {
    private String nodes;
    private String maxAttempts;
    private String expire;
    private String commandTimeout;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(String maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(String commandTimeout) {
        this.commandTimeout = commandTimeout;
    }
}
