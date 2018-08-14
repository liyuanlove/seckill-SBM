package org.tan.seckill.dto;

import lombok.Data;

/**
 *暴露接口
 */
@Data
public class Exposer {

    private boolean exposed;

    private String md5;

    private long seckillId;

    private long nowTime;

    private long startTime;

    private long endTime;


    /**
     * 秒杀开始暴露接口
     * @param exposed
     * @param md5
     * @param seckillId
     */
    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    /**
     * 未开始或已经结束
     * @param exposed
     * @param seckillId
     * @param nowTime
     * @param startTime
     * @param endTime
     */
    public Exposer(boolean exposed, long seckillId, long nowTime, long startTime, long endTime) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 查询失败
     * @param exposed
     * @param seckillId
     */
    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }
}
