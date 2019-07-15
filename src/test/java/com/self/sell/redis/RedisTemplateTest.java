package com.self.sell.redis;

import com.self.sell.common.component.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void setString() {
         redisUtils.getExpire("");
    }


}
