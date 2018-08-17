//package org.tan.seckill.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.tan.seckill.mapper.RedisDao;
//import org.tan.seckill.core.dto.Exposer;
//import org.tan.seckill.core.dto.SeckillResult;
//import org.tan.seckill.po.Seckill;
//
//import java.util.List;
//
///**
// * Author: Redinw
// * Description:
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
//public class ISeckillServiceTest {
//
//    @Autowired
//    private ISeckillService seckillService;
//
//    @Autowired
//    private RedisDao redisDao;
//
//    //list=Page{count=true, pageNum=1, pageSize=5, startRow=0, endRow=5, total=4, pages=1, reasonable=true, pageSizeZero=false}
//    @Test
//    public void queryAll() throws Exception {
//        List<Seckill> list = seckillService.list(0, 5);
//        log.info("list={}", list);
//    }
//
//    @Test
//    public void testSeckillList() throws Exception {
//        List<Seckill> list = seckillService.list(0, 5);
//        log.info("list={}", list);
//        if (list != null) {
//            redisDao.putObjectList("list", list);
//            list = redisDao.getObjectList("list", Seckill.class);
//            log.info("{}", list);
//        }
//
//    }
//
//    //seckill=Seckill(seckillId=1000, name=1000元秒杀iphone6, number=98, startTime=Fri Jan 01 00:00:00 CST 2016, endTime=Fri Aug 31 00:00:00 CST 2018, createTime=Sun Apr 08 11:12:42 CST 2018)
//    @Test
//    public void queryById() throws Exception {
//        Seckill seckill = seckillService.getById(1000L);
//        log.info("seckill={}", seckill);
//    }
//
//    //expser=Exposer(exposed=true, md5=100010459d1ff8c1056f5adc9ec5c466fb63, seckillId=1000, nowTime=0, startTime=0, endTime=0)
//    @Test
//    public void exoportSeckillUrl() throws Exception {
//        long id = 1000;
//        Exposer exposer = seckillService.exoportSeckillUrl(1000L);
//        log.info("expser={}", exposer);
//    }
//
//
//    //seckillExcution=SeckillResult(seckillId=1000, state=1, stateInfo=秒杀成功, successKilled=SuccessKilled(seckillId=1000, userPhone=13783425232, state=0, createTime=Thu Jun 14 21:48:45 CST 2018, seckill=Seckill(seckillId=1000, name=1000元秒杀iphone6, number=97, startTime=Fri Jan 01 00:00:00 CST 2016, endTime=Fri Aug 31 00:00:00 CST 2018, createTime=Sun Apr 08 11:12:42 CST 2018)))
//    @Test
//    public void executeSeckill() throws Exception {
//        SeckillResult seckillExcution = seckillService.executeSeckill(1000L, 13783425232L, "100010459d1ff8c1056f5adc9ec5c466fb63");
//        log.info("seckillExcution={}", seckillExcution);
//    }
//
//    @Test
//    public void executeSeckillProcedure() {
//        long seckillId = 1000L;
//        long phone = 13680111011L;
//        Exposer exposer = seckillService.exoportSeckillUrl(seckillId);
//        if (exposer.isExposed()) {
//            String md5 = exposer.getMd5();
//            SeckillResult execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
//            log.info(execution.getStateInfo());
//        }
//    }
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//}