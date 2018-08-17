package org.tan.seckill.core.dto;

import lombok.Data;
import org.tan.seckill.core.enums.SeckillStateEnum;
import org.tan.seckill.po.SuccessKilled;

/**
 * 秒杀执行结果
 */
@Data
public class SeckillResult {

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
     *
     * @param seckillId
     * @param seckillStateEnum
     * @param successKilled
     */
    public SeckillResult(long seckillId, SeckillStateEnum seckillStateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    /**
     * 秒杀失败
     *
     * @param seckillId
     * @param seckillStateEnum
     */
    public SeckillResult(long seckillId, SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
    }

    /**
     * 秒杀失败
     *
     * @param seckillStateEnum
     */
    public SeckillResult(SeckillStateEnum seckillStateEnum) {
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
    }

    /**
     * 成功
     *
     * @param seckillId
     * @param seckillStateEnum
     * @param successKilled
     * @return
     */
    public static SeckillResult ok(long seckillId, SeckillStateEnum seckillStateEnum, SuccessKilled successKilled) {
        return new SeckillResult(seckillId, seckillStateEnum, successKilled);
    }

    /**
     * 失败
     *
     * @param seckillId
     * @param seckillStateEnum
     * @return
     */
    public static SeckillResult error(long seckillId, SeckillStateEnum seckillStateEnum) {
        return new SeckillResult(seckillId, seckillStateEnum);
    }

    /**
     * 失败
     *
     * @param seckillStateEnum
     * @return
     */
    public static SeckillResult error(SeckillStateEnum seckillStateEnum) {
        return new SeckillResult(seckillStateEnum);
    }
}
