-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: Tieba
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `Tieba`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `Tieba` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `Tieba`;

--
-- Table structure for table `bars`
--

DROP TABLE IF EXISTS `bars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bars` (
  `BarID` int(11) NOT NULL AUTO_INCREMENT,
  `BarName` varchar(12) NOT NULL,
  `BarContent` varchar(300) DEFAULT NULL,
  `Visible` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`BarID`),
  UNIQUE KEY `Bars_BarName_uindex` (`BarName`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bars`
--

LOCK TABLES `bars` WRITE;
/*!40000 ALTER TABLE `bars` DISABLE KEYS */;
INSERT INTO `bars` VALUES (1,'HTML','HTML吧欢迎你的到来',1),(2,'Java','Java吧欢迎你的到来',1),(3,'JavaScript','JavaScript吧欢迎你的到来',1),(4,'ECMAScript6','1996年11月，JavaScript的创造者Netscape公司，决定将JavaScript提交给标准化组织ECMA，希望这种语言能够成为国际标准。<br>次年，ECMA发布262号标准文件(ECMA-262)的第一版，规定了浏览器脚本语言的标准，并将这种语言称为ECMAScript。',1),(5,'CSS3','CSS3吧欢迎你的到来',1),(6,'我也不知道这个该叫什么','管理员创建这个吧的时候偷懒了，简介什么也没有写',1);
/*!40000 ALTER TABLE `bars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiereply`
--

DROP TABLE IF EXISTS `tiereply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiereply` (
  `TieID` int(11) NOT NULL,
  `Floor` int(11) NOT NULL,
  `Reply` varchar(1024) DEFAULT NULL,
  `ReUser` varchar(12) NOT NULL,
  `ReTime` datetime DEFAULT NULL,
  `Visible` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`TieID`,`Floor`),
  CONSTRAINT `tiereply_ties_TieID_fk` FOREIGN KEY (`TieID`) REFERENCES `ties` (`TieID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiereply`
--

LOCK TABLES `tiereply` WRITE;
/*!40000 ALTER TABLE `tiereply` DISABLE KEYS */;
INSERT INTO `tiereply` VALUES (1,1,'有哪些呢?','Ilirus','2018-07-23 20:12:10',1),(1,2,'const','Ilirus','2018-07-24 22:32:08',1),(1,3,'箭头函数','Ilirus','2018-07-24 22:33:56',1),(2,1,'Title:null<br>Content:undefined','Ilirus','2018-07-24 22:41:35',1),(2,2,'!@#$%^&*()_=+-*/,.?','Ilirus','2018-07-24 22:42:11',1),(3,1,'测试<br><br><br>测试','Ilirus','2018-07-24 18:29:26',1),(4,1,'<p>p1</p><p>2p</p><p>结束</p>','Ilirus','2018-07-24 18:30:15',1);
/*!40000 ALTER TABLE `tiereply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ties`
--

DROP TABLE IF EXISTS `ties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ties` (
  `TieID` int(11) NOT NULL AUTO_INCREMENT,
  `BarID` int(11) NOT NULL,
  `TieTitle` varchar(24) NOT NULL,
  `TieMain` varchar(1024) DEFAULT NULL,
  `TieUser` varchar(12) NOT NULL,
  `PostTime` datetime NOT NULL,
  `UpdateTime` datetime NOT NULL,
  `Visible` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`TieID`),
  KEY `ties_bars_BarID_fk` (`BarID`),
  CONSTRAINT `ties_bars_BarID_fk` FOREIGN KEY (`BarID`) REFERENCES `bars` (`BarID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ties`
--

LOCK TABLES `ties` WRITE;
/*!40000 ALTER TABLE `ties` DISABLE KEYS */;
INSERT INTO `ties` VALUES (1,4,'ES6中的新特性','有哪些呢?','Ilirus','2018-07-23 20:12:10','2018-07-24 22:33:56',1),(2,4,'排版测试1','Title:null<br>Content:undefined','Ilirus','2018-07-24 18:27:15','2018-07-24 22:42:11',1),(3,4,'排版测试2','测试<br><br><br>测试','Ilirus','2018-07-24 18:29:26','2018-07-24 18:29:26',1),(4,4,'排版测试3','<p>p1</p><p>2p</p><p>结束</p>','Ilirus','2018-07-24 18:30:15','2018-07-24 18:30:15',1);
/*!40000 ALTER TABLE `ties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `Account` varchar(18) NOT NULL,
  `Username` varchar(12) NOT NULL,
  `Password` varchar(16) NOT NULL,
  `Adminlv` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UID`),
  UNIQUE KEY `User_Account_uindex` (`Account`),
  UNIQUE KEY `User_Username_uindex` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'wan1483114030','Ilirus','147158',3),(2,'wan147158','empty','147158',2),(3,'wan123456','admin','147158',2),(4,'wan450','null','147158',1),(5,'wan233','undefine','147158',1),(6,'wan12450','丨放肆丨','123456',0),(7,'wan123','Nick','123456',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-11 21:13:38
