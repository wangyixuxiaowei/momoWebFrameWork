# Host: localhost  (Version: 5.6.17)
# Date: 2016-06-17 14:16:14
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

INSERT INTO `sm_module` VALUES (1,1,'首页',0,'/home',1,0,'/home',0,NULL,NULL,NULL,NULL),(11,11,'首页',1,'/home/index',1,0,'/home/index',0,NULL,NULL,NULL,NULL),(100,100,'系统管理',0,'/system',1,0,'/system',0,NULL,NULL,NULL,NULL),(110,110,'权限管理',100,'/system/perms',1,0,'/system/perms',0,NULL,NULL,NULL,NULL),(111,111,'用户管理',110,'/system/perms/user/list',1,0,'/system/perms/user/list',0,NULL,NULL,NULL,NULL),(112,112,'角色管理',110,'/system/perms/role/list',1,0,'/system/perms/role/list',0,NULL,NULL,NULL,NULL),(120,120,'操作日志管理',100,'/system/oplog/list',1,0,'/system/oplog/list',0,NULL,NULL,NULL,NULL);

#
# Structure for table "sm_operation_log"
#

DROP TABLE IF EXISTS `sm_operation_log`;
CREATE TABLE `sm_operation_log` (
  `ID` int(12) NOT NULL AUTO_INCREMENT,
  `OPERATOR` varchar(20) NOT NULL,
  `OPERATION_TYPE` varchar(20) NOT NULL,
  `MODULE` varchar(20) NOT NULL,
  `OPERATION_TIME` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `OPERATOR_IP` varchar(20) NOT NULL,
  `OPERATION_RESULT` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='日志表';

#
# Data for table "sm_operation_log"
#

INSERT INTO `sm_operation_log` VALUES (1,'momo','登录','系统管理','2016-06-02 17:38:07','0:0:0:0:0:0:0:1','成功'),(2,'momo','登录','系统管理','2016-06-02 17:40:16','0:0:0:0:0:0:0:1','成功'),(3,'momo','登录','系统管理','2016-06-02 17:41:58','0:0:0:0:0:0:0:1','成功'),(4,'momo','登录','系统管理','2016-06-02 17:46:38','0:0:0:0:0:0:0:1','成功'),(5,'momo','登录','系统管理','2016-06-02 17:53:27','0:0:0:0:0:0:0:1','成功'),(6,'momo','登录','系统管理','2016-06-02 17:58:16','0:0:0:0:0:0:0:1','成功'),(7,'momo','登录','系统管理','2016-06-02 18:06:45','0:0:0:0:0:0:0:1','成功'),(8,'momo','添加','用户管理','2016-06-02 18:07:49','0:0:0:0:0:0:0:1','成功'),(9,'momo','登录','系统管理','2016-06-03 16:02:32','0:0:0:0:0:0:0:1','成功'),(10,'momo','添加','用户管理','2016-06-03 16:07:02','0:0:0:0:0:0:0:1','成功'),(11,'momo','登出','系统管理','2016-06-03 16:07:13','0:0:0:0:0:0:0:1','成功'),(12,'hellokity','登录','系统管理','2016-06-03 16:07:19','0:0:0:0:0:0:0:1','成功'),(13,'hellokity','登出','系统管理','2016-06-03 16:07:24','0:0:0:0:0:0:0:1','成功'),(14,'momo','登录','系统管理','2016-06-03 16:11:54','0:0:0:0:0:0:0:1','成功'),(15,'momo','添加','角色管理-角色管理','2016-06-03 16:12:17','0:0:0:0:0:0:0:1','成功'),(16,'momo','添加','角色管理','2016-06-03 16:12:23','0:0:0:0:0:0:0:1','成功'),(17,'momo','登出','系统管理','2016-06-03 16:47:49','0:0:0:0:0:0:0:1','成功'),(18,'momo','登录','系统管理','2016-06-03 16:49:24','0:0:0:0:0:0:0:1','成功'),(19,'momo','登录','系统管理','2016-06-03 17:20:05','0:0:0:0:0:0:0:1','成功'),(20,'momo','登录','系统管理','2016-06-03 17:22:10','0:0:0:0:0:0:0:1','成功'),(21,'momo','登录','系统管理','2016-06-13 15:21:45','0:0:0:0:0:0:0:1','成功'),(22,'momo','登出','系统管理','2016-06-13 15:21:58','0:0:0:0:0:0:0:1','成功'),(23,'momo','登录','系统管理','2016-06-13 15:22:04','0:0:0:0:0:0:0:1','成功'),(24,'momo','修改','角色管理-角色管理','2016-06-13 15:22:35','0:0:0:0:0:0:0:1','成功'),(25,'momo','登出','系统管理','2016-06-13 15:23:50','0:0:0:0:0:0:0:1','成功'),(26,'momo','登录','系统管理','2016-06-13 16:33:31','127.0.0.1','成功');

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
) ENGINE=InnoDB AUTO_INCREMENT=324 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

