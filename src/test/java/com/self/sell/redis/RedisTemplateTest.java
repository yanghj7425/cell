package com.self.sell.redis;

import cn.hutool.core.lang.Assert;
import com.self.sell.common.component.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void setString() {
        boolean set = redisUtils.set("abc", "asdfasdf");
        Assert.isTrue(set == true, "set 操作失败");
    }


    @Test
    public void setTemplate() {
        redisTemplate.opsForValue().set("sss", "你好");
    }


}
