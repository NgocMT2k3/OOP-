-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: library_database
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `username` varchar(50) NOT NULL,
  `bookid` int NOT NULL,
  `request_date` timestamp NULL DEFAULT NULL,
  `borrow_date` timestamp NULL DEFAULT NULL,
  `renew_date` timestamp NULL DEFAULT NULL,
  `return_date` timestamp NULL DEFAULT NULL,
  `status` varchar(10) GENERATED ALWAYS AS ((case when ((`borrow_date` is not null) and (`return_date` is null)) then _utf8mb4'borrowed' when ((`borrow_date` is not null) and (`return_date` is not null)) then _utf8mb4'returned' end)) STORED,
  KEY `username` (`username`),
  KEY `bookid` (`bookid`),
  CONSTRAINT `history_ibfk_1` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`),
  CONSTRAINT `history_ibfk_2` FOREIGN KEY (`bookid`) REFERENCES `books` (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` (`username`, `bookid`, `request_date`, `borrow_date`, `renew_date`, `return_date`) VALUES ('1',1,'2025-04-29 08:03:44','2025-04-29 10:23:46','2025-04-29 10:22:25','2025-04-29 09:05:45'),('1',2,'2025-04-29 08:03:50','2025-05-02 16:48:27','2025-05-02 16:59:12',NULL),('1',1,'2025-04-29 09:33:00','2025-04-29 10:23:46','2025-04-29 10:22:25',NULL),('ngoc',2,'2025-05-02 16:36:10','2025-05-02 16:48:27','2025-05-02 16:59:12',NULL),('ngoc',2,'2025-05-02 16:36:26','2025-05-02 16:48:27','2025-05-02 16:59:12',NULL),('ngoc',3,'2025-05-02 16:56:53',NULL,NULL,NULL);
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-03  0:59:45
