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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `bookid` int NOT NULL,
  `title` varchar(200) NOT NULL,
  `publishyear` int NOT NULL,
  `author` varchar(50) NOT NULL,
  `category` varchar(100) NOT NULL,
  `quantity` int NOT NULL,
  `price` int DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Giáo Trình Nhập Môn An Toàn Thông Tin',2021,'TS. Nguyễn Đức Hoàng','An toàn thông tin',6,98000,'enable'),(2,'Cơ Sở Dữ Liệu',2020,'TS. Nguyễn Văn Hiếu','Công nghệ thông tin',7,95000,'enable'),(3,'Kỹ Thuật Truyền Số Liệu',2019,'PGS.TS. Trần Văn Lộc','Viễn thông',4,87000,'enable'),(4,'Mạng Máy Tính',2022,'TS. Nguyễn Hồng Quang','CNTT - Viễn thông',8,102000,'enable'),(5,'Nguyên Lý Hệ Điều Hành',2021,'PGS.TS. Nguyễn Văn Bình','Công nghệ thông tin',7,99000,'enable'),(6,'Lập Trình Hướng Đối Tượng Với Java',2020,'TS. Trần Đức Hiếu','Công nghệ phần mềm',9,105000,'enable'),(7,'Kinh Tế Số',2022,'TS. Phạm Minh Tuấn','Kinh tế số',4,113000,'enable'),(8,'Điện Tử Số Cơ Bản',2018,'PGS.TS. Lê Văn Thắng','Điện tử viễn thông',5,94000,'enable'),(9,'Trí Tuệ Nhân Tạo',2023,'TS. Nguyễn Thị Hồng Vân','Trí tuệ nhân tạo',6,119000,'enable'),(10,'Phân Tích Và Thiết Kế Hệ Thống Thông Tin',2020,'TS. Trần Quốc Anh','Hệ thống thông tin',7,99000,'enable'),(11,'Kinh Tế Vi Mô Cơ Bản',2020,'TS. Nguyễn Văn Phúc','Kinh tế học',6,89000,'enable'),(12,'Kinh Tế Vĩ Mô',2021,'PGS.TS. Trần Thị Lan Hương','Kinh tế học',5,92000,'enable'),(13,'Nguyên Lý Marketing',2022,'TS. Lê Quốc Hưng','Marketing',7,97000,'enable'),(14,'Tài Chính Doanh Nghiệp',2019,'PGS.TS. Nguyễn Thị Mai','Tài chính',4,99000,'enable'),(15,'Quản Trị Chiến Lược',2021,'TS. Phạm Anh Dũng','Quản trị kinh doanh',6,101000,'enable'),(20,'binh1',2000,'binhe','binh',3,100000000,'enable');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
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
