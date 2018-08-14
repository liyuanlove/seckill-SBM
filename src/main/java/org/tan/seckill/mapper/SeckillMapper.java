package org.tan.seckill.mapper;

import org.apache.ibatis.annotations.Param;
import org.tan.seckill.po.Seckill;
import org.tan.seckill.utils.MyMapper;

import java.util.Date;
import java.util.Map;

/**
 * Author: Redinw
 * Description:
 */
public interface SeckillMapper extends MyMapper<Seckill>{

    /**
     * 减库存
     *
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 使用存储过程执行秒杀
     * @param paramsMap
     */
    void killByProcedure(Map<String,Object> paramsMap);
}
