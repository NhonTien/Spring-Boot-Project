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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `brand_id` int NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(45) DEFAULT NULL,
  `logo` text,
  `description` text,
  PRIMARY KEY (`brand_id`),
  UNIQUE KEY `brand_name_UNIQUE` (`brand_name`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Apple','/images/brand/20220319-1051-4e1lmu.jpg','Apple Inc, California'),(2,'Samsung','/images/brand/20220319-1051-lb2til.png','Samsung Inc, Korea'),(3,'Oppo','/images/brand/20220319-1051-5bs9c1.jpg','Oppo Inc, China'),(4,'Blackberry','/images/brand/20220319-1052-c7aeo2.png','Blackberry Inc, California'),(5,'LG','/images/brand/20220319-1052-qk8oiv.jfif','LG Inc, Japan'),(6,'Xiaomi','/images/brand/20220319-1052-dhs1ut.png','Xiaomi Inc, China'),(7,'Sony','/images/brand/20220319-1052-inm22l.jpg','Sony Inc, Japan'),(8,'Nokia','/images/brand/20220319-1053-mol20f.jpg','Nokia Inc, Korea'),(9,'Huawei','/images/brand/20220319-1053-76cvtb.png','Huawei made in China'),(10,'Vivo','/images/brand/20220319-1053-capkjt.jpg','Vivo Inc, China'),(11,'HTC','/images/brand/20220319-1053-mi8g96.png','HTC Inc, California'),(12,'Asus','/images/brand/20220319-1054-tsdmma.png','Asus Inc, China'),(13,'V Smart','/images/brand/20220319-1054-bj14ir.jpg','VinGroup Inc, Vietnam'),(14,'Realme','/images/brand/20220319-1054-si5r0j.jpg','Realme Inc, China'),(99,'Dell','/images/brand/20220418-0219-mfsfvm.png','Dell Inc, Texas'),(100,'Lenovo','/images/brand/20220418-0220-e7ij04.png','Lenovo Inc, Beijing'),(101,'Acer','/images/brand/20220418-0221-u6tfd6.png','Acer Inc, Taiwan');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
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
