/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 8.0.12 : Database - boss
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`boss` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `boss`;

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` char(50) NOT NULL,
  `url` varchar(64) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`title`,`url`,`status`,`create_time`,`update_time`) values 
(1,'setRole','/admin/setRole',1,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(2,'test','/user/test',1,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(3,'dev','/user/dev',1,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(4,'master','/user/master',1,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(5,'removeRole','/admin/removeRole',1,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(6,'addPermission','/admin/addPermission',1,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(7,'deletePermission','/admin/deletePermission',1,'0000-00-00 00:00:00','0000-00-00 00:00:00');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` char(32) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`status`,`create_time`,`update_time`) values 
(1,'ADMIN',1,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(2,'USER',1,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(3,'TEST',1,'0000-00-00 00:00:00','0000-00-00 00:00:00');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`role_id`,`permission_id`,`id`,`create_time`,`update_time`) values 
(1,1,1,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(1,2,2,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(2,3,3,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(3,4,4,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(1,5,5,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(1,6,6,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(1,7,8,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(2,4,9,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(1,3,10,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(1,4,11,'0000-00-00 00:00:00','0000-00-00 00:00:00');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(256) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`password`,`status`,`create_time`,`update_time`) values 
(1,'jbb','$2a$10$mU8ZkfZtHC7oUedzdtl5.ORtSrQeg8xuuhk.3ZS54zFEYiLGBljQi',1,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(2,'b','$2a$10$mU8ZkfZtHC7oUedzdtl5.ORtSrQeg8xuuhk.3ZS54zFEYiLGBljQi',1,'0000-00-00 00:00:00','0000-00-00 00:00:00');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`uid`,`role_id`,`create_time`,`update_time`) values 
(1,1,2,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(2,1,1,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(6,2,2,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(7,1,3,'0000-00-00 00:00:00','0000-00-00 00:00:00'),
(8,2,3,'0000-00-00 00:00:00','0000-00-00 00:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
