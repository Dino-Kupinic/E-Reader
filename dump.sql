-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: ereader
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` datetime(6) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  `resource_id` bigint DEFAULT NULL,
  `is_returned` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKphxmq2f253m8rov3dgiub0oq4` (`customer_id`),
  KEY `FK1np22xingf8dbxv4dbxol4wj8` (`resource_id`),
  CONSTRAINT `FK1np22xingf8dbxv4dbxol4wj8` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`),
  CONSTRAINT `FKphxmq2f253m8rov3dgiub0oq4` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES (11,'2024-02-27 00:00:00.000000','2024-02-13 00:00:00.000000',5,7,_binary ''),(14,'2024-03-22 00:00:00.000000','2024-03-16 00:00:00.000000',2,9,_binary ''),(15,'2024-03-22 00:00:00.000000','2024-03-17 00:00:00.000000',2,10,_binary ''),(17,'2024-04-07 00:00:00.000000','2024-04-06 00:00:00.000000',10,6,_binary ''),(19,'2024-03-16 00:00:00.000000','2024-03-11 00:00:00.000000',2,7,_binary '\0'),(20,'2024-03-17 00:00:00.000000','2024-03-11 00:00:00.000000',2,8,_binary '\0'),(21,'2024-03-15 00:00:00.000000','2024-03-11 00:00:00.000000',2,9,_binary '\0'),(22,'2024-03-16 00:00:00.000000','2024-03-11 00:00:00.000000',2,15,_binary '\0'),(23,'2024-03-13 00:00:00.000000','2024-03-11 00:00:00.000000',2,16,_binary '\0'),(24,'2024-04-07 00:00:00.000000','2024-04-06 00:00:00.000000',9,17,_binary '');
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (2,'Horror'),(3,'Action'),(4,'Family'),(5,'Thriller'),(6,'Love'),(7,'Drama'),(8,'Comedy'),(9,'Western'),(10,'Sciene Fiction');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (2,'Dino','Kupinic'),(3,'Heinz','Schweiger'),(4,'Peter','Rathgeb'),(5,'Michael','Ploier'),(6,'Jannick','Angerer'),(7,'Thomas','M├╝ller'),(8,'Lara','M├╝ller'),(9,'Alex','Rechberger'),(10,'Daniel','M├╝ller');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `type_id` bigint DEFAULT NULL,
  `daily_rate` double NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqi8wwpr89wbb0pdwsma5dhysj` (`category_id`),
  KEY `FKnnl5bjk7stntwucnfeavmmwjj` (`type_id`),
  CONSTRAINT `FKnnl5bjk7stntwucnfeavmmwjj` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`),
  CONSTRAINT `FKqi8wwpr89wbb0pdwsma5dhysj` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES (6,'Fifty Shades of Gray',3,3,0.25,_binary ''),(7,'Herr der Ringe',5,1,0.3,_binary '\0'),(8,'Avatar',2,6,0.1,_binary '\0'),(9,'Oppenheimer',7,4,0.4,_binary '\0'),(10,'Dr. House',8,4,0.5,_binary ''),(15,'Pinguine aus Madagaskar',8,6,0.3,_binary '\0'),(16,'Ups die Pannenshow',8,1,0.1,_binary '\0'),(17,'Big Bang Theory',4,4,0.1,_binary '\0'),(18,'Indiana Jones',3,1,0.1,_binary '');
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'BluRay'),(3,'Book'),(4,'Digital'),(5,'DVD'),(6,'TV');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-11 21:04:16
