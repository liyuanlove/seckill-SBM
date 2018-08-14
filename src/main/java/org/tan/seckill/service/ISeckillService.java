package org.tan.seckill.service;

import org.tan.seckill.po.Seckill;
import org.tan.seckill.dto.Exposer;
import org.tan.seckill.dto.SeckillExecution;

import java.util.List;

/**
 * Author: Redinw
 * Description:
 */

public interface ISeckillService {

    List<Seckill> getListByPage(Integer page, Integer pageSize);

    Seckill getById(long seckillId);

    Exposer exoportSeckillUrl(long seckillId);

    SeckillExecution executeSeckill(long seckllId, long userPhone, String md5);

    SeckillExecution executeSeckillProcedure(long seckillid,long userPhone,String md5);

}
