-- MySQL dump 10.16  Distrib 10.1.19-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: localhost
-- ------------------------------------------------------
-- Server version	10.1.19-MariaDB

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `experience_id` int(11) NOT NULL,
  `user_id` int(7) NOT NULL,
  `content` varchar(3000) NOT NULL,
  `time_stamp` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,1,'I\'m a comment',NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drug`
--

DROP TABLE IF EXISTS `drug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drug` (
  `drug_id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`drug_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drug`
--

LOCK TABLES `drug` WRITE;
/*!40000 ALTER TABLE `drug` DISABLE KEYS */;
INSERT INTO `drug` VALUES (1,'Alcohol','https://www.erowid.org/chemicals/alcohol/alcohol.shtml'),(2,'Alprazolam','https://www.erowid.org/pharms/alprazolam/alprazolam.shtml'),(3,'Ayahuasca','https://www.erowid.org/chemicals/ayahuasca/ayahuasca.shtml'),(4,'Ayahuasca','https://www.erowid.org/chemicals/ayahuasca/ayahuasca.shtml'),(5,'Caffeine','https://www.erowid.org/chemicals/caffeine/caffeine.shtml'),(6,'Cacti','https://www.erowid.org/plants/cacti/cacti.shtml'),(7,'Cannabis','https://www.erowid.org/plants/cannabis/cannabis.shtml'),(8,'Cocaine','https://www.erowid.org/chemicals/cocaine/cocaine.shtml'),(9,'DMT','https://www.erowid.org/chemicals/dmt/dmt.shtml'),(10,'DXM','https://www.erowid.org/chemicals/dxm/dxm.shtml'),(11,'GHB','https://www.erowid.org/chemicals/ghb/ghb.shtml'),(12,'Ketamine','https://www.erowid.org/chemicals/ketamine/ketamine.shtml'),(13,'Kratom','https://www.erowid.org/plants/kratom/kratom.shtml'),(14,'LSD','https://www.erowid.org/chemicals/lsd/lsd.shtml'),(15,'MDMA','https://www.erowid.org/chemicals/mdma/mdma.shtml'),(16,'MDPV','https://www.erowid.org/chemicals/mdpv/mdpv.shtml'),(17,'Meth','https://www.erowid.org/chemicals/meth/meth.shtml'),(18,'MXE','https://www.erowid.org/chemicals/methoxetamine/methoxetamine.shtml'),(19,'Mushrooms','https://www.erowid.org/plants/mushrooms/mushrooms.shtml'),(20,'Peyote','https://www.erowid.org/plants/peyote/peyote.shtml'),(21,'Salvia','https://www.erowid.org/plants/salvia/salvia.shtml'),(22,'Zolpidem','https://www.erowid.org/pharms/zolpidem/zolpidem.shtml'),(23,'25I-NBOMe','https://www.erowid.org/chemicals/2ci_nbome/2ci_nbome.shtml'),(24,'2C-B','https://www.erowid.org/chemicals/2cb/2cb.shtml');
/*!40000 ALTER TABLE `drug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drug_data`
--

DROP TABLE IF EXISTS `drug_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drug_data` (
  `drug_id` int(7) NOT NULL,
  `count` int(7) DEFAULT NULL,
  PRIMARY KEY (`drug_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drug_data`
--

LOCK TABLES `drug_data` WRITE;
/*!40000 ALTER TABLE `drug_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `drug_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encryption_key`
--

DROP TABLE IF EXISTS `encryption_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encryption_key` (
  `user_id` int(7) NOT NULL,
  `user_key` varchar(65) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encryption_key`
--

LOCK TABLES `encryption_key` WRITE;
/*!40000 ALTER TABLE `encryption_key` DISABLE KEYS */;
/*!40000 ALTER TABLE `encryption_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experience`
--

DROP TABLE IF EXISTS `experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `experience` (
  `experience_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(7) NOT NULL,
  `drug1` int(7) NOT NULL,
  `drug2` int(7) DEFAULT NULL,
  `title` varchar(45) NOT NULL,
  `user_rating` int(1) DEFAULT NULL,
  `others_rating` int(1) DEFAULT NULL,
  `time_stamp` varchar(25) DEFAULT NULL,
  `content` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`experience_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `experience_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experience`
--

LOCK TABLES `experience` WRITE;
/*!40000 ALTER TABLE `experience` DISABLE KEYS */;
INSERT INTO `experience` VALUES (1,1,1,1,'Jeff\'s story',NULL,NULL,NULL,NULL),(2,2,1,NULL,'Beware',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `experience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `feedback_id` int(7) NOT NULL AUTO_INCREMENT,
  `user_id` int(7) NOT NULL,
  `text` varchar(1000) NOT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,1,'Test feedback');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(7) NOT NULL AUTO_INCREMENT,
  `email` varchar(44) NOT NULL,
  `pass` varchar(30) NOT NULL,
  `display_name` varchar(18) DEFAULT NULL,
  `dob` varchar(18) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `encryption_key` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin',NULL,NULL,144.4,NULL),(2,'charlebois.gab@gmail.com','jeff','Gabriel',NULL,144.4,NULL);
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

-- Dump completed on 2017-12-14 14:27:31
