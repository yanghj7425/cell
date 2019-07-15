package com.self.sell.common.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public boolean expire(String key, long time) {
        try {
            Boolean expire = redisTemplate.expire(key, time, TimeUnit.SECONDS);
            if (!expire) {
                log.warn("【 redis 过期时间设置 】, 当前 key 不存在, key = {}", key);
            }
            return expire;

        } catch (Exception e) {
            log.error("【 redis 过期时间设置 】 设置出现错误 key = {}", key);
            return false;
        }
    }


    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


}
