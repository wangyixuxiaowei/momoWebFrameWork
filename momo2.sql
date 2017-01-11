# Host: localhost  (Version: 5.6.17)
# Date: 2016-05-20 10:05:22
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "sm_module"
#

DROP TABLE IF EXISTS `sm_module`;
CREATE TABLE `sm_module` (
  `CID` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ID` int(8) NOT NULL DEFAULT '0' COMMENT '功能ID',
  `NAME` varchar(30) NOT NULL DEFAULT '新模块' COMMENT '模块名称',
  `PID` int(8) NOT NULL DEFAULT '0' COMMENT '父模块ID',
  `LINKURL` varchar(100) NOT NULL DEFAULT '/newurl' COMMENT '模块的资源地址',
  `ISENABLE` int(1) NOT NULL DEFAULT '0' COMMENT '模块是否可用',
  `ORDERID` int(8) NOT NULL DEFAULT '0' COMMENT '排序ID',
  `CODE` varchar(100) DEFAULT NULL COMMENT '与权限关联的字面量',
  `ISDEFAULT` int(2) DEFAULT NULL COMMENT '默认展示',
  `TOPCLASS` varchar(30) DEFAULT NULL,
  `MENUCLASS` varchar(30) DEFAULT NULL,
  `TARGET` varchar(30) DEFAULT NULL,
  `MEMO` varchar(255) DEFAULT NULL COMMENT '描述说明',
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COMMENT='功能模块表';

#
# Data for table "sm_module"
#

INSERT INTO `sm_module` VALUES (1,1,'首页',0,'/home',1,0,'/home',0,NULL,NULL,NULL,NULL),(11,11,'首页',1,'/home/index',1,0,'/home/index',0,NULL,NULL,NULL,NULL),(100,100,'系统管理',0,'/system',1,0,'/system',0,NULL,NULL,NULL,NULL),(110,110,'权限管理',100,'/system/perms',1,0,'/system/perms',0,NULL,NULL,NULL,NULL),(111,111,'用户管理',110,'/system/perms/user/list',1,0,'/system/perms/user/list',0,NULL,NULL,NULL,NULL),(112,112,'角色管理',110,'/system/perms/role/list',1,0,'/system/perms/role/list',0,NULL,NULL,NULL,NULL),(120,120,'日志管理',100,'/system/oplog',1,0,'/system/oplog',0,NULL,NULL,NULL,NULL);

#
# Structure for table "sm_permission"
#

DROP TABLE IF EXISTS `sm_permission`;
CREATE TABLE `sm_permission` (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `RECIPIENT_ID` varchar(100) DEFAULT NULL,
  `ACTION` varchar(200) DEFAULT NULL,
  `TARGET` varchar(100) DEFAULT NULL,
  `DISCRIMINATOR` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

#
# Data for table "sm_permission"
#

INSERT INTO `sm_permission` VALUES (1,'1','view','/home','role'),(2,'1','view','/system','role'),(3,'1','view','/system/perms','role'),(4,'1','view','/system/perms/user/list','role'),(5,'1','view','/system/perms/role/list','role'),(6,'1','view','/system/oplog','role'),(7,'1','view','/home/index','role'),(121,'23','view','/system/oplog','role'),(122,'23','view','/system','role'),(123,'24','view','/home/index','role'),(124,'24','view','/home','role'),(125,'24','delete,view,add,edit','/system/perms/role/list','role'),(126,'24','view','/system/perms','role'),(127,'24','view','/system','role'),(128,'24','view,delete,add,edit','/system/perms/user/list','role'),(129,'24','view','/system/oplog','role'),(130,'25','view','/home/index','role'),(131,'25','view','/home','role'),(132,'25','view','/system/oplog','role'),(133,'25','view','/system','role'),(134,'25','edit,add,delete,view','/system/perms/user/list','role'),(135,'25','view','/system/perms','role');

#
# Structure for table "sm_permvalue"
#

DROP TABLE IF EXISTS `sm_permvalue`;
CREATE TABLE `sm_permvalue` (
  `PE_ID` int(8) DEFAULT NULL,
  `MODULE_ID` int(8) DEFAULT NULL,
  `PE_NAME` varchar(100) DEFAULT NULL,
  `PE_CODE` varchar(100) DEFAULT NULL,
  KEY `FK_PERMVALUE_MODULE` (`MODULE_ID`),
  CONSTRAINT `FK_PERMVALUE_MODULE` FOREIGN KEY (`MODULE_ID`) REFERENCES `sm_module` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块动作表';

#
# Data for table "sm_permvalue"
#

INSERT INTO `sm_permvalue` VALUES (1,1,'查看','view'),(2,11,'查看','view'),(3,100,'查看','view'),(4,110,'查看','view'),(5,111,'查看','view'),(6,111,'添加','add'),(7,111,'修改','edit'),(8,111,'删除','delete'),(9,112,'查看','view'),(10,112,'添加','add'),(11,112,'修改','edit'),(12,112,'删除','delete'),(13,120,'查看','view');

#
# Structure for table "sm_role"
#

DROP TABLE IF EXISTS `sm_role`;
CREATE TABLE `sm_role` (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(50) DEFAULT '1',
  `NAME` varchar(100) DEFAULT NULL,
  `MEMO` varchar(100) DEFAULT NULL,
  `ROLETYPE` int(2) DEFAULT '11',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='角色表';

#
# Data for table "sm_role"
#

INSERT INTO `sm_role` VALUES (1,'1','admin','管理员角色',11),(2,'2','gome','gome it is.',11),(3,'99','fwe','fwef',11),(21,'99','momomo','momomo',11),(24,'24','admin2','admin2',11),(25,'25','admin3','admin3',11);

#
# Structure for table "sm_user"
#

DROP TABLE IF EXISTS `sm_user`;
CREATE TABLE `sm_user` (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `MEMO` varchar(200) DEFAULT NULL,
  `ENABLE` int(1) DEFAULT '1',
  `USERTYPE` int(2) DEFAULT '11',
  `PWD_UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "sm_user"
#

INSERT INTO `sm_user` VALUES (1,'momo','11111111','momo','管理员',1,11,'2016-05-13 14:20:21'),(3,'admin1','11111111','admin1','测试admin1',0,11,'2016-05-16 14:51:43'),(10,'xuxiaowei','11111111','许晓伟','测试',1,11,'2016-05-18 14:14:11'),(11,'admin2','admin2','admin2','admin2/admin2',1,11,'2016-05-18 14:15:02'),(12,'admin3','admin2','admin3','admin3',1,11,'2016-05-18 14:16:15');

#
# Structure for table "sm_role_user"
#

DROP TABLE IF EXISTS `sm_role_user`;
CREATE TABLE `sm_role_user` (
  `ROLE_ID` int(8) DEFAULT NULL,
  `USER_ID` int(8) DEFAULT NULL,
  KEY `FK_SM_ROLE_USER_ROLE` (`ROLE_ID`),
  KEY `FK_SM_ROLE_USER_USER` (`USER_ID`),
  CONSTRAINT `FK_SM_ROLE_USER_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `sm_role` (`ID`),
  CONSTRAINT `FK_SM_ROLE_USER_USER` FOREIGN KEY (`USER_ID`) REFERENCES `sm_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色用户关系表';

#
# Data for table "sm_role_user"
#

INSERT INTO `sm_role_user` VALUES (1,1),(1,3),(1,10),(24,11),(25,12);
