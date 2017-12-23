CREATE DATABASE  IF NOT EXISTS `javaexample` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `javaexample`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: javaexample
-- ------------------------------------------------------
-- Server version	5.6.37-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `admin` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin','123456');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `actors` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `director` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `thumb` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `showDate` date DEFAULT NULL,
  `time` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `country` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `detail` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'追龙',' 甄子丹 / 刘德华 / 姜皓文 / 郑则仕 / 刘浩龙','王晶 / 关智耀','E:\\eclipse-workspace\\Example5\\images\\thumb.jpg','2017-09-13','128','动作 / 犯罪','香港 / 中国大陆','电影讲述了香港现代史上两大著名狠角色大毒枭跛豪（甄子丹饰）、五亿探长雷洛（刘德华饰）的传奇故事。上世纪六七十年代，香港由英国殖民，权势腐败、社会混乱。1963年，穷困潦倒的青年阿豪（甄子丹饰）偷渡至香港，抱着“生死有命、富贵在天”之心决意一搏人生。阿豪带着几个兄弟，从九龙城寨底层开始一路刀刃舔血，爬上香港毒品霸主之位，一手掌控香港十大黑帮，江湖人称“跛豪”。而雷洛（刘德华饰）则从一位普通探长一步步爬上华人总探长高位，统领全香港三万警察，手握香港治安“潜规则”。为垄断香港黄赌毒三大经济产业，跛豪与雷洛结拜为兄弟，两人一黑一白两手遮天，权势滔天，家财亿万，独霸香港岛……'),(2,'美国队长2','安东尼·罗素 / 乔·罗素','克里斯·埃文斯 / 斯嘉丽·约翰逊 / 塞巴斯蒂安·斯坦 / 安东尼·麦凯 / 海莉·阿特维尔','E:\\eclipse-workspace\\Example5\\images\\thumb1.jpg','2014-04-04','136','动作 / 科幻 / 冒险','美国','渐渐习惯了现代生活的美国队长史蒂夫·罗杰斯（克里斯·埃文斯 Chris Evans 饰），在一次行动后隐隐嗅到神盾局内部所弥漫出来的凶险气味。\n而当得知神盾局正秘密进行的“洞察计划”后，他更为此感到愤怒。某天，神盾局指挥官尼克-法瑞（塞缪尔·杰克逊 Samuel L. Jackson 饰）遭到一群武装分子袭击，他拼尽全力将一支U盘交到美国队长手中，这里面藏着该局二战以来最重要且最可怕的秘密。在此之后，美国队长因涉嫌杀害尼克而遭到前局长亚历山大·皮尔斯（罗伯特·雷德福/Robert Redford 饰）的通缉和追杀，逃亡中他得到黑寡妇（斯嘉丽·约翰逊 Scarlett Johansson 饰）和退伍老兵“猎鹰”（安东尼·麦凯 Anthony Mackie 饰）的帮助。 \n　　他们必须争分夺秒解开神盾局的秘密，阻止“洞察计划”的施行……');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-23 22:25:16
