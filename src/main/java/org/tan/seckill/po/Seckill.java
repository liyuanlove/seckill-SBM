package org.tan.seckill.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Seckill {
    /**
     * 商品库存ID
     */
    @Id
    @Column(name = "seckill_id")
    private Long seckillId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 库存数量
     */
    private Integer number;

    /**
     * 秒杀开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}