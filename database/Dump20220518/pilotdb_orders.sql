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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apt_suit` text,
  `order_date` datetime DEFAULT NULL,
  `total_quantity` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  `confirm` int DEFAULT NULL,
  `available` int DEFAULT NULL,
  `ward_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `district_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `province_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `order_code` varchar(45) DEFAULT NULL,
  `feeship` double DEFAULT NULL,
  `promo_code` varchar(255) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_province_orders_idx` (`province_id`),
  KEY `fk_district_orders_idx` (`district_id`),
  KEY `fk_ward_orders_idx` (`ward_id`),
  KEY `fk_user_orders_idx` (`user_id`),
  CONSTRAINT `fk_district_orders` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`),
  CONSTRAINT `fk_province_orders` FOREIGN KEY (`province_id`) REFERENCES `province` (`id`),
  CONSTRAINT `fk_user_orders` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_ward_orders` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'277 Nguyễn Huệ','2022-01-19 20:09:41',2,198100000,0,1,'19783','474','46',8,'097bda4c-d25d-4799-acd6-a96e135687f4',NULL,NULL,NULL,'Cash Settlement'),(2,'277 Nguyễn Huệ','2022-02-19 20:12:02',3,204540000,0,1,'19783','474','46',7,'b588fc36-b349-4dba-9f5c-1db7ba794daa',NULL,NULL,NULL,'Cash Settlement'),(3,'277 Nguyễn Huệ','2022-02-19 23:41:54',2,114390000,0,1,'06649','193','22',7,'8518606e-f3e5-4777-9ef5-c03a2d81e2d4',NULL,NULL,NULL,'Cash Settlement'),(13,'277 Nguyễn Huệ','2022-03-22 19:54:55',5,353030000,0,1,'19783','474','46',8,'85c6f025-7962-426f-b2fc-6a675b78569f',NULL,NULL,NULL,'Cash Settlement'),(14,'277 Nguyễn Huệ','2022-04-22 19:57:14',7,614840000,0,1,'19783','474','46',7,'c3a1955e-ff1d-4236-936a-54b13b4367f3',NULL,NULL,NULL,'Cash Settlement'),(45,'277 Nguyễn Huệ','2022-05-11 00:08:05',32,311798000,0,1,'19783','474','46',7,'76b156a6-ec62-421e-a5c9-5075c64c4df7',NULL,NULL,NULL,'Cash Settlement'),(46,'277 Nguyễn Huệ','2022-05-11 04:18:17',5,109950000,0,1,'19783','474','46',7,'d5e04fbe-ac0a-4759-abb9-7c1cde998cea',NULL,NULL,NULL,'Cash Settlement'),(47,'277 Nguyễn Huệ','2022-05-11 05:14:21',6,34940000,0,1,'19783','474','46',7,'c49b45a0-583c-405e-818b-783d05ebc887',NULL,NULL,NULL,'Cash Settlement'),(48,'277 Nguyễn Huệ','2022-05-11 05:26:48',10,316670000,0,1,'19783','474','46',7,'05383116-7ffe-43b4-ab77-8e21029b159a',NULL,NULL,NULL,'Cash Settlement'),(49,'277 Nguyễn Huệ','2022-05-11 05:39:54',2,61980000,0,1,'19783','474','46',7,'32c4f6b5-b356-46c1-9521-8ed9ce6860a7',NULL,NULL,NULL,'Cash Settlement'),(50,'277 Nguyễn Huệ','2022-05-11 05:41:01',1,8991000,0,1,'19783','474','46',7,'7c92361e-9941-45a1-9991-a8476f4d3480',NULL,NULL,NULL,'Cash Settlement'),(51,'277 Nguyễn Huệ','2022-05-11 05:43:44',2,39981000,0,1,'19783','474','46',7,'881aeca5-1689-4cc8-9653-52138143355a',NULL,NULL,NULL,'Cash Settlement'),(52,'277 Nguyễn Huệ','2022-05-11 05:46:46',2,39981000,0,1,'19783','474','46',7,'7a76020f-260b-4700-b87d-242d8f6f4fdf',NULL,NULL,NULL,'Cash Settlement'),(53,'277 Nguyễn Huệ','2022-05-11 05:48:54',2,17982000,0,1,'19783','474','46',7,'b520e629-0a87-4df5-b932-797ebe28a500',NULL,NULL,NULL,'Cash Settlement'),(54,'277 Nguyễn Huệ','2022-05-11 07:24:22',3,25972000,0,1,'19783','474','46',7,'5012bb41-adc6-42c6-b7e5-8f7a427023a8',45000,'DFFOOK',450000,'Cash Settlement'),(55,'277 Nguyễn Huệ','2022-05-11 07:43:17',2,16981000,0,1,'19750','474','46',7,'0f5a1306-8889-4912-951e-d1bd3e9ac952',0,'',0,'Cash Settlement'),(56,'76 Nguyễn Phúc Nguyên','2022-05-11 10:34:38',2,34330000,1,0,'19774','474','46',7,'bdb02d51-ac2f-4f71-953c-20ba97854567',50000,'TANSV',1000000,'Cash Settlement'),(57,'101 Điện Biên Phủ','2022-05-11 10:38:43',2,4902000,1,0,'19795','474','46',7,'64a166f7-60ad-463b-8a48-95779c4239a1',0,'',0,'Cash Settlement'),(58,'76 Nguyễn Phúc Nguyên','2022-05-12 11:18:31',1,1290000,0,1,'19774','474','46',7,'09f1897a-cac5-4af5-a0cd-c7d6384329e2',50000,'TANSV',1000000,'Cash Settlement'),(59,'76 Nguyễn Phúc Nguyên','2022-05-12 12:58:10',1,2443000,0,1,'19774','474','46',7,'b4059d8c-121b-474c-8886-28ee57dff6e5',50000,'TANSV',1000000,'Cash Settlement'),(60,'277 Nguyễn Huệ','2022-05-14 10:39:02',2,32280000,0,1,'19783','474','46',7,'2762c73f-5037-4c0d-a410-79385c9558ac',40000,'SAMSUNG',500000,'Tiền mặt');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
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
