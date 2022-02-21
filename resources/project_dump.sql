CREATE DATABASE  IF NOT EXISTS `rpg_project` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rpg_project`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: junia_lab03
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2019-09-16 10:00:38', NULL, 'user1', 'password'),
                          (2,'2019-09-16 10:00:38', NULL, 'user2', 'password'),
                          (3,'2019-09-16 10:00:38', NULL, 'user3', 'password'),
                          (4,'2019-09-16 10:00:38', NULL, 'user4', 'password'),
                          (5,'2019-09-16 10:00:39', NULL, 'user5', 'password');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `party`
--

DROP TABLE IF EXISTS `party`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `party` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sessionNumber` int(11) DEFAULT 0,
  `PC1` varchar(255) NOT NULL,
  `PC2` varchar(255) DEFAULT NULL,
  `PC3` varchar(255) DEFAULT NULL,
  `PC4` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `gmUser_id` bigint(20) NOT NULL,
  FOREIGN KEY (`gmUser_id`) REFERENCES `user` (`id`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `party`
--

LOCK TABLES `party` WRITE;
/*!40000 ALTER TABLE `party` DISABLE KEYS */;
INSERT INTO `party` VALUES (1,'2019-09-16 10:00:38', NULL, 'Les Fers de Hache', 2, 'user2', 'user4', 'user1', 'user3', 'ceci sont des notes', 2);
/*!40000 ALTER TABLE `party` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characterSheet`
--

DROP TABLE IF EXISTS `characterSheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `characterSheet` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `name` varchar(65) NOT NULL DEFAULT '',
  `gender` varchar(1) NOT NULL DEFAULT 'U',
  `race` varchar(65) NOT NULL DEFAULT 'undefined',
  `will_stat` tinyint(3) NOT NULL DEFAULT 0,
  `strength_stat` tinyint(3) NOT NULL DEFAULT 0,
  `constitution_stat` tinyint(3) NOT NULL DEFAULT 0,
  `dexterity_stat` tinyint(3) NOT NULL DEFAULT 0,
  `intelligence_stat` tinyint(3) NOT NULL DEFAULT 0,
  `wisdom_stat` tinyint(3) NOT NULL DEFAULT 0,
  `charisma_stat` tinyint(3) NOT NULL DEFAULT 0,
  `level` tinyint(3) NOT NULL DEFAULT 0,
  `money` int(9) NOT NULL DEFAULT 0,
  `notes` varchar(3000) NOT NULL DEFAULT '',
  `user_id` bigint(20) NOT NULL,
  `party_id` bigint(20) DEFAULT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`party_id`) REFERENCES `party` (`id`),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characterSheet`
--

LOCK TABLES `characterSheet` WRITE;
/*!40000 ALTER TABLE `characterSheet` DISABLE KEYS */;
INSERT INTO `characterSheet`
    (id, creationDate, gender, race, name, level, user_id, party_id)
    VALUES (1, '2019-09-16 10:00:39', 'F', 'Human', 'Camille', 6, 2, 1),
        (2, '2019-09-16 10:00:39', 'U', 'Human', 'Francis', 6, 4, 1),
        (3, '2019-09-16 10:00:39', 'M', 'Orc', 'Xorox', 6, 1, 1),
        (4, '2019-09-16 10:00:39', 'M', 'Dwarf', 'Philip', 7, 3, 1);
/*!40000 ALTER TABLE `characterSheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (35),(35),(35),(35);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
