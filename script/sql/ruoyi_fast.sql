CREATE DATABASE IF NOT EXISTS `ruoyi_fast` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ruoyi_fast`;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`
(
    `config_id`    bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '参数主键',
    `config_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(100) NOT NULL DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(500) NOT NULL DEFAULT '' COMMENT '参数键值',
    `config_type`  char(1)      NOT NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
    `create_by`    varchar(64)  NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`    varchar(64)  NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`       varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`config_id`),
    UNIQUE INDEX `uk_config_key` (`config_key`)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '参数配置表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`
(
    `dict_code`   bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '字典编码',
    `dict_sort`   int unsigned    NOT NULL DEFAULT 0 COMMENT '字典排序',
    `dict_label`  varchar(100) NOT NULL DEFAULT '' COMMENT '字典标签',
    `dict_value`  varchar(100) NOT NULL DEFAULT '' COMMENT '字典键值',
    `dict_type`   varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型',
    `css_class`   varchar(100) NOT NULL DEFAULT '' COMMENT '样式属性（其他样式扩展）',
    `list_class`  varchar(100) NOT NULL DEFAULT '' COMMENT '表格回显样式',
    `is_default`  char(1)      NOT NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
    `status`      char(1)      NOT NULL DEFAULT '1' COMMENT '状态（1正常 0停用）',
    `create_by`   varchar(64)  NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(64)  NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`dict_code`),
    UNIQUE INDEX `uk_dict_type_value` (`dict_type`, `dict_value`)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '字典数据表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '1', 'admin', '2024-06-13 16:06:36', '', '2024-06-13 16:06:36',
        '性别男');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '1', 'admin', '2024-06-13 16:06:36', '', '2024-06-13 16:06:36',
        '性别女');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '1', 'admin', '2024-06-13 16:06:36', '', '2024-06-13 16:06:36',
        '性别未知');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (6, 1, '正常', '1', 'sys_normal_disable', '', 'primary', 'Y', '1', 'admin', '2024-06-13 16:06:36', 'admin',
        '2024-07-10 14:30:58', '正常状态');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (7, 2, '停用', '0', 'sys_normal_disable', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:36', 'admin',
        '2024-07-10 14:31:06', '停用状态');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '1', 'admin', '2024-06-13 16:06:36', '',
        '2024-06-13 16:06:36',
        '系统默认是');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:36', '',
        '2024-06-13 16:06:36', '系统默认否');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '1', 'admin', '2024-06-13 16:06:36', '',
        '2024-06-13 16:06:36',
        '新增操作');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '1', 'admin', '2024-06-13 16:06:36', '',
        '2024-06-13 16:06:36',
        '修改操作');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:37', '',
        '2024-06-13 16:06:37',
        '删除操作');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '1', 'admin', '2024-06-13 16:06:37', '',
        '2024-06-13 16:06:37',
        '授权操作');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '1', 'admin', '2024-06-13 16:06:37', '',
        '2024-06-13 16:06:37',
        '导出操作');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '1', 'admin', '2024-06-13 16:06:37', '',
        '2024-06-13 16:06:37',
        '导入操作');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:37', '',
        '2024-06-13 16:06:37',
        '强退操作');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '1', 'admin', '2024-06-13 16:06:37', '',
        '2024-06-13 16:06:37',
        '生成操作');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:37', '',
        '2024-06-13 16:06:37',
        '清空操作');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (27, 1, '失败', '0', 'sys_common_status', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:37', 'admin',
        '2024-07-15 10:50:52', '失败状态');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (28, 2, '成功', '1', 'sys_common_status', '', 'success', 'N', '1', 'admin', '2024-06-13 16:06:37', 'admin',
        '2024-07-15 10:51:05', '成功状态');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (29, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '1', 'admin', '2024-06-13 16:06:36', '',
        '2024-06-13 16:06:36',
        '其他操作');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`
(
    `dict_id`     bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_name`   varchar(100) NOT NULL DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型',
    `status`      char(1)      NOT NULL DEFAULT '1' COMMENT '状态（1正常 0停用）',
    `create_by`   varchar(64)  NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(64)  NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`dict_id`),
    UNIQUE INDEX `uk_dict_type` (`dict_type`)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '字典类型表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (1, '用户性别', 'sys_user_sex', '1', 'admin', '2024-06-13 16:06:35', '', '2024-06-13 16:06:35', '用户性别列表');
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (3, '系统开关', 'sys_normal_disable', '1', 'admin', '2024-06-13 16:06:35', '', '2024-06-13 16:06:35',
        '系统开关列表');
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (6, '系统是否', 'sys_yes_no', '1', 'admin', '2024-06-13 16:06:35', '', '2024-06-13 16:06:35', '系统是否列表');
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (9, '操作类型', 'sys_oper_type', '1', 'admin', '2024-06-13 16:06:35', '', '2024-06-13 16:06:35', '操作类型列表');
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (10, '系统状态', 'sys_common_status', '1', 'admin', '2024-06-13 16:06:36', '', '2024-06-13 16:06:36',
        '登录状态列表');

