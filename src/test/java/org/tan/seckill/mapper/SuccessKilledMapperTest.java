package org.tan.seckill.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.tan.seckill.po.SuccessKilled;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Author: Redinw
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class SuccessKilledMapperTest {

    @Resource
    SuccessKilledMapper successKilledMapper;

    @Test
    public void insertSuccessKilled() throws Exception {
        SuccessKilled successKilled = new SuccessKilled();
        successKilled.setSeckillId(1001L);
        successKilled.setUserPhone(13671285902L);
        successKilled.setState(new Byte("0"));
        int num = successKilledMapper.insert(successKilled);
        log.info("num:"+num);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(1001L,13671285902L);
        log.info(successKilled.toString());
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}