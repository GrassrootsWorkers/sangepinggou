/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : qr

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-10-31 16:31:33
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `qr_address`
-- ----------------------------
DROP TABLE IF EXISTS `qr_address`;
CREATE TABLE `qr_address` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '0:省份，1：市，2：县 3：镇 4：村',
  `name` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qr_address
-- ----------------------------
INSERT INTO qr_address VALUES ('1', '0', '北京市', '0');
INSERT INTO qr_address VALUES ('2', '1', '北京市', '1');
INSERT INTO qr_address VALUES ('3', '2', '海淀区', '2');
INSERT INTO qr_address VALUES ('4', '3', '魏公村街道办事处', '3');
INSERT INTO qr_address VALUES ('5', '4', '魏公村小区', '4');
INSERT INTO qr_address VALUES ('6', '2', '昌平区', '2');
INSERT INTO qr_address VALUES ('7', '3', '天通苑', '6');

-- ----------------------------
-- Table structure for `qr_brand`
-- ----------------------------
DROP TABLE IF EXISTS `qr_brand`;
CREATE TABLE `qr_brand` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '水果类型，首字母简称',
  `brand_desc` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qr_brand
-- ----------------------------
INSERT INTO qr_brand VALUES ('1', 'PG', '烟台苹果', '2016-07-01 14:15:07', '烟台栖霞');
INSERT INTO qr_brand VALUES ('2', 'PG', '陕西苹果', '2016-07-01 14:15:43', '陕西苹果');
INSERT INTO qr_brand VALUES ('3', 'XG', '淄博绕王', '2016-09-03 11:18:53', '淄博绕王');

-- ----------------------------
-- Table structure for `qr_dic`
-- ----------------------------
DROP TABLE IF EXISTS `qr_dic`;
CREATE TABLE `qr_dic` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `value` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `type` char(2) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qr_dic
-- ----------------------------

-- ----------------------------
-- Table structure for `qr_farmer`
-- ----------------------------
DROP TABLE IF EXISTS `qr_farmer`;
CREATE TABLE `qr_farmer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `id_card` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `audit_flag` char(1) COLLATE utf8_bin DEFAULT NULL,
  `home_town` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '家庭住址',
  `current_address` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `growth_address` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `experience_age` tinyint(4) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `token` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_image` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qr_farmer
-- ----------------------------
INSERT INTO qr_farmer VALUES ('2', '18618102694', '刘志', 'caa046b7a8ea923586009a7e3ef0f4a886b1ad630f03a4dd31b3e8c3', '372925198711112222', '0', '山东省菏泽市黄冈镇', null, null, '8', '2016-06-26 21:43:56', '2016-10-30 12:21:59', 'DBOU,RGPH,WAMM,HXOQ,XJPC', '2016-10-30 12:21:59', null);