-- ----------------------------
-- Table structure for sys_login_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_info`;
CREATE TABLE `sys_login_info`
(
    `info_id`        bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `user_name`      varchar(50)  NOT NULL DEFAULT '' COMMENT '用户账号',
    `ipaddr`         varchar(128) NOT NULL DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255) NOT NULL DEFAULT '' COMMENT '登录地点',
    `browser`        varchar(50)  NOT NULL DEFAULT '' COMMENT '浏览器类型',
    `os`             varchar(50)  NOT NULL DEFAULT '' COMMENT '操作系统',
    `status`         char(1)      NOT NULL DEFAULT '0' COMMENT '登录状态（1成功 0失败）',
    `msg`            varchar(255) NOT NULL DEFAULT '' COMMENT '提示消息',
    `login_time`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '访问时间',
    `create_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`info_id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_login_time` (`login_time`)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`
(
    `oper_id`        bigint unsigned  NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title`          varchar(50)      NOT NULL DEFAULT '' COMMENT '模块标题',
    `business_type`  tinyint unsigned NOT NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100)     NOT NULL DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10)      NOT NULL DEFAULT '' COMMENT '请求方式',
    `operator_type`  tinyint unsigned NOT NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name`      varchar(50)      NOT NULL DEFAULT '' COMMENT '操作人员',
    `dept_name`      varchar(50)      NOT NULL DEFAULT '' COMMENT '部门名称',
    `oper_url`       varchar(255)     NOT NULL DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(128)     NOT NULL DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(255)     NOT NULL DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2000)    NOT NULL DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2000)    NOT NULL DEFAULT '' COMMENT '返回参数',
    `status`         tinyint unsigned NOT NULL DEFAULT 0 COMMENT '操作状态（0异常 1正常）',
    `error_msg`      varchar(2000)    NOT NULL DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    `create_time`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`oper_id`),
    INDEX `idx_business_type` (`business_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_oper_time` (`oper_time`)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`      bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `user_name`    varchar(30)  NOT NULL DEFAULT '' COMMENT '用户账号',
    `user_type`    char(10)     NOT NULL DEFAULT 'sys_user' COMMENT '用户类型（sys_user系统用户）',
    `phone_number` char(11)     NOT NULL DEFAULT '' COMMENT '手机号码',
    `sex`          char(1)      NOT NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `password`     varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
    `status`       char(1)      NOT NULL DEFAULT '1' COMMENT '账号状态（1正常 0停用）',
    `del_flag`     char(1)      NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    `login_ip`     varchar(128) NOT NULL DEFAULT '' COMMENT '最后登录IP',
    `login_date`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
    `create_by`    varchar(64)  NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`    varchar(64)  NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`       varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `uk_user_name` (`user_name`)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` (`user_id`, `user_name`, `user_type`, `phone_number`, `sex`, `password`, `status`, `del_flag`,
                        `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES (1, 'admin', 'sys_user', '15888888888', '1', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '1',
        '0', '127.0.0.1', '2025-09-12 15:26:34', 'admin', '2024-06-13 16:06:25', 'admin', '2025-09-12 15:26:34',
        '管理员');
