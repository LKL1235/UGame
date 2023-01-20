package com.myApp.gamestore.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author:25445
 * @date:2022/11/24 19:00
 **/
@Service
public class IsLoginVerifyService {
    private final RedisTemplate redisTemplate;

    public IsLoginVerifyService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void setLoginState(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public String getLoginState(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }
}
