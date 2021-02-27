/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80019
Source Host           : 127.0.0.1:3306
Source Database       : mall_db

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2021-02-27 18:59:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `f_product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品名',
  `f_price` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '价格',
  `f_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '描述',
  `f_create_time` datetime NOT NULL COMMENT '创建时间',
  `f_count` int NOT NULL DEFAULT '0' COMMENT '剩余数量',
  `f_is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-正常 1-删除）',
  `f_update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