-- ----------------------------
-- Table structure for `qr_fruit_detail`
-- ----------------------------
DROP TABLE IF EXISTS `qr_fruit_detail`;
CREATE TABLE `qr_fruit_detail` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '初始值10000000',
  `fruit_code` varchar(60) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'token+类型+年+10000000',
  `base_code` bigint(20) DEFAULT NULL COMMENT 'qr_fruit_information.id|水果记录表|刘志|20160826',
  `reserved_id` bigint(20) DEFAULT NULL COMMENT '申请表的编码（打印该批次的二维码）|刘志|20161001',
  `type` char(4) DEFAULT NULL COMMENT '水果分类（PG L，XG PT）|刘志|20160826',
  `brand_id` bigint(20) DEFAULT NULL,
  `variety_id` bigint(20) DEFAULT NULL,
  `weight` double(8,2) DEFAULT NULL,
  `height` double(8,2) DEFAULT NULL,
  `width` double(8,2) DEFAULT NULL,
  `high` double(8,2) DEFAULT NULL,
  `file_path` varchar(100) DEFAULT NULL COMMENT '购物车链接，水果的url|刘志|20160906',
  `maturing_status` tinyint(4) DEFAULT NULL COMMENT '成熟度',
  `harvest_time` datetime DEFAULT NULL COMMENT '采摘日期',
  `sale_price` double(5,2) DEFAULT NULL COMMENT '水果真实价格，根据市场随时调整，来源价格收集系统|刘志|20180831',
  `market_price` double(5,2) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `add_user_id` bigint(20) DEFAULT NULL,
  `checkout_user_id` bigint(20) DEFAULT NULL,
  `checkout_time` datetime DEFAULT NULL,
  `delete_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0' COMMENT '0:未删除1：删除',
  `price_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'Y' COMMENT '是否显示销售价格价格 Y显示 N不显示',
  `lottery_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'N' COMMENT '是否显示抽奖按钮 N否 Y是',
  `if_sale` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'N' COMMENT '是否卖出 N: 否 Y:是',
  `orig_image` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '原始图片--存放绝对物理路径',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间|刘志|20160826',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qr_fruit_detail
-- ----------------------------
INSERT INTO qr_fruit_detail VALUES ('1', 'WAMMPG201610000604', '24', '12', 'PG', '1', '1', '410.00', null, null, null, 'http://m.sangepg.com/fruit/2/2016/', null, '2016-10-07 19:15:00', null, '8.12', '2016-10-07 19:21:32', '2', '12', null, null, null, null, 'N', 'I:\\app\\web_site\\upload\\files\\images\\2016\\10\\12\\webwxgetmsgimg (1).jpg', null);
INSERT INTO qr_fruit_detail VALUES ('2', 'WAMMPG201610000605', '24', '12', 'PG', '1', '1', '360.00', null, null, null, 'http://m.sangepg.com/fruit/2/2016/', null, '2016-10-07 19:15:00', null, '8.12', '2016-10-07 19:21:33', '2', '12', null, null, null, null, 'N', 'I:\\app\\web_site\\upload\\files\\images\\2016\\10\\12\\webwxgetmsgimg (2).jpg', null);
INSERT INTO qr_fruit_detail VALUES ('3', 'WAMMPG201610000606', '24', '12', 'PG', '1', '1', '230.00', null, null, null, 'http://m.sangepg.com/fruit/2/2016/', null, '2016-10-07 19:15:00', null, '8.12', '2016-10-07 19:21:33', '2', '12', null, null, null, null, 'N', 'I:\\app\\web_site\\upload\\files\\images\\2016\\10\\12\\webwxgetmsgimg.jpg', null);
INSERT INTO qr_fruit_detail VALUES ('4', 'RGPHPG201610000607', '24', '13', 'PG', '1', '1', '410.00', null, null, null, 'http://m.sangepg.com/fruit/2/2016/', null, '2016-10-15 17:36:00', null, '8.12', '2016-10-15 17:37:22', '2', '13', null, null, null, null, 'N', 'I:\\app\\web_site\\upload\\files\\images\\2016\\10\\13\\webwxgetmsgimg (1).jpg', null);
INSERT INTO qr_fruit_detail VALUES ('5', 'RGPHPG201610000608', '24', '13', 'PG', '1', '1', '360.00', null, null, null, 'http://m.sangepg.com/fruit/2/2016/', null, '2016-10-15 17:36:00', null, '8.12', '2016-10-15 17:37:23', '2', '13', null, null, null, null, 'N', 'I:\\app\\web_site\\upload\\files\\images\\2016\\10\\13\\webwxgetmsgimg (2).jpg', null);
INSERT INTO qr_fruit_detail VALUES ('6', 'RGPHPG201610000609', '24', '13', 'PG', '1', '1', '230.00', null, null, null, 'http://m.sangepg.com/fruit/2/2016/', null, '2016-10-15 17:36:00', null, '8.12', '2016-10-15 17:37:23', '2', '13', null, null, null, null, 'N', 'I:\\app\\web_site\\upload\\files\\images\\2016\\10\\13\\webwxgetmsgimg.jpg', null);
INSERT INTO qr_fruit_detail VALUES ('7', 'DBOUPG201610000610', '24', '14', 'PG', '1', '1', '10000.00', null, null, null, 'http://m.sangepg.com/fruit/2/2016/14/DBOUPG201610000610.html', null, '2016-10-30 13:14:00', null, '12.23', '2016-10-30 13:28:13', '2', '14', null, 'N', null, null, 'N', 'I:\\app\\web_site\\upload\\files\\images\\2016\\10\\14\\webwxgetmsgimg (1).jpg', null);
INSERT INTO qr_fruit_detail VALUES ('8', 'DBOUPG201610000611', '24', '14', 'PG', '1', '1', '10000.00', null, null, null, 'http://m.sangepg.com/fruit/2/2016/14/DBOUPG201610000611.html', null, '2016-10-30 13:14:00', null, '12.23', '2016-10-30 13:28:44', '2', '14', null, 'N', null, null, 'N', 'I:\\app\\web_site\\upload\\files\\images\\2016\\10\\14\\webwxgetmsgimg (1).jpg', null);
INSERT INTO qr_fruit_detail VALUES ('9', 'DBOUPG201610000612', '24', '14', 'PG', '1', '1', '10000.00', null, null, null, 'http://m.sangepg.com/fruit/2/2016/14/DBOUPG201610000612.html', null, '2016-10-30 13:14:00', null, '12.23', '2016-10-30 13:28:45', '2', '14', null, 'N', null, null, 'N', 'I:\\app\\web_site\\upload\\files\\images\\2016\\10\\14\\webwxgetmsgimg (1).jpg', null);
INSERT INTO qr_fruit_detail VALUES ('10', 'DBOUPG201610000613', '24', '14', 'PG', '1', '1', '9990.00', null, null, null, 'http://m.sangepg.com/fruit/2/2016/14/DBOUPG201610000613.html', null, '2016-10-30 13:14:00', null, '12.23', '2016-10-30 13:28:46', '2', '14', null, 'N', null, null, 'N', 'I:\\app\\web_site\\upload\\files\\images\\2016\\10\\14\\webwxgetmsgimg (1).jpg', null);
INSERT INTO qr_fruit_detail VALUES ('11', 'DBOUPG201610000614', '24', '14', 'PG', '1', '1', '10000.00', null, null, null, 'http://m.sangepg.com/fruit/2/2016/14/DBOUPG201610000614.html', null, '2016-10-30 13:14:00', null, '12.23', '2016-10-30 13:28:46', '2', '14', null, 'N', null, null, 'N', 'I:\\app\\web_site\\upload\\files\\images\\2016\\10\\14\\webwxgetmsgimg (1).jpg', null);

-- ----------------------------
-- Table structure for `qr_fruit_information`
-- ----------------------------
DROP TABLE IF EXISTS `qr_fruit_information`;
CREATE TABLE `qr_fruit_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` char(4) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '申请的类型 苹果：梨 西瓜等',
  `growth_period` int(4) DEFAULT NULL COMMENT '从开花到收获的天数',
  `production_place` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '产地',
  `production_place_desc` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '产地描述',
  `company` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '合作社名称',
  `farmer_desc` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '水果生长环境描述',
  `brand_id` int(20) DEFAULT NULL COMMENT '品牌编码',
  `variety_id` int(20) DEFAULT NULL COMMENT '水果种类',
  `farmer_id` int(20) DEFAULT NULL,
  `storage_way` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '存储方式',
  `destroy_rate` tinyint(4) DEFAULT NULL COMMENT '损坏率',
  `water_rate` tinyint(4) DEFAULT NULL COMMENT '含水量',
  `sugar_rate` tinyint(4) DEFAULT NULL COMMENT '含糖量',
  `status` char(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '0：添加 ,1：审核通过 2：发布 路径farmerid_type_id.html  3:发布失败，利用job补偿',
  `delete_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否删除 0：未删除 1删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qr_fruit_information
