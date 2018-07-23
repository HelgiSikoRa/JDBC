CREATE DATABASE  IF NOT EXISTS `sample` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `sample`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: sample
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `department` (
  `dept_no` varchar(20) NOT NULL,
  `dept_name` varchar(45) DEFAULT NULL,
  `location` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`dept_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('d1','research','Dallas'),('d2','accounting','Seattle'),('d3','marketing','Dallas');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `emp_no` int(11) NOT NULL,
  `emp_fname` varchar(45) NOT NULL,
  `emp_lname` varchar(60) NOT NULL,
  `dept_no` varchar(20) DEFAULT NULL,
  `emp_adress` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`emp_no`),
  KEY `dept_no` (`dept_no`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dept_no`) REFERENCES `department` (`dept_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (2581,'Elke','Hansel','d2','New York'),(9031,'Elsa','Bertoni','d2','Seattle'),(10102,'Ann','Jones','d3','Dallas'),(18316,'John','Barrimore','d1','Dallas'),(25348,'Matthew','Smith','d3','New York'),(28559,'Sybill','Moser','d1','Seattle'),(29346,'James','James','d2','Dallas');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `project` (
  `project_no` varchar(20) NOT NULL,
  `project_name` varchar(45) NOT NULL,
  `budget` double DEFAULT NULL,
  PRIMARY KEY (`project_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('p1','Apollo',120000),('p2','Gemini',95000),('p3','Mercury',186500);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `works_on`
--

DROP TABLE IF EXISTS `works_on`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `works_on` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `emp_no` int(11) DEFAULT NULL,
  `project_no` varchar(45) DEFAULT NULL,
  `job` varchar(60) DEFAULT NULL,
  `enter_data` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `emp_no` (`emp_no`),
  KEY `project_no` (`project_no`),
  CONSTRAINT `works_on_ibfk_1` FOREIGN KEY (`emp_no`) REFERENCES `employee` (`emp_no`),
  CONSTRAINT `works_on_ibfk_2` FOREIGN KEY (`project_no`) REFERENCES `project` (`project_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `works_on`
--

LOCK TABLES `works_on` WRITE;
/*!40000 ALTER TABLE `works_on` DISABLE KEYS */;
INSERT INTO `works_on` VALUES (1,10102,'p1','analyst','2006-10-01'),(2,10102,'p3','manager','2008-01-01'),(3,25348,'p2','clerk','2007-02-15'),(4,18316,'p2',NULL,'2007-06-01'),(5,29346,'p2',NULL,'2006-12-15'),(6,2581,'p3','analyst','2007-10-15'),(7,9031,'p1','manager','2007-04-15'),(8,28559,'p1',NULL,'2007-08-01'),(9,28559,'p2','clerk','2008-02-01'),(10,9031,'p3','clerk','2006-11-15'),(12,29346,'p1','clerk','2007-01-04');
/*!40000 ALTER TABLE `works_on` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-20  1:03:53
