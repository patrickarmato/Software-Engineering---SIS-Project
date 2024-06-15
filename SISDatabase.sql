CREATE DATABASE  IF NOT EXISTS `Student Information System` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `Student Information System`;
-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: localhost    Database: Student Information System
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Dumping events for database 'Student Information System'

-- Student table
CREATE TABLE `Student Information System`.`Student` (
  `idStudent` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `dateOfBirth` DATE,
  `gender` ENUM('Male', 'Female', 'Other'),
  `email` VARCHAR(100),
  `phoneNumber` VARCHAR(20),
  `address` VARCHAR(255),
  `city` VARCHAR(50),
  `state` VARCHAR(50),
  `zipCode` VARCHAR(10),
  PRIMARY KEY (`idStudent`)
);
-- courses table
CREATE TABLE `Student Information System`.`courses` (
  `idcourses` INT NOT NULL AUTO_INCREMENT,
  `courseName` VARCHAR(100) NOT NULL,
  `courseDescription` TEXT,
  `courseCredit` INT,
  PRIMARY KEY (`idcourses`)
);
-- enrollments table
CREATE TABLE `Student Information System`.`enrollments` (
  `idenrollments` INT NOT NULL AUTO_INCREMENT,
  `student_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  `enrollment_date` DATE,
  PRIMARY KEY (`idenrollments`),
  FOREIGN KEY (`student_id`) REFERENCES `Student Information System`.`Student`(`idStudent`),
  FOREIGN KEY (`course_id`) REFERENCES `Student Information System`.`courses`(`idcourses`)
);




--
-- Dumping routines for database 'Student Information System'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-15  9:21:19