-- ----------------------------
INSERT INTO qr_fruit_information VALUES ('24', 'PG', '100', '山东省菏泽市单县', '烟台市苹果协会成立于2002年2月1日，主要是由从事苹果生产、加工、储藏、流通的企业，专业村，专业合作社，专业协会以及科研、技术推广等单位和个人，自愿组成的非营利性、行业性、全市性的社团组织，具有社团法人资格。&amp;lt;br&amp;gt;<br><br><br><br>', '山东省菏泽市合作社', '<h2>基本情况&amp;</h2>编辑<br>协会依据市场经济规则，充分发挥服务、协调、监督的作用，统一管理使用烟台苹果原产地域保护的标识，为会员提供技术、市场、价格、信息等系列化服务，组织制定行规行约，规范与烟台苹果相关的市场秩序，实行公平竞争，建立行业自律运行机制，协调政府、企业、农户之间的关系，维护会员的合法权益。协会现有会员单位138个，其中有进出口权的会员企业28个。协会在全国大中城市设有经销窗口30多个，设立办事处10多处，形成覆盖全国的销售网络。烟台市苹果协会已成为连结会员的纽带，带领广大农户走向市场的龙头。<br><h2>协会宗旨</h2>编辑<br>苹果协会的宗旨：以市场需求为导向，以科技进步为依托，提供综合性服务，促进苹果生产和流通，使烟台苹果产业一体化、苹果生产标准化、苹果销售国际化。坚持“双向通行”、“双向服务”的方向，充分反映会员的愿望和要求，维护会员的合法权益，在政府和企业、果农之间起到桥梁和纽带作用。<br><h2>协会职能</h2>编辑<br>一是保护和宣传烟台苹果品牌。协会将运用原产地域保护政策保护好这个品牌。同时采取各种有效形式，加大对烟台苹果的宣传力度，努力把烟台苹果培育成世界知名品牌。二是搞好技术培训和推广，提高产品质量。协会将积极开展果业技术交流活动，总结推广新技术和先进经验，探索提高果品质量的有效途径，不断提高烟台苹果的质量和档次。三是组织企业开拓苹果销售市场。推动企业进入市场，开拓市场，促进企业间的平等竞争。四是加强行业协调，减少盲目竞争。协会将加强行业自律，采取联合行动，避免盲目竞争，维护市场秩序，建立公平竞争的制度；牵头整顿理顺苗木市场、果袋市场、果箱市场等与苹果生产有关的生产资料市场，为烟台苹果创造良好的生产和经营环境。五是发挥政府与企业之间的桥梁和纽带作用。协会将积极主动地协调各方面的关系，向政府反映会员的意见和要求，协调苹果产加销各环节与相关产业的关系，为企业搞好服务。<br><br><br><br><br>', '1', '1', '2', null, '0', '90', '10', '2', null, '2016-08-02 11:23:44', '2016-08-02 11:23:44');

-- ----------------------------
-- Table structure for `qr_fruit_orders`
-- ----------------------------
DROP TABLE IF EXISTS `qr_fruit_orders`;
CREATE TABLE `qr_fruit_orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fruit_type` varchar(10) DEFAULT NULL COMMENT '购买的水果类型|刘志|20161016',
  `order_no` varchar(20) DEFAULT NULL COMMENT '订单编号20位|刘志|20161009',
  `open_id` varchar(100) DEFAULT NULL COMMENT '用户的openId|标示那个用户|刘志|2016108',
  `mobile` char(11) DEFAULT NULL COMMENT '收货人手机号|刘志|20161007',
  `name` varchar(50) DEFAULT NULL COMMENT '收货人姓名|刘志|20161013',
  `grade` char(1) DEFAULT NULL COMMENT '苹果的品级,1级最高|刘志|20161009',
  `price` double(4,2) DEFAULT NULL COMMENT '苹果的单价和grade对应|刘志|20161009',
  `weight` double(6,2) DEFAULT NULL COMMENT '下单的重量|刘志|20161007',
  `if_pay` char(1) DEFAULT NULL COMMENT '是否支付|刘志|20161007',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间||刘志|20161007',
  `user_ip` bigint(20) DEFAULT NULL COMMENT '下单者ip|刘志|20161007',
  `order_status` char(1) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL COMMENT '发货地址（用户填写地址）|刘志|20161007',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间|刘志|20161007',
  `create_time` datetime DEFAULT NULL,
  `trade_no` varchar(100) DEFAULT NULL COMMENT '交易号，退款处理|刘志|20161011',
  `farmer_ids` varchar(100) DEFAULT NULL COMMENT '根据用户的评价，不给用户发送的农户的水果|刘志|20161024',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单模块|微信公众号预定水果|刘志|20161007';

