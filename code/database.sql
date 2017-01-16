/*
SQLyog Community v8.82 
MySQL - 5.1.41 : Database - clientmonitoring
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`clientmonitoring` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `clientmonitoring`;

/*Table structure for table `command` */

DROP TABLE IF EXISTS `command`;

CREATE TABLE `command` (
  `commandId` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`commandId`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `command` */

/*Table structure for table `host` */

DROP TABLE IF EXISTS `host`;

CREATE TABLE `host` (
  `hostId` int(11) NOT NULL AUTO_INCREMENT,
  `hardWareInfo` varchar(255) DEFAULT NULL,
  `hostName` varchar(255) DEFAULT NULL,
  `osinfo` varchar(255) DEFAULT NULL,
  `systemInfo` varchar(255) DEFAULT NULL,
  `user_userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`hostId`),
  KEY `FK_576a33cc382b4cc3b4a51a3ec00` (`user_userId`),
  KEY `FK_5a3aff0d7bcb459d95f7ac8dd29` (`user_userId`),
  KEY `FK_2e3928e1241341b3bb7f8792d61` (`user_userId`),
  KEY `FK_33439259554b453b88805af2a04` (`user_userId`),
  KEY `FK_b110ca5cc943483aac814b5036a` (`user_userId`),
  KEY `FK_44887405156841ee9c82c635883` (`user_userId`),
  KEY `FK_296f9f6dd07f48c78e716694e8e` (`user_userId`),
  KEY `FK_8286841087df434db34f7cc238b` (`user_userId`),
  KEY `FK_8e7a5cc159584de7b85c6f89781` (`user_userId`),
  KEY `FK_89777064cb154175bfc3e2eaa7c` (`user_userId`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `host` */

insert  into `host`(`hostId`,`hardWareInfo`,`hostName`,`osinfo`,`systemInfo`,`user_userId`) values (11,NULL,'Admin-PC','Windows 7',NULL,11),(12,NULL,'User-PC','Windows 7',NULL,12);

/*Table structure for table `leader` */

DROP TABLE IF EXISTS `leader`;

CREATE TABLE `leader` (
  `leaderId` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`leaderId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `leader` */

insert  into `leader`(`leaderId`,`mobile`,`name`) values (1,'+919539252251','Anjali'),(2,'+919497341123','neethu');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `messageType` int(11) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageId`)
) ENGINE=MyISAM AUTO_INCREMENT=141 DEFAULT CHARSET=latin1;

/*Data for the table `message` */

insert  into `message`(`messageId`,`message`,`messageType`,`mobile`) values (138,'CL SHUTDOWN',0,'+919605892642'),(135,'LD SHOW HAI ANJALI',0,'+919497341123'),(134,'LD SHOW HAI ANJALI',0,'+919497341123'),(122,'LD SHOW HAI',0,'+919539252251'),(124,'LD SHOW HAI',0,'+919539252251'),(128,'MRP:Rs 20.00 Credited Balance Rs 15.80 Final Balance Rs 15.83 Expiry Date 18/07/2016\n\nServ_tax: Rs 2.200 Proc_fee: Rs 3.000 Rechg Cat: TV. Dial *161*2# for Spl ',0,'+919847299997'),(127,'Double  FTT Offers only for U! Rchg with Rs.49 & get Rs.49 TT;OR Rs.75 - Rs75 TT.Normal TT given online. Remaining TT credited in 1.5 hrs.Dial 12111.Rcgh tdy',0,'IL-612345');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `leader_leaderId` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `FK_0b60ce8d8a60487894f936148da` (`leader_leaderId`),
  KEY `FK_4a07a261cb3f4162aae82e4e197` (`leader_leaderId`),
  KEY `FK_6ac6ad810e894035be2fe560756` (`leader_leaderId`),
  KEY `FK_c8188f79b77c48ce8a9c622f81e` (`leader_leaderId`),
  KEY `FK_689f4b38b3604c2bb5d7c326807` (`leader_leaderId`),
  KEY `FK_a957b68c20114954b395274757a` (`leader_leaderId`),
  KEY `FK_be51f5037e3f47448f85d3ce3f6` (`leader_leaderId`),
  KEY `FK_e8cc7b5d01c24c0ba42368c4bd4` (`leader_leaderId`),
  KEY `FK_5875fd042f6149f6b0e7585621c` (`leader_leaderId`),
  KEY `FK_a8db8d5551864c18bfe8b8f27ee` (`leader_leaderId`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`userId`,`email`,`mobile`,`name`,`leader_leaderId`) values (11,'manju@p','9605892642','manju',1),(12,'ammu@gmail.com','9495025713','ammu',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
