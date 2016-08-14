DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) default NULL,
  `uid` bigint(20) default NULL,
  `total_price` double default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `orders` VALUES ('1', '1', '30');

DROP TABLE IF EXISTS `sub_order`;
CREATE TABLE `sub_order` (
  `id` bigint(20) default NULL,
  `order_id` bigint(20) default NULL,
  `product_id` bigint(20) default NULL,
  `nums` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sub_order` VALUES ('1', '1', '1', '10');
INSERT INTO `sub_order` VALUES ('2', '1', '2', '20');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `pwd` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES ('1', 'fuwei1', 'fuwei123');
INSERT INTO `user` VALUES ('2', 'fuwei', '123');

DROP TABLE IF EXISTS `user_ext`;
CREATE TABLE `user_ext` (
  `id` bigint(20) default NULL,
  `uid` bigint(20) default NULL,
  `account_no` varchar(255) default NULL,
  `birth_date` timestamp NULL default NULL on update CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user_ext` VALUES ('1', '1', '110', '2016-08-13 18:04:56');
INSERT INTO `user_ext` VALUES ('2', '2', '111', '2016-08-15 18:05:50');