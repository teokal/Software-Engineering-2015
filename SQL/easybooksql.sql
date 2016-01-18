CREATE DATABASE  IF NOT EXISTS `easybooksql` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
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
-- Table structure for table `booked_rooms`
--

DROP TABLE IF EXISTS `booked_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booked_rooms` (
  `book_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booked_rooms`
--

LOCK TABLES `booked_rooms` WRITE;
/*!40000 ALTER TABLE `booked_rooms` DISABLE KEYS */;
INSERT INTO `booked_rooms` VALUES (1,1);
/*!40000 ALTER TABLE `booked_rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `booked_rooms_and_dates`
--

DROP TABLE IF EXISTS `booked_rooms_and_dates`;
/*!50001 DROP VIEW IF EXISTS `booked_rooms_and_dates`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `booked_rooms_and_dates` AS SELECT 
 1 AS `book_id`,
 1 AS `room_id`,
 1 AS `check_in`,
 1 AS `check_out`*/;
SET character_set_client = @saved_cs_client;

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
INSERT INTO `booking_extras` VALUES (1,6),(1,7);
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
  `code` varchar(15) CHARACTER SET utf8 NOT NULL,
  `check_in` datetime NOT NULL,
  `check_out` datetime NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `sname` varchar(45) CHARACTER SET utf8 NOT NULL,
  `tel` varchar(45) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `idnum` varchar(45) CHARACTER SET utf8 NOT NULL,
  `payment_method` varchar(45) CHARACTER SET utf8 NOT NULL,
  `total_cost` decimal(2,0) NOT NULL,
  `paid` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT 'no',
  `money_received` decimal(2,0) NOT NULL DEFAULT '0',
  `status` varchar(45) CHARACTER SET utf8 NOT NULL DEFAULT 'pending',
  PRIMARY KEY (`b_id`),
  UNIQUE KEY `b_id_UNIQUE` (`b_id`),
  UNIQUE KEY `b_code_UNIQUE` (`code`),
  KEY `check_in_idx` (`check_in`),
  KEY `check_out_idx` (`check_out`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,'AVS0234','2015-12-25 14:00:00','2016-01-00 12:00:00','KungFu','Panda','6997003648','vlakas@gmail.com','AA123456','Easy Bank',99,'pending',0,'pending');
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
  `cost_day` decimal(2,0) DEFAULT NULL,
  `cost_unlimited` decimal(2,0) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`e_id`),
  UNIQUE KEY `e_id_UNIQUE` (`e_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extras`
--

LOCK TABLES `extras` WRITE;
/*!40000 ALTER TABLE `extras` DISABLE KEYS */;
INSERT INTO `extras` VALUES (1,'Breakfast',5,0,NULL),(2,'Diner',10,0,NULL),(3,'Pool',0,5,NULL),(4,'Gym',NULL,5,NULL),(5,'Massage',0,30,NULL),(6,'Spa',0,30,NULL);
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
  PRIMARY KEY (`o_id`),
  UNIQUE KEY `o_id_UNIQUE` (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (1,'PreEaster','2016-10-10 00:00:00','2016-10-25 00:00:00',5,'\0','','\0','','','','',100,0);
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offers_lang`
--

DROP TABLE IF EXISTS `offers_lang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offers_lang` (
  `o_id` int(11) NOT NULL,
  `lang_en` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  `lang_gr` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lang_de` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lang_es` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers_lang`
--

LOCK TABLES `offers_lang` WRITE;
/*!40000 ALTER TABLE `offers_lang` DISABLE KEYS */;
INSERT INTO `offers_lang` VALUES (1,'Enjoy this special offer!',NULL,NULL,NULL);
/*!40000 ALTER TABLE `offers_lang` ENABLE KEYS */;
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
  `t_standard` bit(1) NOT NULL DEFAULT b'0',
  `t_comfort` bit(1) NOT NULL DEFAULT b'0',
  `t_suite` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`s_id`),
  UNIQUE KEY `s_id_UNIQUE` (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_services`
--

LOCK TABLES `room_services` WRITE;
/*!40000 ALTER TABLE `room_services` DISABLE KEYS */;
INSERT INTO `room_services` VALUES (6,'tzakouzi','','',''),(7,'tzaki','\0','',''),(8,'LCD TV','\0','',''),(9,'View Sea','\0','\0',''),(10,'View Pool','','','\0');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'Room 132','Comfort',4,1,85.50),(2,'Room 234','Standard',2,0,19.50),(3,'New York Suite','Suite',1,3,55.00),(4,'asdasd','Comfort',1,1,75.00),(5,'Room 23','Comfort',1,1,23.00),(6,'Room 43','Comfort',0,1,36.00),(7,'Silicon Valley Suite','Suite',0,3,350.00),(9,'New York Suite 2','Suite',1,3,55.00);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms_lang`
--

DROP TABLE IF EXISTS `rooms_lang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms_lang` (
  `room_id` int(11) NOT NULL,
  `lang_en` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  `lang_gr` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lang_de` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lang_es` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms_lang`
--

LOCK TABLES `rooms_lang` WRITE;
/*!40000 ALTER TABLE `rooms_lang` DISABLE KEYS */;
INSERT INTO `rooms_lang` VALUES (1,'Enjoy your staying at our hotel during the Pre-Easter period!','Απολαύστε τη διαμονή στο ξενοδοχείο για την περίοδο πριν το Πάσχα!',NULL,NULL),(2,'Enjoy your staying at our hotel during the Pre-Easter period!','Απολαύστε τη διαμονή στο ξενοδοχείο για την περίοδο πριν το Πάσχα!',NULL,NULL),(3,'ascasca','δσγσδγδσ',NULL,NULL),(4,'ascasca','φασφαφα',NULL,NULL);
/*!40000 ALTER TABLE `rooms_lang` ENABLE KEYS */;
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

--
-- Final view structure for view `booked_rooms_and_dates`
--

/*!50001 DROP VIEW IF EXISTS `booked_rooms_and_dates`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `booked_rooms_and_dates` AS select `booked_rooms`.`book_id` AS `book_id`,`booked_rooms`.`room_id` AS `room_id`,`bookings`.`check_in` AS `check_in`,`bookings`.`check_out` AS `check_out` from (`booked_rooms` left join `bookings` on((`bookings`.`b_id` = `booked_rooms`.`book_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-18  6:06:29
