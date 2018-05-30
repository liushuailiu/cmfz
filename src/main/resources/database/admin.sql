CREATE TABLE `permissiontb` (
  `permissionId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `permissionValue` varchar(50) DEFAULT NULL COMMENT '权限资源对象',
  `permissionModule` varchar(50) NOT NULL COMMENT '权限所属模块',
  `permissionName` varchar(50) NOT NULL COMMENT '权限名称',
  `permissionLastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '权限最近一次修改时间',
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;