package org.tan.seckill.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "success_killed")
public class SuccessKilled {
    /**
     * 秒杀商品ID
     */
    @Id
    @Column(name = "seckill_id")
    private Long seckillId;

    /**
     * 用户手机号
     */
    @Id
    @Column(name = "user_phone")
    private Long userPhone;

    /**
     * 状态标识:-1:无效； 0:成功 ；1:已付款； 2:已发货
     */
    private Byte state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private Seckill seckill;
}