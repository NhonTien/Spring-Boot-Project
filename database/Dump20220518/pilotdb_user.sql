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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `lastname` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `image` text,
  `role` varchar(45) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'admin1',NULL,'admin123',NULL,NULL,'ADMIN',NULL),(2,NULL,'admin2',NULL,'admin456',NULL,NULL,'ADMIN',NULL),(3,NULL,'admin3',NULL,'admin789',NULL,NULL,'ADMIN',NULL),(4,'','admin4','','$2a$10$GegsJL2uu619UI4J5vqIN.tB5gr7SdAbJ1E0zltW4/u9bwOI6eh7C','','','ADMIN',NULL),(5,'','admin5','','$2a$10$wDnHWY0EDb7bkTjPHOu.o.ByJZ8VyAgk2IJi3WyEuLmxd7MG.4Tqq','','','ADMIN',NULL),(6,'','admin6','','$2a$10$bOsNMcSfhs5tiEhF9gOxH.xw08ESVaQZbo3R462K3eGzg4DZgjprO','','','ADMIN',NULL),(7,'Trần Nhơn','Tiến','trannhontien@gmail.com','$2a$10$NdA5hylmqcx2X7X8739Hwegx0ZElyY0lacLQc7DAXHolK4qWHzC6q','0934890765','/images/user/20220422-1826-jjnfjf.png','ROLE_ADMIN','2022-05-13 00:00:00'),(8,'Nguyễn Văn','Thắng','nguyenvanthang@gmail.com','$2a$10$wDnHWY0EDb7bkTjPHOu.o.ByJZ8VyAgk2IJi3WyEuLmxd7MG.4Tqq','0934567904','/images/user/20220430-1345-f9ir4t.jpg','ROLE_USER','2022-04-30 00:00:00'),(9,'Nguyễn Văn ','Thành','nguyenvanthanh@gmail.com','$2a$10$wDnHWY0EDb7bkTjPHOu.o.ByJZ8VyAgk2IJi3WyEuLmxd7MG.4Tqq','0934896789','','ROLE_USER',NULL),(10,'Lê Văn','Anh','levananh@gmail.com','$2a$10$wDnHWY0EDb7bkTjPHOu.o.ByJZ8VyAgk2IJi3WyEuLmxd7MG.4Tqq','0934689076','','ROLE_USER',NULL),(11,'Lê Văn','Hoàng','levanhoang@gmail.com','$2a$10$6SlKP8SJhV/Kn2x.zHEm6OHZcctz3.zCwBRsliGlktt/lXwe38JGa','0934780657','','ROLE_USER',NULL),(12,'Nguyễn Văn','Hoàng','nguyenvanhoang@gmail.com','$2a$10$ftwvLrlDI3Mui6gCtivfp.TT8/aUOBLDomEmLYjPp3tub3skv1ohm','0965356789','','ROLE_USER',NULL),(13,'Nguyễn','Hoàng','nguyenhoang@gmail.com','$2a$10$sHBO8jS/zy8iMAuHI7RN3eRZLME4JYeSXcoCgfbiuohv22LziejpO','0945789765','','ROLE_USER',NULL),(14,'Lê Văn','Hoàng','vanhoang@gmail.com','$2a$10$yZSchvf8kt323u1BXmzxouWJXi.pHAJGzOAzhAZZr3GloGdjBNkPS','0987456345','','ROLE_USER',NULL),(15,'Hoàng Văn','Hưng','hoangvanhung@gmail.com','$2a$10$31FUDSBk.qVviIBtuA0bTuYTYeEOGnTElzm8yfK8Nqen.5Vk/HNRe','0934678455','/images/user/20220423-0626-gumis1.png','ROLE_USER','2022-04-23 00:00:00'),(16,'Nguyễn Hoàng','Anh','nguyenhoanganh@gmail.com','$2a$10$k2hsW9vj7li1AHM/r3BiB.Nk.x78bE6w7MNNuqGGvqTFBmM1HTShy','0934789654','/images/user/20220422-0936-j6cp0i.png','ROLE_USER',NULL),(17,'Nguyễn Hoàng','Thành','nguyenhoangthanh@gmail.com','$2a$10$X4i50JzcHINJiO2iRdkuq.ffXflqtahT.jYnel3G9Nm0gj.m7TjuW','0934457683','','ROLE_ADMIN',NULL),(20,'s','s','s','$2a$10$vz7KRLl08gJELz0914.uBOhkmHprfCxIl5idEEujvAoFiUsutExam','s','','ADMIN','2022-04-22 00:00:00'),(21,'ss','ss','sss','$2a$10$icup2jNgB5c4Aitq8h/GzertKZuwYwccSLxi7d.wjs04MBuZqCJ/m','ss','','ADMIN','2022-04-22 00:00:00'),(22,'Nguyễn Văn','Hoàng','nvhoang@gmail.com','$2a$10$hGSEgLnFLvKW0vcx88KzDuq9f5cFbsFHmSGfufdOD1docKBE6hGPa','ss','/images/user/20220422-1925-mr7la9.png','ADMIN','2022-04-22 00:00:00'),(23,'Nguyễn Hoàng','Minh','nguyenhoangminh@gmail.com','$2a$10$jtvWp5KcMNBnbU4Sbg9LtOi2zKzk2F7i7q/UsVSM6FQtFMf5m0FzS','0934894562','','ROLE_USER','2022-04-26 00:00:00'),(24,'Nguyễn Văn','Thành','vanthanhnguyen@gmail.com','$2a$10$VS8ZbxWNCiScBWRqi9AmpenWPDYc5V8w98XFdhPKiMH4ajOZENO8G','0934890654','','ROLE_USER','2022-04-28 00:00:00');
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

-- Dump completed on 2022-05-18  0:06:44
