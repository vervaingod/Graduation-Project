/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : classroommanage

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-03-08 16:01:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for applyinfo
-- ----------------------------
DROP TABLE IF EXISTS `applyinfo`;
CREATE TABLE `applyinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applystudent` varchar(255) DEFAULT NULL,
  `applyclassroom` varchar(255) DEFAULT NULL,
  `roomstate` int(255) DEFAULT NULL,
  `applyday` varchar(255) DEFAULT NULL,
  `applystate` int(255) DEFAULT NULL,
  `applyreply` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of applyinfo
-- ----------------------------
INSERT INTO `applyinfo` VALUES ('1', 'xujiayang', '11211', '1', null, '0', null, 'haha');
INSERT INTO `applyinfo` VALUES ('2', 'yanfan', '12111', '1', null, '-1', null, '待评价');
INSERT INTO `applyinfo` VALUES ('3', 'godyang', '10001', '1', null, '1', null, '该生保持了环境卫生');
INSERT INTO `applyinfo` VALUES ('10', 'yy', '01401', '1', null, '0', null, null);
INSERT INTO `applyinfo` VALUES ('11', 'fanshao', '11202', '1', null, '-1', null, null);
INSERT INTO `applyinfo` VALUES ('12', 'ss', '11201', '1', null, '1', null, '待评价');
INSERT INTO `applyinfo` VALUES ('35', '1221010619', '11201', null, null, '-1', null, null);
INSERT INTO `applyinfo` VALUES ('36', '1221010619', '10203', '1', null, '1', null, null);
INSERT INTO `applyinfo` VALUES ('37', '1221010620', '11201', '1', null, '-1', null, null);
INSERT INTO `applyinfo` VALUES ('38', '1221010618', '10203', '1', null, '1', null, null);
INSERT INTO `applyinfo` VALUES ('39', '1221010618', '12101', '1', null, '1', null, '保持了环境卫生');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classnum` varchar(255) DEFAULT NULL,
  `classname` varchar(255) DEFAULT NULL,
  `classroom` varchar(255) DEFAULT NULL,
  `classstate` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', '1', '高等数学', '11201', '1');
INSERT INTO `class` VALUES ('2', '2', '线性代数', '12203', '1');
INSERT INTO `class` VALUES ('3', '3', '软件工程导论', '10001', '1');
INSERT INTO `class` VALUES ('4', '4', '数学', '01203', '1');

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `classroomnumber` varchar(10) DEFAULT NULL,
  `room` varchar(10) DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES ('1', '11203', '11201', '1', '1');
INSERT INTO `classroom` VALUES ('2', '10203', '10203', '1', '自习室');
INSERT INTO `classroom` VALUES ('3', '12101', '12101', '0', '自习室');
INSERT INTO `classroom` VALUES ('4', '09211', '09201', '1', '自习室');

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `peopleid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `classid` varchar(11) DEFAULT NULL,
  `phone` int(255) DEFAULT NULL,
  `role` int(255) DEFAULT NULL,
  `state` int(255) DEFAULT NULL,
  PRIMARY KEY (`peopleid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES ('1', 'xujiayang', 'vervain', '6666', '742749059@qq.com', '1', '1873453874', '1', '1');
INSERT INTO `people` VALUES ('2', 'fanshao', 'yy', '1', '1234567@qq.com', '2', '1111111111', '0', '1');
INSERT INTO `people` VALUES ('3', 'haha', 'ss', '1', '11', '11', '11', '0', '1');
INSERT INTO `people` VALUES ('4', '许嘉阳', '11', '11', '11', null, '11', '1', '1');
INSERT INTO `people` VALUES ('5', '1111', '11', '11', '11', null, '111', '1', '1');
INSERT INTO `people` VALUES ('6', '22', '22', '222222', null, '22', '0', '1', '1');
INSERT INTO `people` VALUES ('8', 'godyang', '1221010617', '1221010617', null, '12210A02', '0', '0', '0');
INSERT INTO `people` VALUES ('9', 'yang', '1221010615', '1221010615', null, '12210A02', '0', '0', '0');
INSERT INTO `people` VALUES ('10', '1221010614', '1221010614', '1221010614', null, '12210A02', '0', '0', '0');
INSERT INTO `people` VALUES ('15', '1221010619', '1221010619', '1221010619', null, 'A02', '0', '0', '1');
INSERT INTO `people` VALUES ('16', 'xujiayang', '1221010620', '1221010620', null, 'A02', '0', '0', '1');
INSERT INTO `people` VALUES ('18', 'xujiayang', '1221010618', '1221010618', null, 'A02', '0', '0', '1');
