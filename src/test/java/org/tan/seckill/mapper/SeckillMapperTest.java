package org.tan.seckill.mapper;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tan.seckill.SeckillApplication;
import org.tan.seckill.po.Seckill;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


/**
 * Author: Redinw
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeckillApplication.class)
@Slf4j
public class SeckillMapperTest {

    @Autowired
    private SeckillMapper seckillMapper;

    @Test
    public void reduceNumber() throws Exception {
        long id = 1000L;
        int rowindex = seckillMapper.reduceNumber(id,new Date());
        Seckill seckill = seckillMapper.selectByPrimaryKey(id);
        log.info(seckill.toString());
    }

    //Seckill(seckillId=1000, name=1000元秒杀iphone6, number=99, startTime=Fri Jan 01 00:00:00 CST 2016, endTime=Fri May 25 00:00:00 CST 2018, createTime=Sun Apr 08 11:12:42 CST 2018)
    @Test
    public void queryById() throws Exception {
        long id = 1000L;
        Seckill seckill = seckillMapper.selectByPrimaryKey(id);
        log.info(seckill.toString());
    }

    @Test
    public void queryAll() throws Exception {
        // 开始分页
        PageHelper.startPage(0, 5);
        Example example = new Example(Seckill.class);
        example.orderBy("createTime").desc();
        List<Seckill> userList = seckillMapper.selectByExample(example);
        for(Seckill seckill:userList){
            log.info(seckill.toString());
        }

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void killByProcedure() {
    }
}