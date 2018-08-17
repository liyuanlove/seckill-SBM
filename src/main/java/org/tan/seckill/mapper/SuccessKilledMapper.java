package org.tan.seckill.mapper;

import org.apache.ibatis.annotations.Param;
import org.tan.seckill.core.utils.MyMapper;
import org.tan.seckill.po.SuccessKilled;


public interface SuccessKilledMapper extends MyMapper<SuccessKilled> {

    /**
     * 根据id查询SuccessKilled并携带秒杀对象实体
     *
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
