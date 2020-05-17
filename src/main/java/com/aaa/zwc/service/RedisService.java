package com.aaa.zwc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;


/**
 * user : 秀仔
 * Data: 2020/3/24
 * @author 19435
 */
@Service
public class RedisService {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 获取redis的数据
     * @param key
     * @return
     */
    public String get(String key){
        return jedisCluster.get(key);
    }

    /**
     * 往redis中存数据成功返回OK
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) throws RedisSystemException {
        //先判断redis中是否已存在key值存在true不存在false
        Boolean exists = this.isExists(key);
        if (!exists){
            //不存在则可以使用当前key
            return jedisCluster.set(key, value);
        }else {
            //存在则抛出异常
            throw new RedisSystemException("请使用其它key值",new Throwable("当前使用的key值已存在"));
        }

    }

    /**
     * 设置缓存数据与该数据的失效时间单位秒
     * @param key
     * @param value
     * @param seconds
     * @return
     * @throws RedisSystemException
     */
    public String setAndExpireSeconds(String key, String value,Integer seconds) throws RedisSystemException {
        //先判断redis中是否已存在key值存在true不存在false
        Boolean exists = this.isExists(key);
        if (!exists){
            //不存在则可以使用当前key存放数据
            String result = jedisCluster.set(key, value);
            //设置失效时间
            jedisCluster.expire(key,seconds);
            return result;
        }else {
            //存在则抛出异常
            throw new RedisSystemException("请使用其它key值",new Throwable("当前使用的key值已存在"));
        }

    }

    /**
     * 设置缓存数据与该数据的失效时间单位分钟
     * @param key
     * @param value
     * @param seconds
     * @return
     * @throws RedisSystemException
     */
    public String setAndExpireMinute(String key, String value,Integer seconds) throws RedisSystemException {
        //先判断redis中是否已存在key值存在true不存在false
        Boolean exists = this.isExists(key);
        if (!exists){
            //不存在则可以使用当前key存放数据
            String result = jedisCluster.set(key, value);
            //设置失效时间
            jedisCluster.expire(key,seconds*60);
            return result;
        }else {
            //存在则抛出异常
            throw new RedisSystemException("请使用其它key值",new Throwable("当前使用的key值已存在"));
        }

    }

    /**
     * 覆盖方法只有key存在时才能覆盖
     * @param key
     * @param value
     * @return
     */
    public String cover(String key, String value){

        //先判断redis中是否已存在key值存在true不存在false
        Boolean exists = this.isExists(key);
        if (exists){
            //存在则可以重新设定该key的value值
            return jedisCluster.set(key, value);
        }else {
            //不存在则抛出异常
            throw new RedisSystemException("请找到对应的key值",new Throwable("当前使用的key值不存在"));
        }
    }

    /**
     * 删除key的数据
     * @param key
     * @return
     */
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    /**
     * 通过key值设置失效时间
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, Integer seconds) {
        return jedisCluster.expire(key, seconds);
    }

    /**
     * 查看redis中的key是否已存在
     * 存在true
     * 不存在false
     * @param key
     * @return
     */
    public Boolean isExists(String key){
        return jedisCluster.exists(key);
    }
}

