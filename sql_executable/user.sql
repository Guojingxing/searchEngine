/*
Navicat MySQL Data Transfer

Source Server         : t1
Source Server Version : 50559
Source Host           : localhost:3306
Source Database       : search

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2021-03-10 13:05:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` tinyint(1) DEFAULT '1',
  `institution` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123', '123', '1', '华中科技大学', '123456@qq.com', '16153453541071.png');
INSERT INTO `user` VALUES ('1234', '1234', '1', '华中科技大学', '123456@qq.com', null);
INSERT INTO `user` VALUES ('12345', '12345', '1', '华中科技大学', '123@qq.com', null);
INSERT INTO `user` VALUES ('123456', '123', '0', '华中科技大学', '123456@qq.com', null);
INSERT INTO `user` VALUES ('12345678', '12345687', '1', '华中科技大学', '12345678@qq.com', null);
INSERT INTO `user` VALUES ('234', '2345', '1', null, 'guojingxing0905@gmail.com', null);
INSERT INTO `user` VALUES ('abc', '123', '1', 'abc', 'abc@game.com', null);
INSERT INTO `user` VALUES ('abcd', '123', '1', 'The White house', '1234@qq.com', null);
INSERT INTO `user` VALUES ('Barack Obama', '123', '1', 'The White house', 'obama@us.gov', null);
INSERT INTO `user` VALUES ('guojingxing123', '123', '1', '华中科技大学', '784830122@qq.com', null);
INSERT INTO `user` VALUES ('Joe biden', '123', '1', 'The White house', 'joebiden@us.gov', null);
INSERT INTO `user` VALUES ('trump', '123', '1', '123', '123@qq.com', null);
