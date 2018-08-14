package org.tan.seckill.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tan.seckill.SeckillApplication;
import org.tan.seckill.mapper.SeckillMapper;
import org.tan.seckill.po.Seckill;
import org.tan.seckill.utils.ProtoStuffUtil;

import javax.annotation.Resource;

/**
 * Author: Redinw
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeckillApplication.class)
@Slf4j
public class RedisDaoTest {
    private long id = 1001L;

    @Resource
    private RedisDao redisDao;

    @Resource
    private SeckillMapper seckillMapper;

    @Test
    public void testSeckill() throws Exception {
        Seckill seckill = redisDao.getObject(id,Seckill.class);
        log.info("{}", seckill);
        if (seckill == null) {
            seckill = seckillMapper.selectByPrimaryKey(id);
            log.info("{}", seckill);
            if (seckill != null) {
                redisDao.putObject(seckill.getSeckillId(),seckill);
                seckill = redisDao.getObject(id,Seckill.class);
                log.info("{}", seckill);
            }

        }
    }

    @Test
    public void rss() throws Exception {
        Seckill seckill = seckillMapper.selectByPrimaryKey(id);
        log.info("{}", seckill);
        byte[] bytes = ProtoStuffUtil.serialize(seckill);
        log.info("lenth:{}",bytes.length);
        Seckill s = ProtoStuffUtil.deserialize(bytes, Seckill.class);
        log.info("{}", s);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}