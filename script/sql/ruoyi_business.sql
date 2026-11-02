CREATE DATABASE IF NOT EXISTS `ruoyi_fast` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ruoyi_fast`;

-- ----------------------------
-- 系统用户与微信小程序 token 映射表
-- 一个系统用户只能绑定一个 token，由唯一索引 uk_user_id 保证
-- ----------------------------
DROP TABLE IF EXISTS `study_room_user_token`;
CREATE TABLE `study_room_user_token`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     bigint unsigned NOT NULL DEFAULT 0 COMMENT '系统用户ID',
    `token`       varchar(1024)   NOT NULL DEFAULT '' COMMENT '微信小程序token',
    `create_by`   varchar(64)     NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(64)     NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户与微信小程序token映射表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- 自动预约用户设置表
-- 一个系统用户一份配置，config_json 只存用户的覆盖项，缺省值在代码里
-- ----------------------------
DROP TABLE IF EXISTS `study_room_auto_reserve_setting`;
CREATE TABLE `study_room_auto_reserve_setting`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     bigint unsigned NOT NULL DEFAULT 0 COMMENT '系统用户ID',
    `config_json` varchar(2048)   NOT NULL DEFAULT '{}' COMMENT '自动预约配置(JSON)',
    `create_by`   varchar(64)     NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(64)     NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '自动预约用户设置表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- 自动预约任务表
-- task_json 存规则详情，查询用的字段独立成列
-- ----------------------------
DROP TABLE IF EXISTS `study_room_auto_reserve_task`;
CREATE TABLE `study_room_auto_reserve_task`
(
    `id`                    bigint unsigned  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`               bigint unsigned  NOT NULL DEFAULT 0 COMMENT '系统用户ID',
    `venue_id`              int unsigned     NOT NULL DEFAULT 0 COMMENT '门店ID',
    `venue_name`            varchar(128)     NOT NULL DEFAULT '' COMMENT '门店名称',
    `task_json`             varchar(2048)    NOT NULL DEFAULT '{}' COMMENT '任务详情(JSON)',
    `enable_status`         tinyint unsigned NOT NULL DEFAULT 0 COMMENT '启用状态：0 暂停，1 启用',
    `pause_reason`          varchar(32)      NOT NULL DEFAULT '' COMMENT '暂停原因',
    `last_max_reserve_date` date             NOT NULL DEFAULT '1970-01-01' COMMENT '放号基线日期',
    `create_by`             varchar(64)      NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time`           datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`             varchar(64)      NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time`           datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id_enable_status` (`user_id`, `enable_status`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '自动预约任务表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- 自动预约执行记录表
-- 每轮抢座一条，记录跳过 / 成功 / 失败终态
-- ----------------------------
DROP TABLE IF EXISTS `study_room_auto_reserve_task_record`;
CREATE TABLE `study_room_auto_reserve_task_record`
(
    `id`           bigint unsigned  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `task_id`      bigint unsigned  NOT NULL DEFAULT 0 COMMENT '任务ID',
    `user_id`      bigint unsigned  NOT NULL DEFAULT 0 COMMENT '系统用户ID',
    `trigger_type` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '触发方式：0 定时，1 手动',
    `target_date`  date             NOT NULL DEFAULT '1970-01-01' COMMENT '目标预约日期',
    `status`       tinyint unsigned NOT NULL DEFAULT 0 COMMENT '执行状态：0 跳过，1 成功，2 失败',
    `detail_json`  varchar(2048)    NOT NULL DEFAULT '{}' COMMENT '尝试明细(JSON)',
    `create_by`    varchar(64)      NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`    varchar(64)      NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_task_id` (`task_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '自动预约执行记录表'
  ROW_FORMAT = Dynamic;
