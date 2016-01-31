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
INSERT INTO `booking_extras` VALUES (14,1),(14,2),(14,3),(15,1),(15,2),(15,5),(16,1),(16,2),(16,3),(16,4),(16,5),(16,6),(16,7),(16,8),(17,1),(17,3),(17,5),(18,2),(18,3),(18,4),(18,6),(18,8),(19,1),(20,1),(20,2),(20,4),(20,5),(20,6),(20,7),(21,1),(21,2),(21,3),(21,4),(21,7),(21,8),(22,2),(22,3),(22,4),(22,5),(22,6),(22,7),(25,2),(25,3),(25,4),(25,5),(25,7),(26,1),(26,2),(26,3),(26,4),(26,5),(26,8),(27,1),(27,2),(27,4),(27,5),(27,7),(28,1),(28,2),(28,5),(28,6),(29,1),(29,2),(30,2),(30,3),(30,4),(30,5),(30,6),(31,1),(31,2),(32,2),(32,3),(32,4),(32,7),(33,1),(33,2),(33,3),(33,6),(34,5),(34,6),(34,7),(34,8),(35,3),(35,6),(35,7),(36,1),(36,4),(36,5),(37,1),(37,2),(37,3),(37,4),(37,5),(37,6),(37,7),(37,8),(38,1),(38,2),(38,3),(38,4),(38,5),(38,6),(38,7),(38,8),(40,1),(40,2),(40,3),(40,4),(40,5),(40,6),(40,7),(40,8),(41,5),(42,4),(43,1),(43,2),(43,4),(43,7),(43,8),(44,1),(44,2),(44,8),(45,1),(45,4),(45,5),(45,7),(46,2),(46,3),(46,5),(46,7),(46,8),(47,2),(47,4),(47,5),(47,7),(48,1),(48,2),(48,3),(48,4),(48,5),(48,6),(48,8),(49,4),(49,5),(49,6),(49,8),(50,3),(50,4),(50,7),(52,2),(52,4),(52,7),(54,2),(54,6),(54,7),(54,8),(55,1),(55,4),(55,5);
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
  `numOfPerson` int(10) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (17,6,0,'70EFDF2EC9','2016-01-19 14:00:00','2016-01-26 12:00:00',3,2,'Mr.','Petros','Papadakos','AB 12345','697123456','petrosp@hotmail.com','EasyBank',138.10,'paid',138.10,'pending','free wifi'),(19,10,0,'1F0E3DAD99','2016-05-18 14:00:00','2016-05-20 12:00:00',2,4,'Mr.','Konstantinos','Efstathioou','98765','78945632','konefsta@gmail.com','EasyBank',67.60,'paid',67.60,'pending','I want women every night'),(20,9,0,'98F1370821','2016-02-22 14:00:00','2016-02-25 12:00:00',3,3,'Mr.','Kostas','Kapitanis','23432','454345','pezo@mail.com','EasyBank',361.90,'paid',361.90,'pending','ewfwr'),(21,5,0,'3C59DC048E','2016-03-01 14:00:00','2016-03-16 12:00:00',15,2,'Mr.','Joghny','Depp','2323432','343542542354','xazoulis@mail.com','EasyBank',820.00,'paid',820.00,'pending',''),(25,2,18,'8E296A067A','2016-02-09 14:00:00','2016-02-18 12:00:00',9,3,'Mr.','Maria','Panagiotou','23432','233432','ee@aa.com','EasyBank',507.85,'paid',507.85,'pending','ewfewfwe'),(26,5,0,'4E732CED34','2016-02-02 14:00:00','2016-02-04 12:00:00',2,3,'Mr.','Demos','TOkkas','2343','3432532','efew@gmai.com','EasyBank',241.10,'paid',241.10,'pending','I want women for sex'),(27,31,17,'2E74F10E03','2016-01-31 14:00:00','2016-02-02 12:00:00',2,3,'Mr.','Giorgos','Efrem','129834','9667003825','efrem@gmail.com','EasyBank',222.10,'paid',222.10,'pending',''),(28,28,0,'33E75FF09D','2016-02-04 14:00:00','2016-02-06 12:00:00',2,3,'Mr.','Andreas','Dionisiou','325423','6910452545','dionisiou@yahoo.gr','EasyBank',177.10,'paid',177.10,'pending',''),(29,34,0,'6EA9AB1BAA','2016-02-14 14:00:00','2016-02-15 12:00:00',1,2,'Mr.','Nikoletta','Georgiou','432543','691065748','nikole@outlook.com','EasyBank',525.20,'paid',525.20,'cancelled','I want food.'),(30,33,21,'34173CB38F','2016-02-13 14:00:00','2016-02-15 12:00:00',2,2,'Mr.','Marios','Mixail','978451','210489122','marios@gmail.om','EasyBank',503.10,'paid',503.10,'pending',''),(31,5,0,'C16A5320FA','2016-07-12 14:00:00','2016-07-14 12:00:00',2,4,'Mr.','Maria','Panayiotou','158612','698741523','maria@outlook.com','EasyBank',146.80,'paid',146.80,'pending',''),(32,21,0,'6364D3F0F4','2016-03-15 14:00:00','2016-03-22 12:00:00',7,3,'Mr.','Zinonas','Mixail','784123','987123654','zin@msn.com','EasyBank',552.90,'paid',552.90,'pending',''),(33,29,0,'182BE0C5CD','2016-10-10 14:00:00','2016-10-19 12:00:00',9,3,'Mr.','Rafael','Odiseous','7815154','69971536','raf@hotmail.com','EasyBank',752.70,'paid',752.70,'pending',''),(34,29,0,'E369853DF7','2016-05-17 14:00:00','2016-05-25 12:00:00',8,4,'Mr.','Andreas','Nikolaou','7844514','5965929198','nik@gmail.com','EasyBank',473.50,'paid',473.50,'pending',''),(35,32,0,'1C383CD30B','2016-05-20 14:00:00','2016-05-31 12:00:00',11,2,'Mr.','Stavros','Stavrou','48521','54894158','customer@gamil.com','EasyBank',1167.50,'paid',1167.50,'pending',''),(36,29,17,'19CA14E7EA','2016-01-31 14:00:00','2016-02-01 12:00:00',1,2,'Mr.','Xristina','Konstantinou','784512','210987415','Xris@msn.com','EasyBank',61.40,'paid',61.40,'pending',''),(37,1,0,'A5BFC9E079','2016-07-06 14:00:00','2016-07-20 12:00:00',14,4,'Mr.','Sotiris','Moustakas','7842123','699703512','sot@gmail.com','EasyBank',2126.10,'paid',2126.10,'pending',''),(38,31,0,'A5771BCE93','2016-08-08 14:00:00','2016-08-31 12:00:00',23,5,'Mr.','Georgia','Georgiou','3254233','4354353643','geo@msn.com','EasyBank',2759.00,'paid',2759.00,'pending',''),(39,5,0,'D67D8AB4F4','2016-04-12 14:00:00','2016-04-14 12:00:00',2,3,'Mr.','Foivos','Irakleous','2342352','210698741','foiv@mail.com','EasyBank',46.00,'paid',46.00,'pending',''),(40,34,0,'D645920E39','2016-12-25 14:00:00','2016-12-29 12:00:00',4,2,'Mr.','Panos','Toka','3454325','5432124252','ael@mail.com','EasyBank',2221.30,'paid',2221.30,'pending',''),(41,14,0,'3416A75F4C','2016-06-08 14:00:00','2016-06-10 12:00:00',2,2,'Mr.','Andreas','Xatzikosti','232321','6997003825','and@yahoo.gr','EasyBank',56.00,'paid',56.00,'pending',''),(42,36,0,'A1D0C6E83F','2016-06-26 14:00:00','2016-06-28 12:00:00',2,5,'Mr.','Mixalis','Dionisiou','8784214','6997003825','omonoia@gmail.om','EasyBank',80.00,'paid',80.00,'pending',''),(43,34,0,'17E62166FC','2016-11-17 14:00:00','2016-11-30 12:00:00',13,3,'Mr.','Peter','Johanson','7841512','6997003825','peter@gmail.com','EasyBank',7086.90,'paid',7086.90,'pending',''),(44,1,0,'F7177163C8','2016-09-14 14:00:00','2016-09-16 12:00:00',2,3,'Mr.','Tomas','Orlando','8484851','6997003825','raf@gmail.com','EasyBank',287.10,'paid',287.10,'pending',''),(45,36,22,'6C8349CC72','2016-09-19 14:00:00','2016-09-26 12:00:00',7,2,'Mr.','Jessica','Alba','7894512','96781456','alba@msn.com','EasyBank',288.80,'paid',288.80,'pending',''),(46,20,0,'D9D4F495E8','2016-09-27 14:00:00','2016-09-29 12:00:00',2,3,'Mr.','George','Clooney','7412587','69874123','jorg@msn.com','EasyBank',283.90,'paid',283.90,'pending',''),(47,26,22,'67C6A1E7CE','2016-09-29 14:00:00','2016-10-05 12:00:00',6,3,'Mr.','Christan','Grey','12342','123514578','chris@msn.com','EasyBank',327.20,'paid',327.20,'pending',''),(48,34,0,'642E92EFB7','2016-04-06 14:00:00','2016-04-25 12:00:00',19,2,'Mr.','Cameron','Diaz','7854123','945186218','cam@yahoo.com','EasyBank',10069.30,'paid',10069.30,'pending',''),(49,7,0,'F457C545A9','2016-05-15 14:00:00','2016-05-24 12:00:00',9,2,'Mr.','Lion','Messi','2342521','725142142','messi@gmail.com','EasyBank',3210.50,'paid',3210.50,'pending',''),(50,5,0,'C0C7C76D30','2016-07-05 14:00:00','2016-07-06 12:00:00',1,2,'Mr.','Michle','Hamburger','25252331','234252253','mic@msn.com','EasyBank',93.00,'paid',93.00,'pending',''),(51,5,0,'2838023A77','2016-07-17 14:00:00','2016-07-18 12:00:00',1,2,'Mr.','Leonardo','DiCaprio','2412251','2534154','leo@msn.com','EasyBank',23.00,'paid',23.00,'pending',''),(52,5,0,'9A1158154D','2016-07-26 14:00:00','2016-07-28 12:00:00',2,3,'Mr.','Monica','Belucci','2642362','433662464','mon@gmail.com','EasyBank',160.40,'paid',160.40,'pending',''),(53,34,0,'D82C8D1619','2016-11-23 14:00:00','2016-11-23 23:59:59',1,2,'Mr.','Eirini','Karditsa','1234524','6997415865','umpudu@gmail.com','EasyBank',500.00,'paid',500.00,'pending',''),(54,34,0,'A684ECEEE7','2016-07-07 14:00:00','2016-07-08 12:00:00',1,2,'Mr.','Giorgos','Ioanidis','568712','6997003824','gpm@gmail.com','EasyBank',584.30,'paid',584.30,'pending',''),(55,14,1,'B53B3A3D6A','2016-01-31 14:00:00','2016-02-06 12:00:00',6,2,'Mr.','oejwgw','oigwj','3209423','23098409','eovj@meroij.com','EasyBank',78.40,'paid',78.40,'pending','');
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
INSERT INTO `extras` VALUES (1,'Breakfast',2.70),(2,'Full Dinner',9.90),(3,'Pool',15.00),(4,'Hair Salon',10.00),(5,'Fitness Centre',8.00),(6,'Spa Treatments',7.50),(7,'Face & Body Care',15.00),(8,'Massage Therapies',13.50);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (1,'PreEaster','2016-01-01 00:00:00','2016-02-26 00:00:00',5,'','','','','','','',100,0,'asdadsa'),(16,'After Xmas','2016-03-08 00:00:00','2016-03-27 00:00:00',2,'','','','','','\0','\0',0,30,'One more offer only for you!'),(17,'We Celebrate','2016-01-22 00:00:00','2016-02-04 00:00:00',1,'\0','','\0','\0','\0','','\0',0,25,'We are celebrating something!'),(18,'WeRtheBest','2016-01-29 00:00:00','2016-02-25 00:00:00',7,'\0','','','','','','\0',0,90,'X-Clusive Offer\n-90% Discount for our Standard Rooms above 7 days bookings'),(20,'Summer','2016-06-01 00:00:00','2016-08-31 00:00:00',4,'\0','\0','\0','\0','\0','\0','',0,0,'Enjoy your summer holidays :)'),(21,'Valentines Day','2016-02-13 00:00:00','2016-02-16 00:00:00',2,'\0','','','','\0','','',0,50,'Come with your lover!'),(22,'Large Families','2016-09-11 00:00:00','2016-10-31 00:00:00',5,'\0','\0','\0','','','\0','\0',50,0,'');
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
INSERT INTO `room_services` VALUES (6,'Kitchen',1,0,1),(7,'Fridge',1,0,0),(8,'A/C',1,0,1),(9,'Wi-Fi',1,0,0),(10,'Radio',1,0,1),(11,'LCD TV',1,1,1),(12,'Safe',1,1,0),(13,'Fireplace',1,1,0),(14,'Mini Bar',0,1,0),(15,'Jacuzzi',0,1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'Room 132','Comfort',4,1,85.50),(2,'Room 234','Standard',2,2,129.50),(3,'New York Suite','Suite',1,3,55.00),(5,'Room 23','Standard',1,0,23.00),(6,'Room 43','Standard',0,1,36.00),(7,'Silicon Valley Suite','Suite',0,3,350.00),(9,'New York Suite 2','Suite',1,3,55.00),(10,'Room 3445','Comfort',0,1,23.00),(14,'Relax','Standard',1,0,20.00),(16,'Vacations','Comfort',1,1,10.00),(20,'Village 200','Comfort',2,2,35.00),(21,'Village 201','Comfort',0,1,35.00),(22,'Village 202','Comfort',4,0,35.00),(23,'Garden 101','Standard',1,1,15.00),(24,'Garden 102','Standard',2,0,20.00),(25,'Garden 104','Comfort',2,1,20.00),(26,'Garden 105','Standard',2,2,20.00),(27,'Pool A','Standard',2,1,25.00),(28,'Pool B','Comfort',2,2,35.00),(29,'Chivas','Comfort',2,0,40.00),(30,'Jack Daniels','Comfort',3,1,40.00),(31,'Jack Daniels','Comfort',0,2,45.00),(32,'Las Vegas','Suite',0,1,100.00),(33,'Las Vegas','Suite',0,2,400.00),(34,'50Shades','Suite',0,1,500.00),(36,'Room 150','Standard',3,2,35.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'admin','202cb962ac59075b964b07152d234b70','Admin','Admin','admin@easybook.gr','admin'),(3,'user','202cb962ac59075b964b07152d234b70','User','User','user@easybook.gr','user');
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

-- Dump completed on 2016-01-31 23:17:31
