/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : equipmentmanage

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-08-05 15:44:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `announcement`
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `announcement_id` int(11) NOT NULL AUTO_INCREMENT,
  `announcement_name` varchar(255) DEFAULT NULL,
  `announcement_state` int(11) DEFAULT NULL,
  `announcement_time` varchar(20) DEFAULT NULL,
  `announcement_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`announcement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES ('1', '休息通知', '1', '2016-08-03 14:51:53', '     8月2日上午休息，周知..');
INSERT INTO `announcement` VALUES ('4', '系统维护', '1', '2016-08-02 09:12:58', '    8月5日系统进行维护，届时系统将不能使用，给大家带来不便敬请谅解。');
INSERT INTO `announcement` VALUES ('5', '系统维护', '1', '2016-08-01 16:16:24', '    定于8月3日上午10时维护系统，大约一小时。届时系统将会暂停使用。周知。');
INSERT INTO `announcement` VALUES ('6', '欢迎好又快公司加入', '1', '2016-08-01 16:16:44', '     热烈欢迎好又快维修公司的加入。');
INSERT INTO `announcement` VALUES ('7', '放假通知', '1', '2016-08-01 16:16:25', '     8月10日至12日放假休息，周知');
INSERT INTO `announcement` VALUES ('8', '休息通知', '1', '2016-08-02 09:24:33', '8月3日下午放假半天，周知');
INSERT INTO `announcement` VALUES ('9', '休息通知', '1', '2016-08-04 16:14:51', '   2016 8月4日休息，周知.');

-- ----------------------------
-- Table structure for `cost`
-- ----------------------------
DROP TABLE IF EXISTS `cost`;
CREATE TABLE `cost` (
  `cost_id` int(10) NOT NULL AUTO_INCREMENT,
  `cost_project` varchar(10) DEFAULT NULL,
  `cost_service` varchar(10) DEFAULT NULL,
  `cost_material` varchar(10) DEFAULT NULL,
  `cost_maintenance_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`cost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cost
-- ----------------------------
INSERT INTO `cost` VALUES ('8', '电脑病毒', '80', '20', '6');
INSERT INTO `cost` VALUES ('10', '电脑病毒', '80', '30', '4');
INSERT INTO `cost` VALUES ('11', '其他', '80', '20', '4');
INSERT INTO `cost` VALUES ('12', '电脑病毒', '80', '60', '27');
INSERT INTO `cost` VALUES ('14', '电脑病毒', '80', '60', '28');
INSERT INTO `cost` VALUES ('15', '连不上网', '70', '0', '28');
INSERT INTO `cost` VALUES ('16', '蓝屏死机', '100', '10', '29');
INSERT INTO `cost` VALUES ('17', '电脑病毒', '80', '50', '30');
INSERT INTO `cost` VALUES ('18', '电脑病毒', '80', '20', '31');
INSERT INTO `cost` VALUES ('19', '其他', '80', '30', '33');
INSERT INTO `cost` VALUES ('22', '电脑病毒', '80', '30', '34');
INSERT INTO `cost` VALUES ('23', '电脑病毒', '80', '20', '40');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(15) DEFAULT NULL,
  `department_linkman` varchar(15) DEFAULT NULL,
  `department_username` varchar(15) DEFAULT NULL,
  `department_tel` varchar(15) DEFAULT NULL,
  `department_type` int(10) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '河西分局', '王二胖', 'dd', '187351587', '1');
INSERT INTO `department` VALUES ('2', '和平北路派出所', '刘警官', 'yy', '15452354202', '2');
INSERT INTO `department` VALUES ('3', '好友快维修公司', '赵总', 'ss', '18757654578', '3');
INSERT INTO `department` VALUES ('4', '嘉禾维修公司', '王总', 'sk', '18765152456', '3');
INSERT INTO `department` VALUES ('5', '和平南路派出所', '张警官', 'er', '18535457412', '2');
INSERT INTO `department` VALUES ('7', '小井峪派出所', '赵警官', 'fg', '18736512589', '2');
INSERT INTO `department` VALUES ('8', '快又好维修公司', '许总', 'la3652145', '18456254571', '3');
INSERT INTO `department` VALUES ('9', '兴华小区派出所', '许文强', 'as1', '18756542145', '2');
INSERT INTO `department` VALUES ('10', '南寒派出所', '刘三', 'zsd3', '13702365123', '2');
INSERT INTO `department` VALUES ('11', '好得快维修公司', '赵三', 'sdf', '13735403521', '3');
INSERT INTO `department` VALUES ('13', '真利维修公司', '李丽', 'z2', '13735620324', '3');

