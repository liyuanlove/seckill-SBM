package org.tan.seckill.dto;

import lombok.Data;
import org.tan.seckill.enums.SeckillStateEnum;
import org.tan.seckill.po.SuccessKilled;

/**
 * 秒杀执行结果
 */
@Data
public class SeckillExecution {

    //秒杀商品ID
    private long seckillId;

    //秒杀执行结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //秒杀成功信息
    private SuccessKilled successKilled;

    /**
     * 秒杀成功
     * @param seckillId
     * @param seckillStateEnum
     * @param successKilled
     */
    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    /**
     * 秒杀失败
     * @param seckillId
     * @param seckillStateEnum
     */
    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
}
}
