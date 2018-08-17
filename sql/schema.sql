-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
USE seckill;

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id`  BIGINT(20)   NOT NULL AUTO_INCREMENT
  COMMENT '商品库存ID',
  `name`        VARCHAR(120) CHARACTER SET utf8
  COLLATE utf8_general_ci    NOT NULL
  COMMENT '商品名称',
  `number`      INT(11)      NOT NULL
  COMMENT '库存数量',
  `start_time`  TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '秒杀开始时间',
  `end_time`    TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`) USING BTREE,
  INDEX `idx_start_time`(`start_time`) USING BTREE,
  INDEX `idx_end_time`(`end_time`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1004
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT = '秒杀库存表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill`
VALUES (1000, '1000元秒杀iphone6', 99, '2016-01-01 00:00:00', '2018-05-25 00:00:00', '2018-04-08 11:12:42');
INSERT INTO `seckill`
VALUES (1001, '800元秒杀ipad', 100, '2016-01-01 00:00:00', '2018-05-25 00:00:00', '2018-04-08 11:12:42');
INSERT INTO `seckill`
VALUES (1002, '6600元秒杀mac book pro', 99, '2016-01-01 00:00:00', '2018-05-25 00:00:00', '2018-04-08 11:12:42');
INSERT INTO `seckill`
VALUES (1003, '7000元秒杀iMac', 100, '2016-01-01 00:00:00', '2018-05-25 00:00:00', '2018-04-08 11:12:42');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id`  BIGINT(20)   NOT NULL
  COMMENT '秒杀商品ID',
  `user_phone`  BIGINT(20)   NOT NULL
  COMMENT '用户手机号',
  `state`       TINYINT(4)   NOT NULL DEFAULT -1
  COMMENT '状态标识:-1:无效； 0:成功 ；1:已付款； 2:已发货',
  `create_time` TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0)
  COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`, `user_phone`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
)
  ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT = '秒杀成功明细表'
  ROW_FORMAT = DYNAMIC;