-- ----------------------------
-- Table structure for `dispatch`
-- ----------------------------
DROP TABLE IF EXISTS `dispatch`;
CREATE TABLE `dispatch` (
  `dispatch_id` int(10) NOT NULL AUTO_INCREMENT,
  `dispatch_user_id` int(10) DEFAULT NULL,
  `dispatch_maintenance_id` int(10) DEFAULT NULL,
  `dispatch_expect_time` varchar(20) DEFAULT NULL,
  `dispatch_maintenance_phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`dispatch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch
-- ----------------------------
INSERT INTO `dispatch` VALUES ('5', '4', '27', '2016-7-30', '15478568700');
INSERT INTO `dispatch` VALUES ('6', '4', '28', '2016-08-02', '15478568700');
INSERT INTO `dispatch` VALUES ('7', '4', '29', '2016-08-02', '15478568700');
INSERT INTO `dispatch` VALUES ('8', '4', '30', '2016-08-03', '15478568700');
INSERT INTO `dispatch` VALUES ('9', '4', '31', '2016-08-03', '15478568700');
INSERT INTO `dispatch` VALUES ('10', '4', '33', '2016-08-03', '15478568700');
INSERT INTO `dispatch` VALUES ('11', '4', '34', '2016-08-03', '15478568700');
INSERT INTO `dispatch` VALUES ('12', '4', '38', '2016-8-4', '15478568700');
INSERT INTO `dispatch` VALUES ('13', '4', '40', '2016-08-06', '15478568700');

-- ----------------------------
-- Table structure for `fail_type`
-- ----------------------------
DROP TABLE IF EXISTS `fail_type`;
CREATE TABLE `fail_type` (
  `fail_type_id` int(10) NOT NULL AUTO_INCREMENT,
  `fail_type_name` varchar(10) DEFAULT NULL,
  `fail_type_service_fee` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`fail_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fail_type
-- ----------------------------
INSERT INTO `fail_type` VALUES ('1', '电脑病毒', '80');
INSERT INTO `fail_type` VALUES ('2', '蓝屏死机', '100');
INSERT INTO `fail_type` VALUES ('3', '连不上网', '70');
INSERT INTO `fail_type` VALUES ('4', '其他', '80');
INSERT INTO `fail_type` VALUES ('5', '监控器', '120');

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` int(10) NOT NULL AUTO_INCREMENT,
  `log_operation` varchar(10) DEFAULT NULL,
  `log_content` varchar(10) DEFAULT NULL,
  `log_people` varchar(10) DEFAULT NULL,
  `log_time` varchar(40) DEFAULT NULL,
  `log_maintenance_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('7', '申报', '维修申报', '王二胖', '2016-07-30 09:38:54', '27');
INSERT INTO `log` VALUES ('8', '派工', '派遣维修工', '赵总', '2016-07-30 09:39:40', '27');
INSERT INTO `log` VALUES ('9', '受理', '受理维修', '张师傅', '2016-07-30 09:42:59', '27');
INSERT INTO `log` VALUES ('11', '确认', '确认维修完成', '王二胖', '2016-07-30 09:43:38', '27');
INSERT INTO `log` VALUES ('13', '申报', '维修申报', '王二胖', '2016-08-01 15:13:07', '28');
INSERT INTO `log` VALUES ('14', '派工', '派遣维修工', '赵总', '2016-08-01 15:14:05', '28');
INSERT INTO `log` VALUES ('15', '受理', '受理维修', '张师傅', '2016-08-01 15:17:34', '28');
INSERT INTO `log` VALUES ('16', '确认', '确认维修完成', '王二胖', '2016-08-01 15:18:21', '28');
INSERT INTO `log` VALUES ('17', '申报', '维修申报', '王二胖', '2016-08-01 15:24:48', '29');
INSERT INTO `log` VALUES ('18', '派工', '派遣维修工', '赵总', '2016-08-01 15:25:25', '29');
INSERT INTO `log` VALUES ('19', '受理', '受理维修', '张师傅', '2016-08-01 15:26:52', '29');
INSERT INTO `log` VALUES ('20', '确认', '确认维修完成', '王二胖', '2016-08-01 15:27:50', '29');
INSERT INTO `log` VALUES ('21', '申报', '维修申报', '王二胖', '2016-08-02 15:12:48', '30');
INSERT INTO `log` VALUES ('22', '申报', '维修申报', '王二胖', '2016-08-02 15:12:48', '31');
INSERT INTO `log` VALUES ('23', '申报', '维修申报', '王二胖', '2016-08-02 15:33:38', '32');
INSERT INTO `log` VALUES ('24', '申报', '维修申报', '王二胖', '2016-08-02 15:33:38', '33');
INSERT INTO `log` VALUES ('25', '申报', '维修申报', '王二胖', '2016-08-02 15:33:38', '34');
INSERT INTO `log` VALUES ('26', '派工', '派遣维修工', '赵总', '2016-08-02 15:59:55', '30');
INSERT INTO `log` VALUES ('27', '派工', '派遣维修工', '赵总', '2016-08-02 16:00:14', '31');
INSERT INTO `log` VALUES ('28', '受理', '受理维修', '张师傅', '2016-08-02 17:46:23', '30');
INSERT INTO `log` VALUES ('29', '申报', '维修申报', '王二胖', '2016-08-03 09:00:20', '35');
INSERT INTO `log` VALUES ('30', '申报', '维修申报', '王二胖', '2016-08-03 10:23:51', '36');
INSERT INTO `log` VALUES ('31', '作废', '派工作废', '赵总', '2016-08-03 10:24:38', '36');
INSERT INTO `log` VALUES ('32', '派工', '派遣维修工', '赵总', '2016-08-03 15:22:00', '33');
INSERT INTO `log` VALUES ('33', '派工', '派遣维修工', '赵总', '2016-08-03 15:22:21', '34');
INSERT INTO `log` VALUES ('34', '受理', '受理维修', '张师傅', '2016-08-03 15:29:35', '31');
INSERT INTO `log` VALUES ('35', '受理', '受理维修', '张师傅', '2016-08-03 15:30:54', '33');
INSERT INTO `log` VALUES ('36', '申报', '维修申报', '刘警官', '2016-08-04 09:22:53', '37');
INSERT INTO `log` VALUES ('37', '申报', '维修申报', '王二胖', '2016-08-04 15:35:31', '38');
INSERT INTO `log` VALUES ('38', '派工', '派遣维修工', '赵总', '2016-08-04 15:36:29', '38');
INSERT INTO `log` VALUES ('39', '受理', '受理维修', '张师傅', '2016-08-04 15:46:53', '34');
INSERT INTO `log` VALUES ('40', '确认', '确认维修完成', '王二胖', '2016-08-04 15:47:15', '34');
INSERT INTO `log` VALUES ('41', '申报', '维修申报', '王二胖', '2016-08-04 15:35:31', '39');
INSERT INTO `log` VALUES ('42', '作废', '派工作废', '赵总', '2016-08-04 15:49:47', '39');
INSERT INTO `log` VALUES ('43', '申报', '维修申报', '王二胖', '2016-08-05 15:15:21', '40');
INSERT INTO `log` VALUES ('44', '派工', '派遣维修工', '赵总', '2016-08-05 15:15:56', '40');
INSERT INTO `log` VALUES ('45', '受理', '受理维修', '张师傅', '2016-08-05 15:18:01', '40');
INSERT INTO `log` VALUES ('46', '确认', '确认维修完成', '王二胖', '2016-08-05 15:18:56', '40');

-- ----------------------------
-- Table structure for `maintenance`
-- ----------------------------
DROP TABLE IF EXISTS `maintenance`;
CREATE TABLE `maintenance` (
  `maintenance_id` int(10) NOT NULL AUTO_INCREMENT,
  `maintenance_department` varchar(10) DEFAULT NULL,
  `maintenance_unit` varchar(10) DEFAULT NULL,
  `maintenance_name` varchar(10) DEFAULT NULL,
  `maintenance_phone` varchar(13) DEFAULT NULL,
  `maintenance_time` varchar(100) DEFAULT NULL,
  `maintenance_fail_type` varchar(10) DEFAULT NULL,
  `maintenance_description` varchar(1000) DEFAULT NULL,
  `maintenance_dispatch_state` int(10) DEFAULT NULL,
  `maintenance_accept_state` int(10) DEFAULT NULL,
  `maintenance_confirm` int(10) DEFAULT NULL,
  `maintenance_record` int(10) DEFAULT NULL,
  `maintenance_equipment` varchar(15) DEFAULT NULL,
  `maintenance_IP` varchar(30) DEFAULT NULL,
  `maintenance_MAC` varchar(30) DEFAULT NULL,
  `maintenance_arrive_time` varchar(20) DEFAULT NULL,
  `maintenance_completion` varchar(20) DEFAULT NULL,
  `maintenance_material_cost` int(10) DEFAULT NULL,
  `maintenance_sum_cost` int(10) DEFAULT NULL,
  PRIMARY KEY (`maintenance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintenance
-- ----------------------------
INSERT INTO `maintenance` VALUES ('27', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-07-30 09:38:54', '蓝屏死机', '电脑一开机就蓝屏了', '1', '1', '1', '0', '电脑', '192.168.1.1', 'A2-25-6B-C2-12-2A', null, '2016-08-01 14:53:12', null, '270');
INSERT INTO `maintenance` VALUES ('28', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-08-01 15:13:07', '连不上网', '设备连不上网了。错误691', '1', '1', '1', '0', '电脑', '192.168.1.1', 'A2-2B-31-11-100', null, '2016-08-01 15:17:34', null, '210');
INSERT INTO `maintenance` VALUES ('29', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-08-01 15:24:48', '蓝屏死机', '电脑无法开机了，快派人来看看', '1', '1', '1', '0', '电脑', '192.168.1.1', 'E1-12-2A-13-35-C2', null, '2016-08-01 15:26:52', null, '110');
INSERT INTO `maintenance` VALUES ('30', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-08-02 15:12:48', '蓝屏死机', '电脑没法正常开机', '1', '1', '0', '1', '电脑', '192.168.1.1', 'E1-35-EA-2C-33-21', null, '2016-08-02 17:46:23', null, '130');
INSERT INTO `maintenance` VALUES ('31', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-08-02 15:12:48', '监控器', '监控器没法正常工作了', '1', '1', '0', '0', '监控器', '192.168.1.1', 'E1-A2-E3-32-13-1C', null, '2016-08-03 15:29:35', null, '100');
INSERT INTO `maintenance` VALUES ('33', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-08-02 15:33:38', '其他', '鼠标的接口坏了', '1', '1', '0', '0', '鼠标接口', '192.168.1.1', 'E3-31-A2-C2-AA-36', null, '2016-08-03 15:30:54', null, '110');
INSERT INTO `maintenance` VALUES ('34', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-08-02 15:33:38', '电脑病毒', '好几台电脑中了毒，自动关机', '1', '1', '1', '0', '电脑', '192.168.1.1', 'E1-A2-22-2A-23-32', null, '2016-08-04 15:46:53', null, '110');
INSERT INTO `maintenance` VALUES ('35', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-08-03 09:00:20', '电脑病毒', '电脑开不了机，疑似中毒', '0', '0', '0', null, null, null, null, null, null, null, null);
INSERT INTO `maintenance` VALUES ('36', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-08-03 10:23:51', '其他', '鼠标和键盘都失灵', '-1', '0', '0', null, null, null, null, null, null, null, null);
INSERT INTO `maintenance` VALUES ('37', '和平北路派出所', '好友快维修公司', '刘警官', '15452354100', '2016-08-04 09:22:53', '电脑病毒', '电脑中毒了，很卡', '0', '0', '0', null, null, null, null, null, null, null, null);
INSERT INTO `maintenance` VALUES ('38', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-08-04 15:35:31', '监控器', '监控器不能正常工作', '1', '0', '0', null, null, null, null, null, null, null, null);
INSERT INTO `maintenance` VALUES ('39', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-08-04 15:35:31', '蓝屏死机', 'kuailai', '-1', '0', '0', null, null, null, null, null, null, null, null);
INSERT INTO `maintenance` VALUES ('40', '河西分局', '好友快维修公司', '王二胖', '18735158700', '2016-08-05 15:15:21', '电脑病毒', '电脑中毒不能开机', '1', '1', '1', '0', '电脑', '192.168.1.1', 'E1-A2-31-56-34-31', null, '2016-08-05 15:18:01', null, '100');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` int(10) NOT NULL AUTO_INCREMENT,
  `permission_information` int(10) DEFAULT NULL,
  `permission_account` int(10) DEFAULT NULL,
  `permission_department` int(10) DEFAULT NULL,
  `permission_fail_type` int(10) DEFAULT NULL,
  `permission_unit` int(10) DEFAULT NULL,
  `permission_announcement` int(10) DEFAULT NULL,
  `permission_sign` int(10) DEFAULT NULL,
  `permission_statistical` int(10) DEFAULT NULL,
  `permission_password` int(10) DEFAULT NULL,
  `permission_declare` int(10) DEFAULT NULL,
  `permission_confirm` int(10) DEFAULT NULL,
  `permission_accept` int(10) DEFAULT NULL,
  `permission_dispatch` int(10) DEFAULT NULL,
  `permission_role` int(10) NOT NULL DEFAULT '0',
  `permission_department_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '1');
INSERT INTO `permission` VALUES ('2', '1', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '0', '0', '21', '2');
INSERT INTO `permission` VALUES ('3', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '1', '1', '10', '3');
INSERT INTO `permission` VALUES ('4', '1', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '11', '3');
INSERT INTO `permission` VALUES ('5', '1', '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '0', '20', '2');
INSERT INTO `permission` VALUES ('6', '1', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '1', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_username` varchar(10) NOT NULL,
  `user_password` varchar(10) NOT NULL,
  `user_copyright` varchar(10) DEFAULT NULL,
  `user_technical_support` varchar(10) DEFAULT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `user_phone` varchar(11) DEFAULT NULL,
  `user_role` int(11) NOT NULL,
  `user_department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'dd', '123', 'vervain', 'godyang', '王二胖', '18735158700', '0', '1');
INSERT INTO `user` VALUES ('2', 'yy', '123', null, null, '刘警官', '15452354100', '20', '2');
INSERT INTO `user` VALUES ('3', 'ss', '123', ' ', '', '赵总', '18754562100', '10', '3');
INSERT INTO `user` VALUES ('4', 'vervain', '123', null, null, '张师傅', '15478568700', '11', '3');
INSERT INTO `user` VALUES ('5', 'ww', '123', null, null, '杨警官', '12345612300', '21', '2');
INSERT INTO `user` VALUES ('6', 'hh', '123', null, null, '许师傅', '12589574400', '11', '3');
INSERT INTO `user` VALUES ('9', 'qwe', '123', null, null, 'xujiayang', '18735152475', '1', '1');
INSERT INTO `user` VALUES ('10', 'asd', '123', null, null, '高师傅', '18578963214', '11', '3');
INSERT INTO `user` VALUES ('11', 'zxc', '123', null, null, 'godyang', '15654874512', '1', '1');
INSERT INTO `user` VALUES ('12', 'sk', '123', null, null, '王总', '18765152458', '10', '4');
INSERT INTO `user` VALUES ('15', '123', '123', null, null, '321', '9484576451', '1', '1');
INSERT INTO `user` VALUES ('16', 'er', '123', null, null, '张警官', '18535457412', '0', '0');
INSERT INTO `user` VALUES ('17', 'fg', '123', null, null, '赵警官', '18736512589', '20', '7');
INSERT INTO `user` VALUES ('18', 'la3652145', '123', null, null, '许总', '18456254571', '10', '8');
INSERT INTO `user` VALUES ('19', 'q1', '123', null, null, '张德华', '18565742564', '1', '1');
INSERT INTO `user` VALUES ('20', 'q2', '123', null, null, '王德华', '15245685412', '1', '1');
INSERT INTO `user` VALUES ('21', 'as1', '123', null, null, '许文强', '18756542145', '20', '9');
INSERT INTO `user` VALUES ('22', 'zsd3', '123', null, null, '刘三', '13702365123', '20', '10');
INSERT INTO `user` VALUES ('23', 'sdf', '123', null, null, '赵三', '13735403521', '10', '11');
INSERT INTO `user` VALUES ('25', 'z2', '123', null, null, '李丽', '13735620324', '10', '13');
INSERT INTO `user` VALUES ('26', 't1', '123456', null, null, 'haha', '16457342510', '1', '1');
