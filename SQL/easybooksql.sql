USE `easybooksql`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: easybooksql
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.9-MariaDB

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
-- Table structure for table `booking_extras`
--

DROP TABLE IF EXISTS `booking_extras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking_extras` (
  `b_id` int(11) NOT NULL,
  `e_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_extras`
--

LOCK TABLES `booking_extras` WRITE;
/*!40000 ALTER TABLE `booking_extras` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking_extras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookings` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL,
  `offer_id` int(11) DEFAULT NULL,
  `code` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `check_in` datetime NOT NULL,
  `check_out` datetime NOT NULL,
  `numdays` int(11) NOT NULL,
  `title` varchar(10) CHARACTER SET utf8 NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `sname` varchar(45) CHARACTER SET utf8 NOT NULL,
  `idnum` varchar(45) CHARACTER SET utf8 NOT NULL,
  `tel` varchar(45) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `payment_method` varchar(45) CHARACTER SET utf8 NOT NULL,
  `total_cost` decimal(10,2) NOT NULL,
  `paid` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT 'no',
  `money_received` decimal(10,2) NOT NULL DEFAULT '0.00',
  `status` varchar(45) CHARACTER SET utf8 NOT NULL DEFAULT 'pending',
  `comments` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`b_id`),
  UNIQUE KEY `b_id_UNIQUE` (`b_id`),
  UNIQUE KEY `b_code_UNIQUE` (`code`),
  KEY `check_in_idx` (`check_in`),
  KEY `check_out_idx` (`check_out`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extras`
--

DROP TABLE IF EXISTS `extras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extras` (
  `e_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `cost` decimal(5,2) NOT NULL,
  PRIMARY KEY (`e_id`),
  UNIQUE KEY `e_id_UNIQUE` (`e_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extras`
--

LOCK TABLES `extras` WRITE;
/*!40000 ALTER TABLE `extras` DISABLE KEYS */;
INSERT INTO `extras` VALUES (1,'Breakfast',2.70),(2,'Full Dinner',9.90),(3,'Pool',15.00),(4,'Hair Salon',10.00),(5,'Fitness Centre',7.00),(6,'Spa Treatments',7.50),(7,'Face & Body Care',15.00),(8,'Massage Therapies',13.50);
/*!40000 ALTER TABLE `extras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offers` (
  `o_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `valid_from` datetime NOT NULL,
  `valid_until` datetime NOT NULL,
  `required_days` int(11) NOT NULL DEFAULT '0',
  `one_bed` bit(1) DEFAULT b'0',
  `two_beds` bit(1) DEFAULT b'0',
  `three_beds` bit(1) DEFAULT b'0',
  `fplus_beds` bit(1) DEFAULT b'0',
  `type_stand` bit(1) NOT NULL,
  `type_comf` bit(1) DEFAULT b'0',
  `type_suite` bit(1) DEFAULT b'0',
  `discount_amount` int(11) DEFAULT '0',
  `discount_percentage` int(11) DEFAULT '0',
  `lang_en` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`o_id`),
  UNIQUE KEY `o_id_UNIQUE` (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (1,'PreEaster','2016-01-01 00:00:00','2016-02-26 00:00:00',5,'','','','','','','',100,0,'asdadsa'),(16,'After Xmas','2016-03-08 00:00:00','2016-03-27 00:00:00',2,'','','','','','\0','\0',0,30,'One more offer only for you!'),(17,'We Celebrate','2016-01-22 00:00:00','2016-02-04 00:00:00',1,'\0','','\0','\0','\0','','\0',0,25,'We are celebrating something!'),(18,'WeRtheBest','2016-01-29 00:00:00','2016-02-25 00:00:00',7,'\0','','','','','','\0',0,90,'X-Clusive Offer\n-90% Discount for our Standard Rooms above 7 days bookings');
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_services`
--

DROP TABLE IF EXISTS `room_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_services` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(45) NOT NULL,
  `t_standard` tinyint(1) NOT NULL DEFAULT '0',
  `t_comfort` tinyint(1) NOT NULL DEFAULT '0',
  `t_suite` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`s_id`),
  UNIQUE KEY `s_id_UNIQUE` (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_services`
--

LOCK TABLES `room_services` WRITE;
/*!40000 ALTER TABLE `room_services` DISABLE KEYS */;
INSERT INTO `room_services` VALUES (6,'Kitchen',1,1,1),(7,'Fridge',0,1,1),(8,'A/C',0,1,1),(9,'Wi-Fi',0,0,1),(10,'Radio',1,1,0),(11,'LCD TV',0,0,0),(12,'Safe',0,0,0),(13,'Fireplace',0,0,0),(14,'Mini Bar',0,0,0),(15,'Jacuzzi',0,0,0);
/*!40000 ALTER TABLE `room_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(45) NOT NULL,
  `room_type` varchar(45) NOT NULL,
  `single_beds` int(11) NOT NULL DEFAULT '0',
  `double_beds` int(11) NOT NULL DEFAULT '0',
  `cost` decimal(10,2) NOT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `room_id_UNIQUE` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'Room 132','Comfort',4,1,85.50),(2,'Room 234','Standard',2,2,129.50),(3,'New York Suite','Suite',1,3,55.00),(5,'Room 23','Standard',1,0,23.00),(6,'Room 43','Standard',0,1,36.00),(7,'Silicon Valley Suite','Suite',0,3,350.00),(9,'New York Suite 2','Suite',1,3,55.00),(10,'Room 3445','Comfort',0,1,23.00);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(300) NOT NULL,
  `name` varchar(45) NOT NULL,
  `sname` varchar(45) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_type` varchar(45) NOT NULL DEFAULT 'user',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'rafsonic','202cb962ac59075b964b07152d234b70','Rafael','Panayiotou',NULL,'admin'),(2,'teokal','ecb811f3c49a41b9a16374cba20cac50','Thodoris','Kalatzis','teo.kal@hotmail.com','admin'),(3,'petrosp','202cb962ac59075b964b07152d234b70','Petros','Papadakos',NULL,'admin'),(4,'user','202cb962ac59075b964b07152d234b70','User','Test','user@easybook.gr','user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-27  4:34:38