-- ----------------------------
-- Records of qr_fruit_orders
-- ----------------------------

-- ----------------------------
-- Table structure for `qr_fruit_picture`
-- ----------------------------
DROP TABLE IF EXISTS `qr_fruit_picture`;
CREATE TABLE `qr_fruit_picture` (
  `id` int(20) NOT NULL,
  `type` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `number` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `org_image` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `big_image` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `center_image` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `small_image` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `add_time` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `if_main` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qr_fruit_picture
-- ----------------------------

-- ----------------------------
-- Table structure for `qr_fruit_type`
-- ----------------------------
DROP TABLE IF EXISTS `qr_fruit_type`;
CREATE TABLE `qr_fruit_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL COMMENT '水果类型编码|刘志|20160902',
  `name` varchar(50) DEFAULT NULL COMMENT '水果类型名称|刘志|20160902',
  `status` char(1) DEFAULT 'Y' COMMENT '是否可用Y：可用 N:不可用 默认Y|刘志|20160902',
  `description` varchar(100) DEFAULT NULL COMMENT '水果名称的描述',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='基础数据模块|水果种类的编码，首字母|刘志|2016080902';

-- ----------------------------
-- Records of qr_fruit_type
-- ----------------------------
INSERT INTO qr_fruit_type VALUES ('1', 'PG', '苹果', 'Y', null, '2016-09-02 16:25:30', '2016-09-02 16:25:33');
INSERT INTO qr_fruit_type VALUES ('2', 'XG', '西瓜', 'Y', null, '2016-09-02 16:25:30', '2016-09-02 16:26:08');
INSERT INTO qr_fruit_type VALUES ('3', 'HMG', '哈密瓜', 'Y', null, '2016-09-02 16:26:34', '2016-09-02 16:26:41');

-- ----------------------------
-- Table structure for `qr_page_view`
-- ----------------------------
DROP TABLE IF EXISTS `qr_page_view`;
CREATE TABLE `qr_page_view` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户访问量自增id',
  `mobile` varchar(20) DEFAULT NULL COMMENT '用户编码，qr_user.id|刘志|20161007',
  `ip` bigint(8) DEFAULT NULL COMMENT '用户ip的整数值|刘志|20161007',
  `fruit_code` varchar(20) DEFAULT NULL COMMENT '水果的编码qr_fruit.fruit_code|刘志|20161007',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COMMENT='统计网站的访问量|刘志|20161007';

-- ----------------------------
-- Records of qr_page_view
-- ----------------------------
INSERT INTO qr_page_view VALUES ('1', '18618102693', '2130706433', 'WAMMPG201610000604', '2016-10-15 11:18:23');
INSERT INTO qr_page_view VALUES ('2', '18618102693', '2130706433', 'WAMMPG201610000604', '2016-10-15 11:30:46');
INSERT INTO qr_page_view VALUES ('3', '18618102693', '2130706433', 'WAMMPG201610000604', '2016-10-15 11:57:14');
INSERT INTO qr_page_view VALUES ('4', '18618102693', '2130706433', 'WAMMPG201610000604', '2016-10-15 16:55:57');
INSERT INTO qr_page_view VALUES ('5', '18618102693', '2130706433', 'WAMMPG201610000604', '2016-10-15 16:55:58');
INSERT INTO qr_page_view VALUES ('6', '18618102693', '2130706433', 'WAMMPG201610000604', '2016-10-15 16:58:45');
INSERT INTO qr_page_view VALUES ('7', '18618102693', '2130706433', 'WAMMPG201610000604', '2016-10-15 17:00:08');
INSERT INTO qr_page_view VALUES ('8', '18618102693', '2130706433', 'WAMMPG201610000604', '2016-10-15 17:02:06');
INSERT INTO qr_page_view VALUES ('9', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:43:54');
INSERT INTO qr_page_view VALUES ('10', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:44:36');
INSERT INTO qr_page_view VALUES ('11', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:48:50');
INSERT INTO qr_page_view VALUES ('12', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:50:11');
INSERT INTO qr_page_view VALUES ('13', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:52:15');
INSERT INTO qr_page_view VALUES ('14', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:52:20');
INSERT INTO qr_page_view VALUES ('15', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:53:07');
INSERT INTO qr_page_view VALUES ('16', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:55:01');
INSERT INTO qr_page_view VALUES ('17', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:56:44');
INSERT INTO qr_page_view VALUES ('18', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:57:29');
INSERT INTO qr_page_view VALUES ('19', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:58:56');
INSERT INTO qr_page_view VALUES ('20', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:58:58');
INSERT INTO qr_page_view VALUES ('21', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:59:07');
INSERT INTO qr_page_view VALUES ('22', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 17:59:27');
INSERT INTO qr_page_view VALUES ('23', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 18:01:08');
INSERT INTO qr_page_view VALUES ('24', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 19:43:17');
INSERT INTO qr_page_view VALUES ('25', '18618102693', '2130706433', 'RGPHPG201610000607', '2016-10-15 19:43:21');
INSERT INTO qr_page_view VALUES ('26', '11011011011', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:45:02');
INSERT INTO qr_page_view VALUES ('27', '11011011011', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:45:04');
INSERT INTO qr_page_view VALUES ('28', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:54:55');
INSERT INTO qr_page_view VALUES ('29', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:55:26');
INSERT INTO qr_page_view VALUES ('30', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:55:52');
INSERT INTO qr_page_view VALUES ('31', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:55:55');
INSERT INTO qr_page_view VALUES ('32', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:55:59');
INSERT INTO qr_page_view VALUES ('33', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:57:54');
INSERT INTO qr_page_view VALUES ('34', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:58:40');
INSERT INTO qr_page_view VALUES ('35', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:59:00');
INSERT INTO qr_page_view VALUES ('36', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:59:24');
INSERT INTO qr_page_view VALUES ('37', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 20:59:37');
INSERT INTO qr_page_view VALUES ('38', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 21:01:36');
INSERT INTO qr_page_view VALUES ('39', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 21:01:44');
INSERT INTO qr_page_view VALUES ('40', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 21:02:31');
INSERT INTO qr_page_view VALUES ('41', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 21:03:26');
INSERT INTO qr_page_view VALUES ('42', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 21:09:20');
INSERT INTO qr_page_view VALUES ('43', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-15 21:12:58');
INSERT INTO qr_page_view VALUES ('44', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 10:16:49');
INSERT INTO qr_page_view VALUES ('45', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 14:51:52');
INSERT INTO qr_page_view VALUES ('46', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 14:58:21');
INSERT INTO qr_page_view VALUES ('47', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:00:26');
INSERT INTO qr_page_view VALUES ('48', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:01:04');
INSERT INTO qr_page_view VALUES ('49', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:01:15');
INSERT INTO qr_page_view VALUES ('50', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:02:05');
INSERT INTO qr_page_view VALUES ('51', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:08:44');
INSERT INTO qr_page_view VALUES ('52', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:11:01');
INSERT INTO qr_page_view VALUES ('53', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:11:48');
INSERT INTO qr_page_view VALUES ('54', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:13:35');
INSERT INTO qr_page_view VALUES ('55', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:15:23');
INSERT INTO qr_page_view VALUES ('56', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:21:51');
INSERT INTO qr_page_view VALUES ('57', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:29:00');
INSERT INTO qr_page_view VALUES ('58', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:41:48');
INSERT INTO qr_page_view VALUES ('59', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:42:09');
INSERT INTO qr_page_view VALUES ('60', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:42:40');
INSERT INTO qr_page_view VALUES ('61', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:43:42');
INSERT INTO qr_page_view VALUES ('62', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:45:41');
INSERT INTO qr_page_view VALUES ('63', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:47:18');
INSERT INTO qr_page_view VALUES ('64', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 15:48:04');
INSERT INTO qr_page_view VALUES ('65', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 16:13:23');
INSERT INTO qr_page_view VALUES ('66', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 20:14:43');
INSERT INTO qr_page_view VALUES ('67', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 21:03:15');
INSERT INTO qr_page_view VALUES ('68', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-16 21:22:38');
INSERT INTO qr_page_view VALUES ('69', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-18 13:08:47');
INSERT INTO qr_page_view VALUES ('70', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-18 21:16:31');
INSERT INTO qr_page_view VALUES ('71', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:02:22');
INSERT INTO qr_page_view VALUES ('72', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:05:29');
INSERT INTO qr_page_view VALUES ('73', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:06:13');
INSERT INTO qr_page_view VALUES ('74', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:07:38');
INSERT INTO qr_page_view VALUES ('75', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:08:15');
INSERT INTO qr_page_view VALUES ('76', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:09:26');
INSERT INTO qr_page_view VALUES ('77', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:09:54');
INSERT INTO qr_page_view VALUES ('78', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:10:18');
INSERT INTO qr_page_view VALUES ('79', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:31:19');
INSERT INTO qr_page_view VALUES ('80', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:50:55');
INSERT INTO qr_page_view VALUES ('81', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:51:09');
INSERT INTO qr_page_view VALUES ('82', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:52:30');
INSERT INTO qr_page_view VALUES ('83', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:54:12');
INSERT INTO qr_page_view VALUES ('84', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:56:19');
INSERT INTO qr_page_view VALUES ('85', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:57:01');
INSERT INTO qr_page_view VALUES ('86', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:58:21');
INSERT INTO qr_page_view VALUES ('87', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:58:23');
INSERT INTO qr_page_view VALUES ('88', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:58:24');
INSERT INTO qr_page_view VALUES ('89', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:58:24');
INSERT INTO qr_page_view VALUES ('90', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:58:24');
INSERT INTO qr_page_view VALUES ('91', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:58:24');
INSERT INTO qr_page_view VALUES ('92', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 10:59:27');
INSERT INTO qr_page_view VALUES ('93', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 14:56:29');
INSERT INTO qr_page_view VALUES ('94', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 20:58:18');
INSERT INTO qr_page_view VALUES ('95', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 21:12:06');
INSERT INTO qr_page_view VALUES ('96', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 21:15:25');
INSERT INTO qr_page_view VALUES ('97', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-22 21:19:36');
INSERT INTO qr_page_view VALUES ('98', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-29 10:38:53');
INSERT INTO qr_page_view VALUES ('99', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-29 10:41:25');
INSERT INTO qr_page_view VALUES ('100', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-29 10:50:05');
INSERT INTO qr_page_view VALUES ('101', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-29 10:50:08');
INSERT INTO qr_page_view VALUES ('102', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-29 10:50:09');
INSERT INTO qr_page_view VALUES ('103', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-29 10:50:09');
INSERT INTO qr_page_view VALUES ('104', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-29 16:24:53');
INSERT INTO qr_page_view VALUES ('105', '15600059309', '2130706433', 'RGPHPG201610000607', '2016-10-29 18:30:09');

-- ----------------------------
-- Table structure for `qr_partners`
-- ----------------------------
DROP TABLE IF EXISTS `qr_partners`;
CREATE TABLE `qr_partners` (
  `id` bigint(20) DEFAULT NULL,
  `partner_id` varchar(50) DEFAULT NULL,
  `partner_name` varchar(100) DEFAULT NULL,
  `partner_type` char(1) DEFAULT NULL COMMENT '合作商类型s(shop 超市),p(personal个人，小商小贩)',
  `address` varchar(100) DEFAULT NULL COMMENT '根据合作商身份，超市为门面地址，个人为家庭住址|刘志|20160909',
  ` identity_code` varchar(18) DEFAULT NULL COMMENT '根据partner_type决定，超市为工商结构号，个人为身份证号码|刘志|20160909',
  `picture` varchar(100) DEFAULT NULL COMMENT '机构或个人生活照片|刘志|20160909',
  `weixin_code` varchar(100) DEFAULT NULL COMMENT '超市或者个人微信号|刘志|20160909',
  `status` char(1) DEFAULT 'Y' COMMENT '合作商状态N：停用：Y启用',
  `description` varchar(200) DEFAULT NULL COMMENT '个人或者超市的介绍-扫描二维码能查看|刘志|20160909',
  `qr_url` varchar(100) DEFAULT NULL COMMENT '个人或者超市介绍二维码链接|刘志|20160909',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `lon` double(8,4) DEFAULT NULL COMMENT '经度，定位当前合作商的位置，个人要经常上传自己的经度|刘志|20160909',
  `lat` double(8,4) DEFAULT NULL COMMENT '纬度，定位当前合作商的位置，个人要经常上传自己的经度|刘志|20160909',
  `recommend_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '合作用户有推广code|刘志|20160914',
  `father_id` bigint(20) DEFAULT '0' COMMENT '改用户是谁推广的,qr_partners.id，普通用户为0|刘志|20160914'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合作商模块|存储超市和个人信息|刘志|20160909';

-- ----------------------------
-- Records of qr_partners
-- ----------------------------

-- ----------------------------
-- Table structure for `qr_partners_stock`
-- ----------------------------
DROP TABLE IF EXISTS `qr_partners_stock`;
CREATE TABLE `qr_partners_stock` (
  `id` bigint(20) DEFAULT NULL,
  `partner_id` bigint(20) DEFAULT NULL COMMENT 'qr_partners.id|合作商编码|刘志|20160909',
  `type` varchar(20) DEFAULT NULL COMMENT '水果类型（pg-苹果 hmg-哈密瓜）|刘志|20160831',
  `brand_id` bigint(50) DEFAULT NULL COMMENT '品牌编码|刘志|20160909',
  `available_qty` int(4) DEFAULT NULL COMMENT '可用库存|刘志|20160909',
  `freeze_qty` int(4) DEFAULT NULL COMMENT '冻结库存|刘志|20160909',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存模块|合作商家库存|刘志|20160909';

-- ----------------------------
-- Records of qr_partners_stock
-- ----------------------------

-- ----------------------------
-- Table structure for `qr_price_list`
-- ----------------------------
DROP TABLE IF EXISTS `qr_price_list`;
CREATE TABLE `qr_price_list` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `supermarket_id` int(10) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `type` char(2) COLLATE utf8_bin DEFAULT NULL,
  `brand_id` int(20) DEFAULT NULL,
  `price` double(4,2) DEFAULT NULL,
  `address` int(11) DEFAULT NULL,
  `last_price_id` int(20) DEFAULT NULL,
  `audit_flag` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qr_price_list
-- ----------------------------

-- ----------------------------
-- Table structure for `qr_reserved`
-- ----------------------------
DROP TABLE IF EXISTS `qr_reserved`;
CREATE TABLE `qr_reserved` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `farmer_id` bigint(20) DEFAULT NULL,
  `begin` int(10) DEFAULT NULL,
  `end` int(10) DEFAULT NULL,
  `status` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '0:保存中，1：审核中，2：审核通过等待付款，3审核未通过 4：已经付款，5:已经发货，6：已经收货 7:已经使用',
  `token` char(6) COLLATE utf8_bin DEFAULT NULL,
  `apply_count` int(6) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  `type` char(4) COLLATE utf8_bin DEFAULT NULL COMMENT '申请的类型 苹果：梨 西瓜等',
  `brand_id` bigint(20) DEFAULT NULL,
  `variety_id` bigint(20) DEFAULT NULL,
  `audit_desc` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '审核情况描述,前期手工审核，填写信息',
  `apply_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `file_path` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `picture_path` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `test_url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用于校验上传的信息能否和url对应上',
  `finish_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '这次申请是否已经完成，1：完成 0：未完成，标记二维码是否已经生成完',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `market_price` double(6,2) DEFAULT NULL COMMENT '上传是水果的市场价|刘志|20160906',
  `harvest_time` datetime DEFAULT NULL COMMENT '水果采摘时间|刘志|20160906',
  `unit` varchar(10) COLLATE utf8_bin DEFAULT '个' COMMENT '标示水果测量的单位（各，箱，袋）|刘志|20160927',
  `maturing_status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '水果的成熟度|刘志|20161001',
  `if_online_sale` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否在网上出售|刘志|20161009',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qr_reserved
-- ----------------------------
INSERT INTO qr_reserved VALUES ('11', '2', '303', '603', '6', 'FDRX', '300', '2016-10-03 15:42:53', 'PG', '1', '1', null, 'ddddddddddd', null, null, null, '0', '2016-10-07 12:30:49', null, null, '个', '9', null);
INSERT INTO qr_reserved VALUES ('12', '2', '604', '606', '7', 'WAMM', '3', '2016-10-07 12:32:33', 'PG', '1', '1', null, '3333333', 'I:/app/web_site/upload/files/file/2016/10/2_12.xlsx', 'I:/app/web_site/upload/files/images/2016/10/2_12.zip', null, '0', '2016-10-07 19:21:14', '8.12', '2016-10-07 19:15:00', '个', '8', null);
INSERT INTO qr_reserved VALUES ('13', '2', '607', '609', '7', 'RGPH', '3', '2016-10-15 17:29:33', 'PG', '1', '1', null, 'eee', 'I:/app/web_site/upload/files/file/2016/10/2_13.xlsx', 'I:/app/web_site/upload/files/images/2016/10/2_13.zip', null, '0', '2016-10-15 17:37:10', '8.12', '2016-10-15 17:36:00', '个', '9', null);
INSERT INTO qr_reserved VALUES ('14', '2', '610', '614', '7', 'DBOU', '5', '2016-10-30 12:22:02', 'PG', '1', '1', null, 'qr', 'I:/app/web_site/upload/files/file/2016/10/2_14.csv', 'I:/app/web_site/upload/files/images/2016/10/2_14.zip', null, '0', '2016-10-30 13:25:48', '12.23', '2016-10-30 13:14:00', '个', '9', null);

-- ----------------------------
-- Table structure for `qr_review`
-- ----------------------------
DROP TABLE IF EXISTS `qr_review`;
CREATE TABLE `qr_review` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_mobile` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '根据用户手机号或者openId',
  `open_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户先关注微信号后扫描评论|刘志|20161031',
  `content` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `taste_score` int(4) DEFAULT NULL COMMENT '口感得分|刘志|201610116',
  `sugar_score` int(4) DEFAULT NULL COMMENT '甜度隔得评分|刘志|20161016',
  `water_score` int(4) DEFAULT NULL COMMENT '水分含量评分|刘志|20161016',
  `star_level` int(4) DEFAULT NULL COMMENT '用户给打的分总体打分（用苹果代替）|刘志|20161013',
  `replay_flag` char(1) COLLATE utf8_bin DEFAULT NULL,
  `fruit_code` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `farmer_id` bigint(20) DEFAULT NULL COMMENT '种植户编码|评论是针对种植户的评论|刘志|20161015',
  `create_time` datetime DEFAULT NULL,
  `user_ip` bigint(20) DEFAULT NULL,
  `delete_flag` varchar(1) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除Y:删除 N：未删除|刘志|20161015',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qr_review
-- ----------------------------
INSERT INTO qr_review VALUES ('1', '18618102693', null, null, '3', '4', '5', '4', null, null, '2', '2016-10-16 14:13:51', null, 'N', '2016-10-16 14:13:55');
INSERT INTO qr_review VALUES ('2', '1818102693', null, null, '4', '5', '4', '3', null, null, '2', '2016-10-16 14:15:02', null, 'N', '2016-10-16 14:15:04');
INSERT INTO qr_review VALUES ('3', '15600059309', null, null, '5', '5', '5', '5', null, 'RGPHPG201610000607', '2', null, '2130706433', null, null);
INSERT INTO qr_review VALUES ('4', '15600059309', null, null, '5', '4', '5', '5', null, 'RGPHPG201610000607', '2', '2016-10-22 21:14:34', '2130706433', 'N', null);
INSERT INTO qr_review VALUES ('5', '15600059309', null, null, '5', '5', '5', '5', null, 'RGPHPG201610000607', '2', '2016-10-22 21:17:10', '2130706433', 'N', null);
INSERT INTO qr_review VALUES ('6', '15600059309', null, null, '3', '3', '4', '4', null, 'RGPHPG201610000607', '2', '2016-10-22 21:19:22', '2130706433', 'N', null);

-- ----------------------------
-- Table structure for `qr_template`
-- ----------------------------
DROP TABLE IF EXISTS `qr_template`;
CREATE TABLE `qr_template` (
  `template_id` int(20) NOT NULL AUTO_INCREMENT,
  `template_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `template_content` text COLLATE utf8_bin,
  `template_type` char(2) COLLATE utf8_bin DEFAULT NULL,
  `disable` char(1) COLLATE utf8_bin DEFAULT NULL,
  `status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `template_desc` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `template_pic` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `create_user_id` int(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qr_template
-- ----------------------------

-- ----------------------------
-- Table structure for `qr_trade_track`
-- ----------------------------
DROP TABLE IF EXISTS `qr_trade_track`;
CREATE TABLE `qr_trade_track` (
  `id` bigint(20) NOT NULL,
  `order_no` varchar(20) DEFAULT NULL,
  `trade_no` varchar(32) DEFAULT NULL,
  `status` varchar(4) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间|刘志|20161007',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qr_trade_track
-- ----------------------------

-- ----------------------------
-- Table structure for `qr_user`
-- ----------------------------
DROP TABLE IF EXISTS `qr_user`;
CREATE TABLE `qr_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `user_type` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '用户类型，普通用户 0，超市 1和小商小贩 2|刘志|20160914',
  `login_ip` bigint(20) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='网站用户';

-- ----------------------------
-- Records of qr_user
-- ----------------------------
INSERT INTO qr_user VALUES ('4', '18618102693', null, '555666de5e9d924ba256071143a447424f8f621de1b262bddc9d58ed', '1', '1995899098', '2016-09-11 19:19:06', '2016-09-11 19:19:06');
INSERT INTO qr_user VALUES ('5', '15600059309', null, '7e0cf64cea96ae9add67fb4b9d55c1cc95320a8a3e597b81f13366b5', '1', '1941425434', '2016-09-11 19:40:24', '2016-09-11 19:40:24');

-- ----------------------------
-- Table structure for `qr_user_copy`
-- ----------------------------
DROP TABLE IF EXISTS `qr_user_copy`;
CREATE TABLE `qr_user_copy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `user_type` char(1) COLLATE utf8_bin DEFAULT NULL,
  `login_ip` bigint(20) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='网站用户';

-- ----------------------------
-- Records of qr_user_copy
-- ----------------------------
INSERT INTO qr_user_copy VALUES ('3', '18618102693', null, '80be842351c77ad0b163d69f956887af87e35bf70632adaa411d5623', '1', '2130706433', '2016-08-28 18:38:46', '2016-08-28 18:38:46');

-- ----------------------------
-- Table structure for `qr_user_join`
-- ----------------------------
DROP TABLE IF EXISTS `qr_user_join`;
CREATE TABLE `qr_user_join` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(100) DEFAULT NULL,
  `join_type` char(2) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信联合登录';

-- ----------------------------
-- Records of qr_user_join
-- ----------------------------

-- ----------------------------
-- Table structure for `qr_variety`
-- ----------------------------
DROP TABLE IF EXISTS `qr_variety`;
CREATE TABLE `qr_variety` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '水果类型：首字母简称',
  `variety_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `brand_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qr_variety
-- ----------------------------
INSERT INTO qr_variety VALUES ('1', 'PG', '红富士', '2016-08-02 11:03:06', '红富士', '1');
INSERT INTO qr_variety VALUES ('2', 'XG', '京西西瓜', '2016-09-03 11:23:35', '京西', '3');

-- ----------------------------
-- Table structure for `store_product_stock`
-- ----------------------------
DROP TABLE IF EXISTS `store_product_stock`;
CREATE TABLE `store_product_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `skuid` int(11) NOT NULL COMMENT 'skuid',
  `itemid` int(11) NOT NULL COMMENT '商品ID',
  `product_no` varchar(50) NOT NULL COMMENT '商品编码',
  `storage` varchar(50) NOT NULL COMMENT '仓库编码',
  `available_qty` int(11) DEFAULT NULL COMMENT '商品可卖数',
  `freeze_qty` int(11) DEFAULT NULL COMMENT '冻结数量',
  `virtual_qty` int(11) DEFAULT NULL COMMENT '虚拟库存',
  `virtual_freeze_qty` int(11) DEFAULT NULL COMMENT '虚拟冻结库存',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` int(1) DEFAULT '1' COMMENT '状态 1:可用 0:不可用',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_productno_mas_loc` (`product_no`,`storage`),
  KEY `index_itemid` (`itemid`),
  KEY `index_skuid` (`skuid`),
  KEY `storage` (`storage`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of store_product_stock
-- ----------------------------

-- ----------------------------
-- Table structure for `user_cart`
-- ----------------------------
DROP TABLE IF EXISTS `user_cart`;
CREATE TABLE `user_cart` (
  `id` bigint(20) DEFAULT NULL,
  `fruit_id` bigint(20) DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `add_ip` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_cart
-- ----------------------------
