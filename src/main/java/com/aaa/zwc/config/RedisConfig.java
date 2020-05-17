package com.aaa.zwc.config;/**
 * @Date: 2020-05-09 21:22
 * @Author: 秀仔
 * @Description
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;

/**
 * user : 秀仔
 * Data: 2020/5/9
 */
@Configuration
public class RedisConfig {
    @Autowired
    private RedisProperties redisProperties;
    @Bean
    public JedisCluster getJedisCluster(){
        String nodes = redisProperties.getNodes();
        String[] split = nodes.split(",");
        HashSet<HostAndPort> hostAndPortSet = new HashSet<HostAndPort>();
        for (String hostPort : split){
            String[] ipPort = hostPort.split(":");
            HostAndPort hostAndPort = new HostAndPort(ipPort[0], Integer.parseInt(ipPort[1]));
            hostAndPortSet.add(hostAndPort);
        }
        return new JedisCluster(hostAndPortSet,Integer.parseInt(redisProperties.getCommandTimeout()),Integer.parseInt(redisProperties.getMaxAttempts()));
    }
}
