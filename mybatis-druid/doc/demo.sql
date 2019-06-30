CREATE TABLE `demo` (
  `id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(128) NOT NULL,
  `administrator` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `demo2` (
  `id` INT(11) NOT NULL,
  `username` VARCHAR(32) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `administrator` TINYINT(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



insert  into `demo`(`id`,`username`,`password`,`administrator`) values (1,'name1','pass1',1),(2,'name2','pass2',0),(3,'名字3','密码3',1);

INSERT  INTO `demo2`(`id`,`username`,`password`,`administrator`) VALUES (1,'name1','pass1',1),(2,'name2','pass2',0),(3,'名字3','密码3',1);
