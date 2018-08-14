package org.tan.seckill.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tan.seckill.SeckillApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeckillApplication.class)
@Slf4j
public class RedisOperatorTest {

    @Autowired
    private RedisOperator redisOperator;

    @Test
    public void incr() {
    }

    @Test
    public void keys() {
    }

    @Test
    public void del() {
    }

    @Test
    public void set() {
        redisOperator.set("zhangsan","20");
        String age = redisOperator.get("zhangsan");
        log.info("age={}",age);
    }

    @Test
    public void hset() {
    }

    @Test
    public void hget() {
    }
}