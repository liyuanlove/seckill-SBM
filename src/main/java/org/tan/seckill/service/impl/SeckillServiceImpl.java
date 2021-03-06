package org.tan.seckill.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.tan.seckill.core.dto.Exposer;
import org.tan.seckill.core.dto.SeckillResult;
import org.tan.seckill.core.enums.SeckillStateEnum;
import org.tan.seckill.core.exception.RepeatKillException;
import org.tan.seckill.core.exception.SeckillClosedException;
import org.tan.seckill.core.exception.SeckillException;
import org.tan.seckill.core.redis.cache.RedisCacheDao;
import org.tan.seckill.mapper.SeckillMapper;
import org.tan.seckill.mapper.SuccessKilledMapper;
import org.tan.seckill.po.Seckill;
import org.tan.seckill.po.SuccessKilled;
import org.tan.seckill.service.ISeckillService;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Redinw
 * Description:
 */
@Slf4j
@Service
public class SeckillServiceImpl implements ISeckillService {

    public static final String salt = "test";

    @Autowired
    private SeckillMapper seckillMapper;
    @Autowired
    private SuccessKilledMapper successKilledMapper;
    //    @Autowired
//    private RedisDao redisDao;
    @Autowired
    private RedisCacheDao redisCacheDao;

    @Override
    public List<Seckill> list(Integer page, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);
        Example example = new Example(Seckill.class);
        example.orderBy("createTime").desc();
        List<Seckill> list = seckillMapper.selectByExample(example);
        return list;
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillMapper.selectByPrimaryKey(seckillId);
    }

    /**
     * 暴露秒杀接口
     *
     * @param seckillId
     * @return
     */
    @Override
    public Exposer exoportSeckillUrl(long seckillId) {
        Seckill seckill = (Seckill) redisCacheDao.get("seckill", seckillId);
//        Seckill seckill = redisDao.getObject(seckillId, Seckill.class);
        if (seckill == null) {
            seckill = seckillMapper.selectByPrimaryKey(seckillId);
            if (seckill == null) {
                return Exposer.notExist(seckillId);
            } else {
                redisCacheDao.put("seckill", seckillId, seckill);
//                redisDao.putObject(seckillId, seckill);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return Exposer.timeError(seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(seckillId);
        return Exposer.ok(md5, seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return seckillId + md5;
    }

    @Override
    @Transactional //开启事务
    public SeckillResult executeSeckill(long seckllId, long userPhone, String md5) {
        if (md5 == null || !md5.equals(getMD5(seckllId))) {
            throw new SeckillException("seckill data rewirite");
        }

        Date nowTime = new Date();
        //执行秒杀逻辑=减库存+记录购买行为
        try {
            //记录购买行为
            SuccessKilled killed = new SuccessKilled();
            killed.setSeckillId(seckllId);
            killed.setUserPhone(userPhone);
            killed.setState(Byte.parseByte("0"));
            int insertCount = successKilledMapper.insert(killed);
            if (insertCount <= 0) {
                //重复秒杀
                throw new RepeatKillException("seckill repeated");
            } else {
                int updateCount = seckillMapper.reduceNumber(seckllId, nowTime);
                if (updateCount <= 0) {
                    //没有更新到记录
                    throw new SeckillClosedException("seckill is closed");
                } else {
                    SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckllId, userPhone);
                    return new SeckillResult(seckllId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillClosedException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            //所有编译器异常转换为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }

    @Override
    public SeckillResult executeSeckillProcedure(long seckillid, long userPhone, String md5) {
        if (md5 == null || !md5.equals(getMD5(seckillid))) {
            return SeckillResult.error(seckillid, SeckillStateEnum.DATA_REWRITE);
        }
        Date killTime = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("seckillId", seckillid);
        map.put("phone", userPhone);
        map.put("killTime", killTime);
        map.put("result", null);
        try {
            seckillMapper.killByProcedure(map);
            int result = MapUtils.getInteger(map, "result", -2);
            if (result == 1) {
                SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillid, userPhone);
                return SeckillResult.ok(seckillid, SeckillStateEnum.SUCCESS, successKilled);
            } else {
                return SeckillResult.error(seckillid, SeckillStateEnum.stateOf(result));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new SeckillResult(seckillid, SeckillStateEnum.INNER_ERROR);
        }
    }
}
