-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: pilotdb
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `content` text,
  `product_id` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_product_comment_idx` (`product_id`),
  KEY `fk_user_comment_idx` (`user_id`),
  CONSTRAINT `fk_product_comment` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `fk_user_comment` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Very Good',317,'2022-04-12 00:00:00',9),(2,'Good',317,'2022-04-12 00:00:00',8),(3,'Product is very good',317,'2022-04-13 00:00:00',7),(4,'good',316,'2022-04-13 00:00:00',8),(5,'Very Good',317,'2022-04-14 00:00:00',9),(6,'Very Good',316,'2022-04-14 00:00:00',7),(7,'Product is very good',316,'2022-04-14 00:00:00',8),(8,'Very good',316,'2022-04-14 00:00:00',9),(9,'Perfect',316,'2022-04-14 04:15:00',7),(10,'Excellent product',317,'2022-04-14 04:23:53',8),(11,'Well',317,'2022-04-14 14:50:15',9),(12,'Excellent Product',317,'2022-04-14 15:10:32',9),(13,'Good',317,'2022-04-19 19:04:04',7),(14,'Excellent Product',316,'2022-04-19 19:24:12',7),(15,'Good',316,'2022-04-19 19:25:24',7),(16,'Good',316,'2022-04-19 19:26:19',7),(18,'Excellent',317,'2022-04-23 06:31:35',15),(20,'Excellent Product',317,'2022-04-23 06:40:37',15),(21,'Good Product',317,'2022-04-23 07:23:30',8),(22,'Good',317,'2022-04-23 20:22:30',8),(23,'Excellent Product',317,'2022-04-23 20:23:31',8),(24,'Excellent Product',317,'2022-04-23 20:27:14',9),(25,'Excellent Product',316,'2022-04-30 10:40:55',7),(26,'Excellent Product',316,'2022-04-30 10:42:02',7);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-18  0:06:44
