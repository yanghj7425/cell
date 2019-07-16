package com.self.sell.common.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("ConstantConditions")
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private static final String LETTER = "【 redis 操作 】";


    /**
     * <pre>
     *     设置过期时间
     * </pre>
     *
     * @param key  所设置的 key
     * @param time 时间(秒)
     * @return 是否设置成功
     */
    public boolean expire(String key, long time) {
        try {
            Boolean expire = redisTemplate.expire(key, time, TimeUnit.SECONDS);
            if (!expire) {
                log.warn("{}, 过期时间设置失败, 当前 key 不存在, key = {}", LETTER, key);
            }
            return expire;

        } catch (Exception e) {
            log.error("{}, 设置出现错误 key = {}", LETTER, key);
            return false;
        }
    }


    /**
     * <pre>
     *     获得 key 的过期时间
     * </pre>
     *
     * @param key 所查询的　key
     * @return 过期时间
     */
    public long getExpire(String key) {
        if (StringUtils.isEmpty(key)) {
            return -2;
        }
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * <pre>
     *     设置 值
     * <p/>
     *      true 成功, false 失败
     * </pre>
     *
     * @param key   键
     * @param value 值
     * @return 成功标识
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("{} set 操作失败，message = {}", LETTER, e.getMessage(), e);
            return false;
        }
    }


    /**
     * <pre>
     *     redis 锁
     * </pre>
     *
     * @param key   要锁住的键
     * @param value 当前时间 + 超时时间
     * @return 是否获得锁
     */
    public boolean lock(String key, String value) {

        boolean isAbsent = redisTemplate.opsForValue().setIfAbsent(key, value);
        // 如果不存在直接获得锁
        if (isAbsent) {
            return true;
        }

        String currentValue = stringRedisTemplate.opsForValue().get(key);
        // 如果锁过期
        if (!StringUtils.isEmpty(currentValue) &&
                Long.parseLong(currentValue) < System.currentTimeMillis()) {
            // 获取上一个锁的时间
            Object oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);

            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }


    /**
     * @param key   key
     */
    public void unlock(String key) {
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue)) {
            redisTemplate.delete(key);
        }
    }


}
