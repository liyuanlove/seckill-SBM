package org.tan.seckill.service;

import org.tan.seckill.core.dto.Exposer;
import org.tan.seckill.core.dto.SeckillResult;
import org.tan.seckill.po.Seckill;

import java.util.List;

public interface ISeckillService {

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<Seckill> list(Integer page, Integer pageSize);

    /**
     * 根据ID查询
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 暴露接口
     * @param seckillId
     * @return
     */
    Exposer exoportSeckillUrl(long seckillId);

    /**
     * 秒杀
     *
     * @param seckllId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillResult executeSeckill(long seckllId, long userPhone, String md5);

    /**
     * 秒杀（存储过程）
     *
     * @param seckillid
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillResult executeSeckillProcedure(long seckillid, long userPhone, String md5);

}
