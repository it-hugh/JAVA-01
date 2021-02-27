/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80019
Source Host           : 127.0.0.1:3306
Source Database       : mall_db

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2021-02-27 18:59:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `f_user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `f_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `f_phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `f_gender` tinyint NOT NULL DEFAULT '0' COMMENT '性别（0-未知，1-男，2-女）',
  `f_age` int NOT NULL DEFAULT '0' COMMENT '年龄',
  `f_create_time` datetime NOT NULL COMMENT '创建时间',
  `f_update_time` datetime NOT NULL COMMENT '更新时间',
  `f_status` tinyint NOT NULL DEFAULT '0' COMMENT '状态（0-正常 1-锁定 2-注销）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
