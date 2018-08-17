package org.tan.seckill.core.dto;

import lombok.Data;

/**
 * 暴露接口
 */
@Data
public class Exposer {

    //是否暴露接口
    private boolean exposed;
    //接口md5值
    private String md5;
    //商品ID
    private long seckillId;
    //当前时间
    private long nowTime;
    //开启时间
    private long startTime;
    //结束时间
    private long endTime;


    /**
     * 秒杀开始暴露接口
     *
     * @param md5
     * @param seckillId
     */
    public Exposer(String md5, long seckillId) {
        this.exposed = true;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    /**
     * 未开始或已经结束
     *
     * @param seckillId
     * @param nowTime
     * @param startTime
     * @param endTime
     */
    public Exposer(long seckillId, long nowTime, long startTime, long endTime) {
        this.exposed = false;
        this.seckillId = seckillId;
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 查询失败
     *
     * @param seckillId
     */
    public Exposer(long seckillId) {
        this.exposed = false;
        this.seckillId = seckillId;
    }

    public static Exposer ok(String md5, long seckillId) {
        return new Exposer(md5, seckillId);
    }

    public static Exposer timeError(long seckillId, long nowTime, long startTime, long endTime) {
        return new Exposer(seckillId, nowTime, startTime, endTime);
    }

    public static Exposer notExist(long seckillId) {
        return new Exposer(seckillId);
    }
}
