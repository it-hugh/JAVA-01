/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80019
Source Host           : 127.0.0.1:3306
Source Database       : demo_db1

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2021-03-14 00:07:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint NOT NULL,
  `f_user_id` bigint NOT NULL COMMENT '下单用户id',
  `f_product_id` bigint NOT NULL COMMENT '商品id',
  `f_order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `f_product_count` int NOT NULL DEFAULT '0' COMMENT '购买数量',
  `f_total_price` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '订单总价',
  `f_create_time` datetime NOT NULL COMMENT '创建时间',
  `f_status` tinyint NOT NULL COMMENT '订单状态（0-待支付，1-已支付，2-已失效，3-已删除）',
  `f_remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
