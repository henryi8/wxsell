/*
SQLyog Trial v12.2.1 (64 bit)
MySQL - 5.7.21 : Database - sell
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sell` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sell`;

/*Table structure for table `order_detail` */

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '商品小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

/*Data for the table `order_detail` */

insert  into `order_detail`(`detail_id`,`order_id`,`product_id`,`product_name`,`product_price`,`product_quantity`,`product_icon`,`create_time`,`update_time`) values 
('100','1731','1234','煲仔饭1','10.40',1333,'http://www.c3online.com.cn/data/attachment/forum/3729/1130.jpg','2018-08-02 16:17:09','2018-08-02 16:17:09'),
('101','1731','1234','煲仔饭2','10.40',1333,'http://www.c3online.com.cn/data/attachment/forum/3729/1130.jpg','2018-08-02 16:18:31','2018-08-02 16:18:31'),
('1533948468674257066','1533948468592445049','1234','煲仔饭','18.00',1,'http://xxxxx.jpg','2018-07-24 15:00:01','2018-07-24 15:00:01'),
('1533948601651264873','1533948601565485625','1234','煲仔饭','18.00',1,'http://xxxxx.jpg','2018-07-24 15:00:01','2018-07-24 15:00:01'),
('1533948648361623649','1533948648254468006','1234','煲仔饭','18.00',1,'http://xxxxx.jpg','2018-07-24 15:00:01','2018-07-24 15:00:01'),
('1533948888604626619','1533948888437606001','1234','煲仔饭','18.00',1,'http://xxxxx.jpg','2018-07-24 15:00:01','2018-08-11 08:50:48'),
('1533949011526121605','1533949011452269159','1234','煲仔饭','18.00',1,'http://xxxxx.jpg','2018-07-24 15:00:01','2018-08-11 08:54:49'),
('1533949333584196302','1533949333450640988','1234','煲仔饭','18.00',1,'http://xxxxx.jpg','2018-07-24 15:00:01','2018-08-11 08:56:52'),
('1533949333615888131','1533949333450640988','123456','皮蛋粥','3.20',3,'http://xxxxx.jpg','2018-08-11 09:02:13','2018-08-11 09:02:13'),
('1533949491644284569','1533949491559641997','1234','煲仔饭','18.00',1,'http://xxxxx.jpg','2018-07-24 15:00:01','2018-08-11 09:02:14'),
('1533949491681415126','1533949491559641997','123456','皮蛋粥','3.20',3,'http://xxxxx.jpg','2018-08-11 09:02:54','2018-08-11 09:02:14'),
('1533951160099239496','1533951159943412548','1234','煲仔饭','18.00',1,'http://xxxxx.jpg','2018-07-24 15:00:01','2018-08-11 09:04:52'),
('1533951160139761248','1533951159943412548','123456','皮蛋粥','3.20',3,'http://xxxxx.jpg','2018-08-11 09:02:54','2018-08-11 09:04:52');

/*Table structure for table `order_master` */

DROP TABLE IF EXISTS `order_master`;

CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openId',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认0新下单，1：完结，2：已取消',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单主表';

/*Data for the table `order_master` */

insert  into `order_master`(`order_id`,`buyer_name`,`buyer_phone`,`buyer_address`,`buyer_openid`,`order_amount`,`order_status`,`pay_status`,`create_time`,`update_time`) values 
('1533948468592445049','大佬','1297475921222','上海市漕河泾开发区','ABD127','18.00',0,0,'2018-08-11 08:47:49','2018-08-11 08:47:49'),
('1533948601565485625','大佬','1297475921222','上海市漕河泾开发区','ABD127','18.00',0,0,'2018-08-11 08:50:02','2018-08-11 08:50:02'),
('1533948648254468006','大佬','1297475921222','上海市漕河泾开发区','ABD127','18.00',0,0,'2018-08-11 08:50:48','2018-08-11 08:50:48'),
('1533948888437606001','大佬','1297475921222','上海市漕河泾开发区','ABD127','18.00',0,0,'2018-08-11 08:54:49','2018-08-11 08:54:49'),
('1533949011452269159','大佬','1297475921222','上海市漕河泾开发区','ABD127','18.00',0,0,'2018-08-11 08:56:52','2018-08-11 08:56:52'),
('1533949333450640988','大佬','1297475921222','上海市漕河泾开发区','ABD127','27.60',0,0,'2018-08-11 09:02:14','2018-08-11 09:02:14'),
('1533949491559641997','大佬','1297475921222','上海市漕河泾开发区','ABD127','27.60',0,0,'2018-08-11 09:04:52','2018-08-11 09:04:52'),
('1533951159943412548','大佬','1297475921222','上海市漕河泾开发区','ABD127','27.60',0,0,'2018-08-11 09:32:40','2018-08-11 09:32:40'),
('1731','帅哥','153746638194','上海市漕河泾开发区','123Abs','1.30',0,0,'2018-08-02 11:18:05','2018-08-02 11:18:05'),
('17312','嗯地方','153746638194','上海市漕河泾开发区','123123','2.60',0,0,'2018-08-11 09:54:02','2018-08-11 09:54:02');

/*Table structure for table `product_category` */

DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_type` (`category_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='类目表';

/*Data for the table `product_category` */

insert  into `product_category`(`category_id`,`category_name`,`category_type`,`create_time`,`update_time`) values 
(1,'热销排行',2,'2018-07-29 17:31:25','2018-07-29 17:31:25'),
(2,'香菇难受',10,'2018-07-30 15:32:59','2018-07-30 15:33:01'),
(3,'心里伤心',5,'2018-07-30 15:36:24','2018-07-30 15:36:24');

/*Table structure for table `product_info` */

DROP TABLE IF EXISTS `product_info`;

CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '图像',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `product_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '商品状态 0：正常，1：下架',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品详情表';

/*Data for the table `product_info` */

insert  into `product_info`(`product_id`,`product_name`,`product_price`,`product_stock`,`product_description`,`product_icon`,`category_type`,`product_status`,`create_time`,`update_time`) values 
('1234','煲仔饭','18.00',92,'好吃又香甜的改基因大米！','http://xxxxx.jpg',2,0,'2018-07-24 15:00:01','2018-08-11 09:32:40'),
('123456','皮蛋粥','3.20',91,'很好喝的粥','http://xxxxx.jpg',10,0,'2018-08-11 09:02:54','2018-08-11 09:32:40'),
('1314520','小可爱','4.80',120,'香香甜甜小可爱','http://xxxxx.jpg',5,0,'2018-08-01 11:23:01','2018-08-01 11:23:01');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
