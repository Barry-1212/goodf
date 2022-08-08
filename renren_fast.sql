/*
Navicat MySQL Data Transfer

Source Server         : hospital_docker
Source Server Version : 50562
Source Host           : localhost:3309
Source Database       : renren_fast

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2022-07-15 16:20:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `QRTZ_BLOB_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_CALENDARS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_CRON_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for `QRTZ_FIRED_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_JOB_DETAILS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', null, 'io.renren.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000181EF55A3807874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for `QRTZ_LOCKS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for `QRTZ_PAUSED_TRIGGER_GRPS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_SCHEDULER_STATE`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('RenrenScheduler', 'LAPTOP-N2TA8J881657873006995', '1657873159249', '15000');

-- ----------------------------
-- Table structure for `QRTZ_SIMPLE_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_SIMPROP_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1657873800000', '-1', '5', 'WAITING', 'CRON', '1657606653000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000181EF55A3807874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for `schedule_job`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'renren', '0 0/30 * * * ?', '0', '参数测试', '2022-07-12 06:16:16');

-- ----------------------------
-- Table structure for `schedule_job_log`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES ('1', '1', 'testTask', 'renren', '0', null, '1', '2022-07-12 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('2', '1', 'testTask', 'renren', '0', null, '2', '2022-07-12 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('3', '1', 'testTask', 'renren', '0', null, '1', '2022-07-12 15:30:00');

-- ----------------------------
-- Table structure for `sys_captcha`
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统验证码';

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":31,\"parentId\":0,\"name\":\"医生管理\",\"url\":\"src\\\\views\\\\modules\\\\goodf\\\\doctor.vue\",\"perms\":\"doctor\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]', '79', '0:0:0:0:0:0:0:1', '2022-07-12 14:28:31');
INSERT INTO `sys_log` VALUES ('2', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":31,\"parentId\":0,\"name\":\"医生管理\",\"url\":\"goodf\\\\doctor\",\"perms\":\"\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]', '69', '0:0:0:0:0:0:0:1', '2022-07-12 14:31:01');
INSERT INTO `sys_log` VALUES ('3', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":2,\"parentId\":0,\"name\":\"管理员列表\",\"url\":\"sys/user\",\"type\":1,\"icon\":\"admin\",\"orderNum\":1,\"list\":[]}]', '64', '0:0:0:0:0:0:0:1', '2022-07-12 14:32:32');
INSERT INTO `sys_log` VALUES ('4', 'admin', '删除菜单', 'io.renren.modules.sys.controller.SysMenuController.delete()', '[30]', '1', '0:0:0:0:0:0:0:1', '2022-07-12 14:34:32');
INSERT INTO `sys_log` VALUES ('5', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":31,\"parentId\":0,\"name\":\"医生管理\",\"url\":\"goodf/doctor\",\"perms\":\"\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]', '307', '0:0:0:0:0:0:0:1', '2022-07-12 14:37:32');
INSERT INTO `sys_log` VALUES ('6', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"新增\",\"perms\":\"goodf:doctor:save,goodf:doctor:select\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '96', '0:0:0:0:0:0:0:1', '2022-07-12 14:41:24');
INSERT INTO `sys_log` VALUES ('7', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":33,\"parentId\":31,\"name\":\"修改\",\"perms\":\"goodf:doctor:update,goodf:doctor:select\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '101', '0:0:0:0:0:0:0:1', '2022-07-12 14:43:07');
INSERT INTO `sys_log` VALUES ('8', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":34,\"parentId\":31,\"name\":\"删除\",\"url\":\"\",\"perms\":\"goof:doctor:delete\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '100', '0:0:0:0:0:0:0:1', '2022-07-12 14:43:47');
INSERT INTO `sys_log` VALUES ('9', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":34,\"parentId\":31,\"name\":\"删除\",\"url\":\"\",\"perms\":\"goodf:doctor:delete\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '99', '0:0:0:0:0:0:0:1', '2022-07-12 14:43:58');
INSERT INTO `sys_log` VALUES ('10', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":33,\"parentId\":31,\"name\":\"修改\",\"perms\":\"goodf:doctor:save,goodf:doctor:select\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '97', '0:0:0:0:0:0:0:1', '2022-07-12 14:50:08');
INSERT INTO `sys_log` VALUES ('11', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":33,\"parentId\":31,\"name\":\"修改\",\"perms\":\"goodf:doctor:update\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '97', '0:0:0:0:0:0:0:1', '2022-07-12 15:00:35');
INSERT INTO `sys_log` VALUES ('12', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"新增\",\"perms\":\"goodf:doctor:save\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '93', '0:0:0:0:0:0:0:1', '2022-07-12 15:00:48');
INSERT INTO `sys_log` VALUES ('13', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"新增\",\"perms\":\"\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '103', '0:0:0:0:0:0:0:1', '2022-07-12 15:03:36');
INSERT INTO `sys_log` VALUES ('14', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"新增\",\"perms\":\"goodf:doctor:save\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '126', '0:0:0:0:0:0:0:1', '2022-07-12 15:04:09');
INSERT INTO `sys_log` VALUES ('15', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":35,\"parentId\":0,\"name\":\"科室管理\",\"url\":\"goodf/deparment\",\"perms\":\"\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]', '68', '0:0:0:0:0:0:0:1', '2022-07-12 15:05:31');
INSERT INTO `sys_log` VALUES ('16', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":36,\"parentId\":35,\"name\":\"新增\",\"url\":\"\",\"perms\":\"goodf:department:save\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '102', '0:0:0:0:0:0:0:1', '2022-07-12 15:06:09');
INSERT INTO `sys_log` VALUES ('17', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":37,\"parentId\":35,\"name\":\"删除\",\"url\":\"\",\"perms\":\"goodf:deparment:delete\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '98', '0:0:0:0:0:0:0:1', '2022-07-12 15:06:57');
INSERT INTO `sys_log` VALUES ('18', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":36,\"parentId\":35,\"name\":\"新增\",\"url\":\"\",\"perms\":\"goodf:deparment:save\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '94', '0:0:0:0:0:0:0:1', '2022-07-12 15:07:08');
INSERT INTO `sys_log` VALUES ('19', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":38,\"parentId\":35,\"name\":\"修改\",\"url\":\"\",\"perms\":\"goodf:deparment:update\",\"type\":2,\"orderNum\":0,\"list\":[]}]', '100', '0:0:0:0:0:0:0:1', '2022-07-12 15:07:58');
INSERT INTO `sys_log` VALUES ('20', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":39,\"parentId\":0,\"name\":\"科室分类管理\",\"url\":\"goodf/depclass\",\"perms\":\"\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]', '65', '0:0:0:0:0:0:0:1', '2022-07-12 15:09:54');
INSERT INTO `sys_log` VALUES ('21', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":40,\"parentId\":39,\"name\":\"新增\",\"url\":\"\",\"perms\":\"goodf:depclass:save\",\"type\":2,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', '96', '0:0:0:0:0:0:0:1', '2022-07-12 15:10:27');
INSERT INTO `sys_log` VALUES ('22', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":41,\"parentId\":39,\"name\":\"删除\",\"url\":\"\",\"perms\":\"goodf:depclass:delete\",\"type\":2,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', '97', '0:0:0:0:0:0:0:1', '2022-07-12 15:10:46');
INSERT INTO `sys_log` VALUES ('23', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":42,\"parentId\":39,\"name\":\"修改\",\"url\":\"\",\"perms\":\"goodf:depclass:update\",\"type\":2,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', '95', '0:0:0:0:0:0:0:1', '2022-07-12 15:11:02');
INSERT INTO `sys_log` VALUES ('24', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":43,\"parentId\":0,\"name\":\"医院管理\",\"url\":\"goodf/hospital\",\"perms\":\"\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]', '64', '0:0:0:0:0:0:0:1', '2022-07-12 15:12:22');
INSERT INTO `sys_log` VALUES ('25', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":44,\"parentId\":43,\"name\":\"新增\",\"url\":\"\",\"perms\":\"goodf:hospital:save\",\"type\":2,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', '99', '0:0:0:0:0:0:0:1', '2022-07-12 15:12:48');
INSERT INTO `sys_log` VALUES ('26', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":45,\"parentId\":43,\"name\":\"删除\",\"url\":\"\",\"perms\":\"goodf:hospital:delete\",\"type\":2,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', '98', '0:0:0:0:0:0:0:1', '2022-07-12 15:13:00');
INSERT INTO `sys_log` VALUES ('27', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":46,\"parentId\":43,\"name\":\"修改\",\"url\":\"\",\"perms\":\"goodf:hospital:update\",\"type\":2,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', '95', '0:0:0:0:0:0:0:1', '2022-07-12 15:13:15');
INSERT INTO `sys_log` VALUES ('28', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":47,\"parentId\":0,\"name\":\"挂号管理\",\"url\":\"goodf\\\\registration\",\"perms\":\"\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]', '62', '0:0:0:0:0:0:0:1', '2022-07-12 15:13:55');
INSERT INTO `sys_log` VALUES ('29', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":48,\"parentId\":47,\"name\":\"删除\",\"url\":\"\",\"perms\":\"goodf:registration:delete\",\"type\":2,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', '99', '0:0:0:0:0:0:0:1', '2022-07-12 15:14:38');
INSERT INTO `sys_log` VALUES ('30', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":49,\"parentId\":0,\"name\":\"用户管理\",\"url\":\"goodf\\\\user\",\"perms\":\"\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]', '63', '0:0:0:0:0:0:0:1', '2022-07-12 15:15:38');
INSERT INTO `sys_log` VALUES ('31', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":47,\"parentId\":0,\"name\":\"挂号管理\",\"url\":\"goodf/registration\",\"perms\":\"\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]', '63', '0:0:0:0:0:0:0:1', '2022-07-12 15:16:52');
INSERT INTO `sys_log` VALUES ('32', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":49,\"parentId\":0,\"name\":\"用户管理\",\"url\":\"goodf/user\",\"perms\":\"\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]', '64', '0:0:0:0:0:0:0:1', '2022-07-12 15:16:58');
INSERT INTO `sys_log` VALUES ('33', 'admin', '保存用户', 'io.renren.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"111\",\"password\":\"bb4d3425711d5c09a3375b2a3801a08068ba04eea8c7d34511f57d8984597d0a\",\"salt\":\"BkL2RUP0r227FPCoU6md\",\"email\":\"111@qq.com\",\"mobile\":\"11111111111\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Jul 12, 2022 3:28:46 PM\"}]', '342', '0:0:0:0:0:0:0:1', '2022-07-12 15:28:47');
INSERT INTO `sys_log` VALUES ('34', 'admin', '保存角色', 'io.renren.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[1,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,2,15,16,17,18,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,-666666],\"createTime\":\"Jul 13, 2022 9:52:48 AM\"}]', '5188', '0:0:0:0:0:0:0:1', '2022-07-13 09:52:53');
INSERT INTO `sys_log` VALUES ('35', 'admin', '保存角色', 'io.renren.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"管理员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[1,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,2,15,16,17,18,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,-666666],\"createTime\":\"Jul 13, 2022 9:52:53 AM\"}]', '4724', '0:0:0:0:0:0:0:1', '2022-07-13 09:52:58');
INSERT INTO `sys_log` VALUES ('36', 'admin', '删除角色', 'io.renren.modules.sys.controller.SysRoleController.delete()', '[[2]]', '431', '0:0:0:0:0:0:0:1', '2022-07-13 09:53:46');
INSERT INTO `sys_log` VALUES ('37', 'admin', '修改用户', 'io.renren.modules.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"111\",\"salt\":\"BkL2RUP0r227FPCoU6md\",\"email\":\"111@qq.com\",\"mobile\":\"11111111111\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', '430', '0:0:0:0:0:0:0:1', '2022-07-13 09:54:15');
INSERT INTO `sys_log` VALUES ('38', 'admin', '修改角色', 'io.renren.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,-666666]}]', '2623', '0:0:0:0:0:0:0:1', '2022-07-13 09:55:33');
INSERT INTO `sys_log` VALUES ('39', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":3,\"parentId\":0,\"name\":\"角色管理\",\"url\":\"sys/role\",\"type\":1,\"icon\":\"role\",\"orderNum\":2,\"list\":[]}]', '141', '0:0:0:0:0:0:0:1', '2022-07-13 10:02:46');
INSERT INTO `sys_log` VALUES ('40', 'admin', '修改角色', 'io.renren.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,-666666]}]', '10313', '0:0:0:0:0:0:0:1', '2022-07-13 10:03:37');
INSERT INTO `sys_log` VALUES ('41', 'admin', '修改角色', 'io.renren.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,-666666]}]', '8934', '0:0:0:0:0:0:0:1', '2022-07-13 10:03:42');
INSERT INTO `sys_log` VALUES ('42', 'admin', '修改角色', 'io.renren.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,-666666]}]', '7348', '0:0:0:0:0:0:0:1', '2022-07-13 10:03:45');
INSERT INTO `sys_log` VALUES ('43', '111', '保存角色', 'io.renren.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":3,\"roleName\":\"管理员\",\"remark\":\"\",\"createUserId\":2,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,-666666],\"createTime\":\"Jul 13, 2022 10:04:31 AM\"}]', '4388', '0:0:0:0:0:0:0:1', '2022-07-13 10:04:36');
INSERT INTO `sys_log` VALUES ('44', '111', '保存用户', 'io.renren.modules.sys.controller.SysUserController.save()', '[{\"userId\":3,\"username\":\"222\",\"password\":\"be4680e6f09b97279126fcd479dcb204ef123def0abd92602381fc8c8f6c6022\",\"salt\":\"QdXs8yvnkWHW1WuuoPPp\",\"email\":\"222@qq.com\",\"mobile\":\"11111111111\",\"status\":1,\"roleIdList\":[3],\"createUserId\":2,\"createTime\":\"Jul 13, 2022 10:05:07 AM\"}]', '567', '0:0:0:0:0:0:0:1', '2022-07-13 10:05:08');
INSERT INTO `sys_log` VALUES ('45', '111', '修改角色', 'io.renren.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":3,\"roleName\":\"管理员\",\"remark\":\"\",\"createUserId\":2,\"menuIdList\":[31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,-666666]}]', '2343', '0:0:0:0:0:0:0:1', '2022-07-13 10:06:31');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'system', '0');
INSERT INTO `sys_menu` VALUES ('2', '0', '管理员列表', 'sys/user', null, '1', 'admin', '1');
INSERT INTO `sys_menu` VALUES ('3', '0', '角色管理', 'sys/role', null, '1', 'role', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu', null, '1', 'menu', '3');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('31', '0', '医生管理', 'goodf/doctor', '', '1', 'zonghe', '0');
INSERT INTO `sys_menu` VALUES ('32', '31', '新增', null, 'goodf:doctor:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('33', '31', '修改', null, 'goodf:doctor:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('34', '31', '删除', '', 'goodf:doctor:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('35', '0', '科室管理', 'goodf/deparment', '', '1', 'zonghe', '0');
INSERT INTO `sys_menu` VALUES ('36', '35', '新增', '', 'goodf:deparment:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('37', '35', '删除', '', 'goodf:deparment:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('38', '35', '修改', '', 'goodf:deparment:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('39', '0', '科室分类管理', 'goodf/depclass', '', '1', 'zonghe', '0');
INSERT INTO `sys_menu` VALUES ('40', '39', '新增', '', 'goodf:depclass:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('41', '39', '删除', '', 'goodf:depclass:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('42', '39', '修改', '', 'goodf:depclass:update', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('43', '0', '医院管理', 'goodf/hospital', '', '1', 'zonghe', '0');
INSERT INTO `sys_menu` VALUES ('44', '43', '新增', '', 'goodf:hospital:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('45', '43', '删除', '', 'goodf:hospital:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('46', '43', '修改', '', 'goodf:hospital:update', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('47', '0', '挂号管理', 'goodf/registration', '', '1', 'zonghe', '0');
INSERT INTO `sys_menu` VALUES ('48', '47', '删除', '', 'goodf:registration:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('49', '0', '用户管理', 'goodf/user', '', '1', 'zonghe', '0');

-- ----------------------------
-- Table structure for `sys_oss`
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '', '1', '2022-07-13 09:52:48');
INSERT INTO `sys_role` VALUES ('3', '管理员', '', '2', '2022-07-13 10:04:31');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('184', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('185', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('186', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('187', '1', '17');
INSERT INTO `sys_role_menu` VALUES ('188', '1', '18');
INSERT INTO `sys_role_menu` VALUES ('189', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('190', '1', '19');
INSERT INTO `sys_role_menu` VALUES ('191', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('192', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('193', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('194', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('195', '1', '32');
INSERT INTO `sys_role_menu` VALUES ('196', '1', '33');
INSERT INTO `sys_role_menu` VALUES ('197', '1', '34');
INSERT INTO `sys_role_menu` VALUES ('198', '1', '35');
INSERT INTO `sys_role_menu` VALUES ('199', '1', '36');
INSERT INTO `sys_role_menu` VALUES ('200', '1', '37');
INSERT INTO `sys_role_menu` VALUES ('201', '1', '38');
INSERT INTO `sys_role_menu` VALUES ('202', '1', '39');
INSERT INTO `sys_role_menu` VALUES ('203', '1', '40');
INSERT INTO `sys_role_menu` VALUES ('204', '1', '41');
INSERT INTO `sys_role_menu` VALUES ('205', '1', '42');
INSERT INTO `sys_role_menu` VALUES ('206', '1', '43');
INSERT INTO `sys_role_menu` VALUES ('207', '1', '44');
INSERT INTO `sys_role_menu` VALUES ('208', '1', '45');
INSERT INTO `sys_role_menu` VALUES ('209', '1', '46');
INSERT INTO `sys_role_menu` VALUES ('210', '1', '47');
INSERT INTO `sys_role_menu` VALUES ('211', '1', '48');
INSERT INTO `sys_role_menu` VALUES ('212', '1', '49');
INSERT INTO `sys_role_menu` VALUES ('213', '1', '-666666');
INSERT INTO `sys_role_menu` VALUES ('244', '3', '31');
INSERT INTO `sys_role_menu` VALUES ('245', '3', '32');
INSERT INTO `sys_role_menu` VALUES ('246', '3', '33');
INSERT INTO `sys_role_menu` VALUES ('247', '3', '34');
INSERT INTO `sys_role_menu` VALUES ('248', '3', '35');
INSERT INTO `sys_role_menu` VALUES ('249', '3', '36');
INSERT INTO `sys_role_menu` VALUES ('250', '3', '37');
INSERT INTO `sys_role_menu` VALUES ('251', '3', '38');
INSERT INTO `sys_role_menu` VALUES ('252', '3', '39');
INSERT INTO `sys_role_menu` VALUES ('253', '3', '40');
INSERT INTO `sys_role_menu` VALUES ('254', '3', '41');
INSERT INTO `sys_role_menu` VALUES ('255', '3', '42');
INSERT INTO `sys_role_menu` VALUES ('256', '3', '43');
INSERT INTO `sys_role_menu` VALUES ('257', '3', '44');
INSERT INTO `sys_role_menu` VALUES ('258', '3', '45');
INSERT INTO `sys_role_menu` VALUES ('259', '3', '46');
INSERT INTO `sys_role_menu` VALUES ('260', '3', '47');
INSERT INTO `sys_role_menu` VALUES ('261', '3', '48');
INSERT INTO `sys_role_menu` VALUES ('262', '3', '49');
INSERT INTO `sys_role_menu` VALUES ('263', '3', '-666666');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');
INSERT INTO `sys_user` VALUES ('2', '111', 'bb4d3425711d5c09a3375b2a3801a08068ba04eea8c7d34511f57d8984597d0a', 'BkL2RUP0r227FPCoU6md', '111@qq.com', '11111111111', '1', '1', '2022-07-12 15:28:46');
INSERT INTO `sys_user` VALUES ('3', '222', 'be4680e6f09b97279126fcd479dcb204ef123def0abd92602381fc8c8f6c6022', 'QdXs8yvnkWHW1WuuoPPp', '222@qq.com', '11111111111', '1', '2', '2022-07-13 10:05:07');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '2', '1');
INSERT INTO `sys_user_role` VALUES ('2', '3', '3');

-- ----------------------------
-- Table structure for `sys_user_token`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES ('1', '0189e4cda097076db4ea88b29e685539', '2022-07-16 04:18:29', '2022-07-15 16:18:29');
INSERT INTO `sys_user_token` VALUES ('2', '407cf47785a7321e9001966c34a99fdf', '2022-07-13 22:06:10', '2022-07-13 10:06:10');
INSERT INTO `sys_user_token` VALUES ('3', 'e82b5a87e12fd9db65b1c79a81ffbff8', '2022-07-13 22:06:56', '2022-07-13 10:06:56');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');