#
# Data for table "sm_permission"
#

INSERT INTO `sm_permission` VALUES (1,'1','view','/home','role'),(2,'1','view','/system','role'),(3,'1','view','/system/perms','role'),(4,'1','delete,view,add,edit','/system/perms/user/list','role'),(5,'1','delete,view,add,edit','/system/perms/role/list','role'),(6,'1','view','/system/oplog/list','role'),(7,'1','view','/home/index','role'),(121,'23','view','/system/oplog/list','role'),(122,'23','view','/system','role'),(123,'24','view','/home/index','role'),(124,'24','view','/home','role'),(125,'24','delete,view,add,edit','/system/perms/role/list','role'),(126,'24','view','/system/perms','role'),(127,'24','view','/system','role'),(128,'24','view,delete,add,edit','/system/perms/user/list','role'),(129,'24','view','/system/oplog/list','role'),(135,'25','view','/system/perms','role'),(136,'99','view','/home/index','role'),(137,'99','view','/home','role'),(156,'27','view','/home/index','role'),(157,'27','view','/home','role'),(160,'37','view','/home/index','role'),(161,'37','view','/home','role'),(162,'37','edit,delete,view,add','/system/perms/role/list','role'),(163,'37','view','/system/perms','role'),(164,'37','view','/system','role'),(165,'38','delete,edit,add,view','/system/perms/user/list','role'),(166,'38','view','/system/perms','role'),(167,'38','view','/system','role'),(168,'38','edit,add,view,delete','/system/perms/role/list','role'),(169,'38','view','/system/oplog','role'),(170,'39','view','/home/index','role'),(171,'39','view','/home','role'),(172,'40','view','/system/oplog','role'),(173,'40','view','/system','role'),(174,'40','delete,add,edit,view','/system/perms/user/list','role'),(175,'40','view','/system/perms','role'),(176,'40','edit,delete,view,add','/system/perms/role/list','role'),(177,'41','view','/system/oplog','role'),(178,'41','view','/system','role'),(179,'41','add,edit,view,delete','/system/perms/role/list','role'),(180,'41','view','/system/perms','role'),(181,'41','add,edit,delete,view','/system/perms/user/list','role'),(182,'42','delete,edit,view,add','/system/perms/user/list','role'),(183,'42','view','/system/perms','role'),(184,'42','view','/system','role'),(185,'42','add,view,delete,edit','/system/perms/role/list','role'),(186,'42','view','/system/oplog','role'),(187,'43','view','/home/index','role'),(188,'43','view','/home','role'),(189,'44','view','/home/index','role'),(190,'44','view','/home','role'),(191,'45','view','/system/oplog','role'),(192,'45','view','/system','role'),(193,'45','view,edit,add,delete','/system/perms/role/list','role'),(194,'45','view','/system/perms','role'),(195,'45','edit,view,delete,add','/system/perms/user/list','role'),(196,'46','edit,add,delete,view','/system/perms/user/list','role'),(197,'46','view','/system/perms','role'),(198,'46','view','/system','role'),(199,'46','add,view,delete,edit','/system/perms/role/list','role'),(200,'46','view','/system/oplog','role'),(201,'47','view','/home/index','role'),(202,'47','view','/home','role'),(203,'48','view','/home/index','role'),(204,'48','view','/home','role'),(205,'49','view','/home/index','role'),(206,'49','view','/home','role'),(207,'50','view','/home/index','role'),(208,'50','view','/home','role'),(209,'51','view','/home/index','role'),(210,'51','view','/home','role'),(211,'52','view','/home/index','role'),(212,'52','view','/home','role'),(213,'53','view','/system/oplog','role'),(214,'53','view','/system','role'),(215,'53','edit,view,delete,add','/system/perms/role/list','role'),(216,'53','view','/system/perms','role'),(217,'53','edit,delete,view,add','/system/perms/user/list','role'),(218,'54','view','/home/index','role'),(219,'54','view','/home','role'),(220,'55','view','/home/index','role'),(221,'55','view','/home','role'),(222,'56','view','/system/oplog','role'),(223,'56','view','/system','role'),(224,'56','view,edit,delete,add','/system/perms/role/list','role'),(225,'56','view','/system/perms','role'),(226,'56','delete,add,edit,view','/system/perms/user/list','role'),(227,'57','view','/home/index','role'),(228,'57','view','/home','role'),(229,'58','view','/home/index','role'),(230,'58','view','/home','role'),(231,'59','view','/home/index','role'),(232,'59','view','/home','role'),(233,'60','delete,add,view,edit','/system/perms/user/list','role'),(234,'60','view','/system/perms','role'),(235,'60','view','/system','role'),(236,'60','edit,delete,view,add','/system/perms/role/list','role'),(237,'60','view','/system/oplog','role'),(238,'61','view','/system/oplog','role'),(239,'61','view','/system','role'),(240,'61','view,add,delete,edit','/system/perms/role/list','role'),(241,'61','view','/system/perms','role'),(242,'61','add,view,edit,delete','/system/perms/user/list','role'),(243,'62','view','/system/oplog','role'),(244,'62','view','/system','role'),(245,'62','edit,add,view,delete','/system/perms/role/list','role'),(246,'62','view','/system/perms','role'),(247,'62','edit,view,add,delete','/system/perms/user/list','role'),(248,'63','view','/home/index','role'),(249,'63','view','/home','role'),(250,'64','view','/home/index','role'),(251,'64','view','/home','role'),(252,'65','view','/home/index','role'),(253,'65','view','/home','role'),(254,'66','view,add,delete,edit','/system/perms/user/list','role'),(255,'66','view','/system/perms','role'),(256,'66','view','/system','role'),(257,'66','edit,add,view,delete','/system/perms/role/list','role'),(258,'66','view','/system/oplog','role'),(259,'67','view','/home/index','role'),(260,'67','view','/home','role'),(261,'68','view','/home/index','role'),(262,'68','view','/home','role'),(263,'69','view','/system/oplog','role'),(264,'69','view','/system','role'),(265,'69','add,delete,edit,view','/system/perms/role/list','role'),(266,'69','view','/system/perms','role'),(267,'69','delete,add,view,edit','/system/perms/user/list','role'),(268,'70','view','/home/index','role'),(269,'70','view','/home','role'),(270,'71','view','/system/oplog','role'),(271,'71','view','/system','role'),(272,'71','view,edit,delete,add','/system/perms/role/list','role'),(273,'71','view','/system/perms','role'),(274,'71','delete,view,edit,add','/system/perms/user/list','role'),(275,'72','view','/system/oplog','role'),(276,'72','view','/system','role'),(277,'72','delete,view,add,edit','/system/perms/user/list','role'),(278,'72','view','/system/perms','role'),(279,'72','edit,add,view,delete','/system/perms/role/list','role'),(280,'73','view','/home/index','role'),(281,'73','view','/home','role'),(282,'74','view','/system/oplog','role'),(283,'74','view','/system','role'),(284,'74','delete,add,view,edit','/system/perms/user/list','role'),(285,'74','view','/system/perms','role'),(286,'74','edit,add,delete,view','/system/perms/role/list','role'),(287,'75','view','/home/index','role'),(288,'75','view','/home','role'),(289,'76','view','/system/oplog','role'),(290,'76','view','/system','role'),(291,'76','add,view,delete,edit','/system/perms/role/list','role'),(292,'76','view','/system/perms','role'),(293,'76','edit,view,delete,add','/system/perms/user/list','role'),(294,'77','view','/home/index','role'),(295,'77','view','/home','role'),(296,'78','view','/home/index','role'),(297,'78','view','/home','role'),(298,'79','view','/system/oplog','role'),(299,'79','view','/system','role'),(300,'79','add,edit,view,delete','/system/perms/user/list','role'),(301,'79','view','/system/perms','role'),(302,'79','edit,view,add,delete','/system/perms/role/list','role'),(303,'80','view','/home/index','role'),(304,'80','view','/home','role'),(305,'81','view','/home/index','role'),(306,'81','view','/home','role'),(307,'82','view','/system/oplog','role'),(308,'82','view','/system','role'),(309,'82','delete,view,add,edit','/system/perms/role/list','role'),(310,'82','view','/system/perms','role'),(311,'82','edit,delete,add,view','/system/perms/user/list','role'),(312,'83','view','/system/oplog','role'),(313,'83','view','/system','role'),(314,'83','add,view,edit,delete','/system/perms/user/list','role'),(315,'83','view','/system/perms','role'),(316,'83','view,edit,delete,add','/system/perms/role/list','role'),(317,'84','view','/home/index','role'),(318,'84','view','/home','role'),(319,'85','delete,edit,view,add','/system/perms/role/list','role'),(320,'85','view','/system/perms','role'),(321,'85','view','/system','role'),(322,'85','delete,view,edit,add','/system/perms/user/list','role'),(323,'85','view','/system/oplog/list','role');

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
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COMMENT='角色表';

