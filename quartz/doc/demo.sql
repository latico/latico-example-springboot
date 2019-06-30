-- 创建第一个数据库和表
CREATE DATABASE`test`  DEFAULT CHARACTER SET utf8 ;
USE `test`;
CREATE TABLE `demo` (
  `id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(128) NOT NULL,
  `administrator` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建第一个数据库和表
CREATE DATABASE`test2`  DEFAULT CHARACTER SET utf8 ;
USE `test2`;
CREATE TABLE `demo2` (
  `id` INT(11) NOT NULL,
  `username` VARCHAR(32) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `administrator` TINYINT(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- 入第一个库测试数据
USE `test`;
insert  into `demo`(`id`,`username`,`password`,`administrator`) values
(1,'name1','pass1',1),(2,'name2','pass2',0),(3,'名字3','密码3',1),
(4,'name4','pass4',1),(5,'name5','pass5',0),(6,'名字6','密码6',1),
(7,'name7','pass7',1),(8,'name8','pass8',0),(9,'名字9','密码9',1),
(10,'name10','pass10',1)
;

-- 入第二个库测试数据
USE `test2`;
insert  into `demo2`(`id`,`username`,`password`,`administrator`) values
(1,'name1','pass1',1),(2,'name2','pass2',0),(3,'名字3','密码3',1),
(4,'name4','pass4',1),(5,'name5','pass5',0),(6,'名字6','密码6',1),
(7,'name7','pass7',1),(8,'name8','pass8',0),(9,'名字9','密码9',1),
(10,'name10','pass10',1)
;



