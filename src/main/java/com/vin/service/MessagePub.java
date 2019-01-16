package com.vin.service;

import org.springframework.data.redis.core.RedisTemplate;


public interface MessagePub {

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate);

    public void convertAndSend(String channel, Object msg) ;
}