#
# Data for table "sm_role"
#

INSERT INTO `sm_role` VALUES (1,'1','admin','管理员角色',11),(2,'2','gome','gome it is.',11),(3,'99','fwe','fwef',11),(21,'99','momomo','momomo',11),(24,'24','admin2','admin2',11),(25,'99','admin3','admin3',11),(27,'27','bbbb','bbbb',11),(37,'37','qwer','qwer',11),(38,'38','a','a',11),(40,'40','c','c',11),(41,'41','d','d',11),(42,'42','e','e',11),(43,'43','f','f',11),(44,'44','g','g',11),(45,'45','h','h',11),(46,'46','j','j',11),(47,'47','k','k',11),(48,'48','l','l',11),(49,'49','z','z',11),(50,'50','x','x',11),(52,'52','v','v',11),(53,'53','b','b',11),(54,'54','n','n',11),(55,'55','m','m',11),(57,'57','a1','a1',11),(58,'58','a2','a2',11),(59,'59','a3','a3',11),(60,'60','a4','a4',11),(61,'61','a5','a5',11),(62,'62','a6','a6',11),(63,'63','a7','a7',11),(64,'64','a8','a9',11),(65,'65','b1','b1',11),(66,'66','b3','b3',11),(67,'67','b4','b4',11),(68,'68','b5','b5',11),(69,'69','b7','b7',11),(70,'70','b8','b8',11),(71,'71','b9','b9',11),(72,'72','b0','b0',11),(73,'73','c1','c1',11),(74,'74','c2','c2',11),(75,'75','c3','c3',11),(76,'76','c4','c4',11),(77,'77','c5','c5',11),(78,'78','c6','c6',11),(79,'79','c7','c7',11),(80,'80','c8','c8',11),(81,'81','c9','c9',11),(82,'82','c0','c0',11),(83,'83','d1','d1',11),(84,'84','d2','d2',11),(85,'85','fewf ','',11);

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "sm_user"
#

INSERT INTO `sm_user` VALUES (1,'momo','11111111','momo','管理员11',1,11,'2016-05-25 10:44:03'),(3,'admin1','11111111','admin1','测试admin1',0,11,'2016-05-16 14:51:43'),(10,'xuxiaowei','11111111','许晓伟','测试',1,11,'2016-05-18 14:14:11'),(11,'admin2','admin2','admin2','admin2/admin2',1,11,'2016-05-18 14:15:02'),(12,'admin3','admin2','admin3','admin3',1,11,'2016-05-18 14:16:15'),(15,'bbbb','11111111','11111111','',1,11,'2016-05-23 17:48:28'),(16,'GOOGa','12341234','GOOGa','12341234',0,11,'2016-05-24 13:29:57'),(17,'qwer','11111111','qwer','',1,11,'2016-05-24 14:11:54'),(18,'momotu','momotu','momotu','momotu',1,11,'2016-06-02 18:07:49'),(19,'hellokity','11111111','hellokity','',1,11,'2016-06-03 16:07:02');

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

INSERT INTO `sm_role_user` VALUES (1,3),(1,10),(24,11),(25,12),(27,15),(37,17),(1,1),(1,18),(1,19);
