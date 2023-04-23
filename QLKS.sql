-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: hotels
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roomId` int DEFAULT NULL,
  `customerCCCD` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bill_customer_idx` (`customerCCCD`),
  KEY `FK_bill_room_idx` (`roomId`),
  CONSTRAINT `FK_bill_customer` FOREIGN KEY (`customerCCCD`) REFERENCES `customer` (`cccd`),
  CONSTRAINT `FK_bill_room` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomnumber`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,4,'00332201'),(2,3,'033204001445'),(4,7,'033456789'),(8,8,'033201001332');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `name` varchar(50) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `roomnumber` int NOT NULL,
  `price` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `outdate` datetime DEFAULT NULL,
  `day` int DEFAULT NULL,
  `cccd` varchar(45) NOT NULL,
  PRIMARY KEY (`cccd`),
  KEY `PK_customer_room_idx` (`roomnumber`),
  CONSTRAINT `FK_customer_room` FOREIGN KEY (`roomnumber`) REFERENCES `room` (`roomnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('ho van anh','000332201','2023-03-12 00:00:00',4,40000,40000,'2023-03-12 00:00:00',1,'00332201'),('Nguyen Duc Hiep','0123456789','2023-04-22 00:00:00',8,50000,150000,'2023-04-22 00:00:00',1,'033201001332'),('Nguyen Van Khanh','0391666666','2023-03-10 00:00:00',3,10000,20000,'2023-03-12 00:00:00',2,'033204001445'),('Nguyen Duc Van','033456789','2023-04-22 00:00:00',7,45000,345000,'2023-04-22 00:00:00',1,'033456789'),('abc cbd','123112','2023-03-11 00:00:00',1,20000,20000,'2023-03-12 00:00:00',1,'11111'),('huynh thanh anh','1234567','2023-04-22 00:00:00',1,20000,NULL,NULL,NULL,'12345');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `soCCCD` varchar(45) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `chucvu` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Nguyen Van Nam  3','0347279982',500000,'033201001332','Nam','Don Phong'),(2,'Tran van hau','12345',300000,'01246','Nam','Don Phong'),(3,'0','0456789',200000,'0332983728','Nam ','Dau Bep');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `name` varchar(50) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phonenumber` varchar(45) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('admin','admin@gmail','1234','123456789');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oderservice`
--

DROP TABLE IF EXISTS `oderservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oderservice` (
  `oder_id` int NOT NULL AUTO_INCREMENT,
  `booking_room` int DEFAULT NULL,
  `serviceOrder_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`oder_id`),
  KEY `fk_oderservice_customer_idx` (`booking_room`),
  KEY `fk_orderservice_service_idx` (`serviceOrder_id`),
  CONSTRAINT `fk_oderservice_customer` FOREIGN KEY (`booking_room`) REFERENCES `customer` (`roomnumber`),
  CONSTRAINT `fk_orderservice_service` FOREIGN KEY (`serviceOrder_id`) REFERENCES `service` (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oderservice`
--

LOCK TABLES `oderservice` WRITE;
/*!40000 ALTER TABLE `oderservice` DISABLE KEYS */;
INSERT INTO `oderservice` VALUES (1,7,'1'),(2,7,'3'),(3,8,'3');
/*!40000 ALTER TABLE `oderservice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `roomnumber` int NOT NULL,
  `roomtype` varchar(45) NOT NULL,
  `bed` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`roomnumber`),
  UNIQUE KEY `roomnumber_UNIQUE` (`roomnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'twin','single',20000,'booked'),(2,'single','single',25000,'booked'),(3,'single','single',10000,'not-book'),(4,'twin','twin',40000,'booked'),(7,'Triple','Triper',45000,'not-book'),(8,'Twin','Single',50000,'not-book');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `service_id` varchar(255) NOT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `service_price` double DEFAULT NULL,
  `service_nv_id` int DEFAULT NULL,
  `service_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  KEY `PK_Service_Employee_idx` (`service_nv_id`),
  CONSTRAINT `FK_service_employee` FOREIGN KEY (`service_nv_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES ('1','Karaoke',200000,1,'Ok'),('3','Xong hoi',100000,1,'OK');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-23  8:46:14
