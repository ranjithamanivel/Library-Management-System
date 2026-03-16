-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: library_db
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'James');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `available_copies` int NOT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `total_copies` int NOT NULL,
  `author_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKklnrv3weler2ftkweewlky958` (`author_id`),
  CONSTRAINT `FKklnrv3weler2ftkweewlky958` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (3,3,'4576','Java Programming',5,NULL),(5,5,'3576','Python',10,NULL),(6,5,'3576','Python',10,NULL),(9,5,'2004','HTML',11,NULL),(13,5,'3576','Python Programming',10,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowing_transaction`
--

DROP TABLE IF EXISTS `borrowing_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowing_transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `borrow_date` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `book_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb2iixcpw2l81jo65jtsy191tg` (`book_id`),
  KEY `FKrebue97nop2ycbfj65to4amq3` (`user_id`),
  CONSTRAINT `FKb2iixcpw2l81jo65jtsy191tg` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FKrebue97nop2ycbfj65to4amq3` FOREIGN KEY (`user_id`) REFERENCES `library_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowing_transaction`
--

LOCK TABLES `borrowing_transaction` WRITE;
/*!40000 ALTER TABLE `borrowing_transaction` DISABLE KEYS */;
INSERT INTO `borrowing_transaction` VALUES (1,'2025-10-16','2025-10-30',NULL,3,1),(2,'2025-10-16','2025-10-30','2025-10-16',3,1),(3,'2025-10-18','2025-11-01','2025-10-18',5,1);
/*!40000 ALTER TABLE `borrowing_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library_user`
--

DROP TABLE IF EXISTS `library_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `library_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library_user`
--

LOCK TABLES `library_user` WRITE;
/*!40000 ALTER TABLE `library_user` DISABLE KEYS */;
INSERT INTO `library_user` VALUES (1,'ranjithamanivel2006@gmail.com','Ranjitha','$2a$10$r7ejwmZSlfwy5VufghgLgO0DW9STMauJrTE5EkiE4.EXDNM.iAnYS','ADMIN'),(2,'dhanam12@gmail.com','Dhanam','$2a$10$AxPtMtCX9ZNGFbn2943xuulnU6i5LEP00L25wWrjDbHTR9tYAGgUa','USER'),(5,'student1@gmail.com','Student1','$2a$10$QcszHJvfib2WTxPirQZ6AeIz3wLSHl6J39tYC4ircCeYAOaoXR9k6','USER'),(6,'venki1@gmail.com','Venki1','$2a$10$ZBQaNvAguBs8PKan8i1e8.h6PbKzllZZOze1eVR3Imv5VVNOxRsMe','USER'),(7,'hari1@gmail.com','Hari1','$2a$10$/2xDmWU64SUy7MRO1BWl1O/P1.pmANzAro64P/JTsw4mi1o.jsAaC','ADMIN'),(8,'venki2@gmail.com','Venki2','$2a$10$s/sjUFcxdnlgONsHLVua7.uMEHZx/s9obBEcxxGurB/M5XJO/VyOW','USER'),(9,'venki3@gmail.com','Venki3','$2a$10$OE0wXMqcpBPUo2Z4XT5R8u7rNI0jwrRqDZVnACYpcU3sgIMqbSA7i','ADMIN'),(10,'admin@gmail.com','Admin User','$2a$10$MKDBvIRkF.W/Qfgzl59Ube3bWx/x9HNGblkuaHRCS1auh6rh/HI6y','ADMIN');
/*!40000 ALTER TABLE `library_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-16 21:15:40
