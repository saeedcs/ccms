-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cms
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `article_body` text,
  `article_title` varchar(255) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `changed_by` varchar(100) DEFAULT NULL,
  `changed_on` datetime(6) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `excerpt` text,
  `main_page_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` (`id`, `article_body`, `article_title`, `author`, `changed_by`, `changed_on`, `created_by`, `created_on`, `excerpt`, `main_page_img`) VALUES (1,'Sample Article Body','Sample Title','Saeed',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_jsp`
--

DROP TABLE IF EXISTS `article_jsp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_jsp` (
  `id` int(11) NOT NULL,
  `article_body` text,
  `article_title` varchar(255) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `changed_by` varchar(100) DEFAULT NULL,
  `changed_on` datetime(6) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `excerpt` text,
  `main_page_img` varchar(255) DEFAULT NULL,
  `seo_uri` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_jsp`
--

LOCK TABLES `article_jsp` WRITE;
/*!40000 ALTER TABLE `article_jsp` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_jsp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `cat_name` varchar(100) DEFAULT NULL,
  `changed_by` varchar(100) DEFAULT NULL,
  `changed_on` datetime(6) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `show_on_main_page` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `changed_by` varchar(100) DEFAULT NULL,
  `changed_on` datetime(6) DEFAULT NULL,
  `comment_text` text,
  `created_by` varchar(100) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp97oxc8v4wd4ay9hwelxuo8ce` (`article_id`),
  CONSTRAINT `FKnwlc4xv9fm2swgesuas3uf3mo` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `FKp97oxc8v4wd4ay9hwelxuo8ce` FOREIGN KEY (`article_id`) REFERENCES `article_jsp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forget_password`
--

DROP TABLE IF EXISTS `forget_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forget_password` (
  `id` bigint(20) NOT NULL,
  `changed_by` varchar(100) DEFAULT NULL,
  `changed_on` datetime(6) DEFAULT NULL,
  `code` varchar(155) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `used` bit(1) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forget_password`
--

LOCK TABLES `forget_password` WRITE;
/*!40000 ALTER TABLE `forget_password` DISABLE KEYS */;
INSERT INTO `forget_password` (`id`, `changed_by`, `changed_on`, `code`, `created_by`, `created_on`, `used`, `username`) VALUES (26,NULL,NULL,'ZVUIprOMZZ',NULL,'2020-07-25 06:11:47.711000',_binary '\0','admin@q.com'),(27,NULL,NULL,'1ChNbzNAe7',NULL,'2020-07-25 06:49:56.125000',_binary '','admin@q.com'),(28,NULL,NULL,'TGtxnjNwP0',NULL,'2020-07-25 07:26:04.156000',_binary '\0','admin@q.com');
/*!40000 ALTER TABLE `forget_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fruit`
--

DROP TABLE IF EXISTS `fruit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fruit` (
  `id` int(11) NOT NULL,
  `changed_by` varchar(100) DEFAULT NULL,
  `changed_on` datetime(6) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `purchase_date_time` datetime(6) DEFAULT NULL,
  `taste` varchar(15) DEFAULT NULL,
  `page_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpe9012ng3131ynycawwci03a5` (`page_id`),
  CONSTRAINT `FKpe9012ng3131ynycawwci03a5` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fruit`
--

LOCK TABLES `fruit` WRITE;
/*!40000 ALTER TABLE `fruit` DISABLE KEYS */;
/*!40000 ALTER TABLE `fruit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (29);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `num_of_rooms` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `page` (
  `id` int(11) NOT NULL,
  `author` varchar(100) DEFAULT NULL,
  `changed_by` varchar(100) DEFAULT NULL,
  `changed_on` datetime(6) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `page_body` text,
  `page_title` varchar(255) DEFAULT NULL,
  `seo_uri` varchar(255) DEFAULT NULL,
  `show_on_main_page` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page`
--

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
INSERT INTO `page` (`id`, `author`, `changed_by`, `changed_on`, `created_by`, `created_on`, `page_body`, `page_title`, `seo_uri`, `show_on_main_page`) VALUES (1,'Saeed Iqbal',NULL,NULL,NULL,NULL,'This is page body','Title of Page','title-of-page',_binary '');
/*!40000 ALTER TABLE `page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`) VALUES (11,'ROLE_ADMIN'),(12,'ROLE_USER'),(22,'ROLE_MEMBER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todo`
--

DROP TABLE IF EXISTS `todo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `todo` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_done` bit(1) NOT NULL,
  `target_date` datetime(6) DEFAULT NULL,
  `user` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo`
--

LOCK TABLES `todo` WRITE;
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(155) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `enabled`, `password`, `username`) VALUES (10,_binary '','$2a$10$NUL4Ot7BbBm9pYd/ierrreCijUuC78WZP3UKZTsySWVyeMMZk/Qiq','admin'),(21,_binary '','111111','admin@djd.com'),(23,_binary '','111111','admin@qqq.com'),(25,_binary '','111111','admin@q.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (10,11),(10,12),(21,22),(23,22),(25,22);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cms'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-25 18:55:37
