/*

SQLyog Ultimate v8.53
MySQL - 5.5.19 : Database - liugetudb

*********************************************************************

*/



/*!40101 SET NAMES utf8 */;



/*!40101 SET SQL_MODE=''*/;



/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`liugetudb` /*!40100 DEFAULT CHARACTER SET utf8 */;



USE `spring`;



/*Table structure for table `moduletb` */



DROP TABLE IF EXISTS `moduletb`;



CREATE TABLE `moduletb` (
  `moduleId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '模块Id',
  `parentId` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父模块Id',
  `moduleName` varchar(20) DEFAULT NULL COMMENT '模块名称',
  `moduleWeight` int(10) unsigned DEFAULT '0' COMMENT '模块权重,列表顺序',
  `moduleUrl` varchar(50) DEFAULT NULL COMMENT '模块对应页面url路径',
  `moduleCreateTime` datetime NOT NULL COMMENT '模块创建时间',
  `moduleLastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '模块最近一次修改时间',
  PRIMARY KEY (`moduleId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;



/*Data for the table `moduletb` */



insert  into `moduletb`(`moduleId`,`parentId`,`moduleName`,`moduleWeight`,`moduleUrl`,`moduleCreateTime`,`moduleLastUpdateTime`) values (1,0,'系统设置管理',0,NULL,'2018-05-10 12:53:39','2018-05-10 13:42:20'),(2,0,'人力资源管理',0,NULL,'2018-05-10 12:53:39','2018-05-10 13:42:13'),(3,1,'用户管理',0,'jsp/UserManage.html','2018-05-10 12:53:39','2018-05-10 13:44:31'),(4,1,'角色管理',0,'jsp/RoleManage.html','2018-05-10 12:53:39','2018-05-10 13:48:45'),(5,1,'模块管理',0,'jsp/ModuleManage.html','2018-05-10 12:53:39','2018-05-10 13:48:46'),(6,1,'权限管理',0,'jsp/PermissionManage.html','2018-05-10 12:53:39','2018-05-10 13:48:48'),(7,2,'部门管理',0,'jsp/DeptmentManage.html','2018-05-10 12:53:39','2018-05-10 13:56:22'),(8,2,'员工管理',0,'jsp/EmployeeManage.html','2018-05-10 12:53:39','2018-05-10 13:56:23'),(9,2,'薪酬管理',0,'jsp/PaymentManage.html','2018-05-10 12:53:39','2018-05-10 13:56:23'),(10,2,'绩效管理',0,'jsp/PerformanceMange.html','2018-05-10 12:53:39','2018-05-10 13:56:25');



/*Table structure for table `permissiontb` */



DROP TABLE IF EXISTS `permissiontb`;



CREATE TABLE `permissiontb` (
  `permissionId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `permissionValue` varchar(50) DEFAULT NULL COMMENT '权限资源对象',
  `permissionModule` varchar(50) NOT NULL COMMENT '权限所属模块',
  `permissionName` varchar(50) NOT NULL COMMENT '权限名称',
  `permissionLastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '权限最近一次修改时间',
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



/*Data for the table `permissiontb` */



insert  into `permissiontb`(`permissionId`,`permissionValue`,`permissionModule`,`permissionName`,`permissionLastUpdateTime`) values (1,'user_name:insert','用户模块','用户新增','2018-05-11 13:07:00'),(2,'user_name:delete','用户模块','用户删除','2018-05-11 13:07:03'),(3,'user_name:update','用户模块','用户修改','2018-05-11 13:07:07');



/*Table structure for table `rolemoduletb` */



DROP TABLE IF EXISTS `rolemoduletb`;



CREATE TABLE `rolemoduletb` (
  `roleModuleId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色模块关系记录ID',
  `roleId` int(10) unsigned NOT NULL COMMENT '所属角色Id',
  `moduleId` int(10) unsigned NOT NULL COMMENT '所属模块Id',
  `roleModuleLastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '角色模块关系记录最近一次修改时间',
  PRIMARY KEY (`roleModuleId`),
  KEY `FK_rolemoduletb_roleId` (`roleId`),
  KEY `FK_rolemoduletb_moduleId` (`moduleId`),
  CONSTRAINT `FK_rolemoduletb_moduleId` FOREIGN KEY (`moduleId`) REFERENCES `moduletb` (`moduleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rolemoduletb_roleId` FOREIGN KEY (`roleId`) REFERENCES `roletb` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



/*Data for the table `rolemoduletb` */



insert  into `rolemoduletb`(`roleModuleId`,`roleId`,`moduleId`,`roleModuleLastUpdateTime`) values (1,1,1,'2018-05-10 13:59:53'),(2,1,3,'2018-05-10 13:59:58'),(3,1,4,'2018-05-10 14:00:02');



/*Table structure for table `rolepermissiontb` */



DROP TABLE IF EXISTS `rolepermissiontb`;



CREATE TABLE `rolepermissiontb` (
  `rolePermissionId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色权限关系ID',
  `roleId` int(10) unsigned NOT NULL COMMENT '所属角色Id',
  `permissionId` int(10) unsigned NOT NULL COMMENT '所属权限Id',
  `rolePermissionLastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '角色权限关系记录最近一次修改时间',
  PRIMARY KEY (`rolePermissionId`),
  KEY `FK_rolepermissiontb_roleId` (`roleId`),
  KEY `FK_rolepermissiontb_permissionId` (`permissionId`),
  CONSTRAINT `FK_rolepermissiontb_permissionId` FOREIGN KEY (`permissionId`) REFERENCES `permissiontb` (`permissionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rolepermissiontb_roleId` FOREIGN KEY (`roleId`) REFERENCES `roletb` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



/*Data for the table `rolepermissiontb` */



insert  into `rolepermissiontb`(`rolePermissionId`,`roleId`,`permissionId`,`rolePermissionLastUpdateTime`) values (1,1,1,'2018-05-10 14:03:05'),(2,1,2,'2018-05-18 23:54:41');



/*Table structure for table `roletb` */



DROP TABLE IF EXISTS `roletb`;



CREATE TABLE `roletb` (
  `roleId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `roleName` varchar(20) NOT NULL COMMENT '角色名称',
  `roleExplain` varchar(100) DEFAULT NULL COMMENT '角色说明',
  `roleCreateTime` datetime DEFAULT NULL COMMENT '角色创建时间',
  `roleLastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '角色最近修改时间',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;



/*Data for the table `roletb` */



insert  into `roletb`(`roleId`,`roleName`,`roleExplain`,`roleCreateTime`,`roleLastUpdateTime`) values (1,'超级管理员','拥有所有权限','2018-05-10 12:53:39','2018-05-10 13:33:07'),(2,'人事主管','拥有人事部的主管权限','2018-05-10 12:53:39','2018-05-10 13:32:54'),(3,'财务主管','拥有财务部的主管权限','2018-05-10 12:53:39','2018-05-10 13:32:44'),(4,'市场主管','拥有咨询部的主管权限','2018-05-10 12:53:39','2018-05-10 13:34:49');



/*Table structure for table `userroletb` */



DROP TABLE IF EXISTS `userroletb`;



CREATE TABLE `userroletb` (
  `userRoleId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色关系记录ID',
  `userId` int(10) unsigned NOT NULL COMMENT '所属用户Id',
  `roleId` int(10) unsigned NOT NULL COMMENT '所属角色Id',
  `userRoleLastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户角色关系记录最近修改时间',
  PRIMARY KEY (`userRoleId`),
  KEY `FK_userroletb_userId` (`userId`),
  KEY `FK_userroletb_roleId` (`roleId`),
  CONSTRAINT `FK_userroletb_roleId` FOREIGN KEY (`roleId`) REFERENCES `roletb` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_userroletb_userId` FOREIGN KEY (`userId`) REFERENCES `usertb` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



/*Data for the table `userroletb` */



insert  into `userroletb`(`userRoleId`,`userId`,`roleId`,`userRoleLastUpdateTime`) values (1,1,1,'2018-05-10 13:35:14'),(2,2,1,'2018-05-18 23:54:09');



/*Table structure for table `usertb` */



DROP TABLE IF EXISTS `usertb`;



CREATE TABLE `usertb` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `userName` varchar(20) NOT NULL COMMENT '用户登录名',
  `userPassWord` varchar(100) NOT NULL COMMENT '用户密码',
  `userIsLockout` tinyint(1) DEFAULT '0' COMMENT '用户是否锁定:默认不锁定',
  `userUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户信息最后一次被修改时间:数据库自己维护记录的修改时间',
  `userCreateTime` datetime DEFAULT NULL COMMENT '用户账号创建时间',
  `userLastLoginTime` datetime NOT NULL COMMENT '用户上一次登录时间',
  `userLastLoginIp` varchar(20) DEFAULT NULL COMMENT '用户上一次登录实际IP地址',
  `userPassWrongCout` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '用户输入密码错误次数',
  `userLockoutTime` datetime DEFAULT NULL COMMENT '用户输入密码错误次数达到上限锁定时间',
  `userEmail` varchar(50) DEFAULT NULL COMMENT '用户密保邮箱',
  `userTelephone` varchar(20) DEFAULT NULL COMMENT '用户密保电话',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



/*Data for the table `usertb` */



insert  into `usertb`(`userId`,`userName`,`userPassWord`,`userIsLockout`,`userUpdateTime`,`userCreateTime`,`userLastLoginTime`,`userLastLoginIp`,`userPassWrongCout`,`userLockoutTime`,`userEmail`,`userTelephone`) values (1,'马帅','f29ff7d029b838f76beea5cb85b9c2f5',0,'2018-05-18 22:55:44','2018-05-10 12:53:39','2018-05-10 12:53:39','192.168.0.1',0,'2018-05-10 12:53:39','1119616605@qq.com','18336328557'),(2,'tom','229d0f52f7a0596eeece160908e54ad9',0,'2018-05-18 23:00:35','2018-05-10 12:53:39','2018-05-10 12:53:39',NULL,0,'2018-05-10 12:53:39','1119616605@qq.com','18336328557');



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

create trigger updateUserType before update on usertb for each row
  begin
    declare user_wrong int;
    select new.userPassWrongCout into user_wrong for update ;
    if user_wrong >= 5 then
      set new.userIsLockout = 1;
    end if ;
  end;

drop trigger updateUserType;

