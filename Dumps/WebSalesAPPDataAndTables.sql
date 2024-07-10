-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: theenglishcut
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `category_entity`
--

DROP TABLE IF EXISTS `category_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_entity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_entity`
--

LOCK TABLES `category_entity` WRITE;
/*!40000 ALTER TABLE `category_entity` DISABLE KEYS */;
INSERT INTO `category_entity` VALUES (1,'COMIDA',NULL),(2,'MUEBLES',NULL),(3,'ROPA',NULL);
/*!40000 ALTER TABLE `category_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_entity`
--

DROP TABLE IF EXISTS `order_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_entity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `entrega` varchar(255) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `usuario` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqmi76lvbwkiod3irjy7d0ssr2` (`usuario`),
  CONSTRAINT `FKqmi76lvbwkiod3irjy7d0ssr2` FOREIGN KEY (`usuario`) REFERENCES `user_entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_entity`
--

LOCK TABLES `order_entity` WRITE;
/*!40000 ALTER TABLE `order_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_entity`
--

DROP TABLE IF EXISTS `product_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_entity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `inventario` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7sjsetugpgd6cfl3jfy21bymo` (`inventario`),
  CONSTRAINT `FK7sjsetugpgd6cfl3jfy21bymo` FOREIGN KEY (`inventario`) REFERENCES `stock_entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_entity`
--

LOCK TABLES `product_entity` WRITE;
/*!40000 ALTER TABLE `product_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_to_category_entity`
--

DROP TABLE IF EXISTS `product_to_category_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_to_category_entity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp2ja9ophdh949rigonbq9kjkd` (`category_id`),
  KEY `FKiwmsithhfera1kbhn73qkq4up` (`product_id`),
  CONSTRAINT `FKiwmsithhfera1kbhn73qkq4up` FOREIGN KEY (`product_id`) REFERENCES `product_entity` (`id`),
  CONSTRAINT `FKp2ja9ophdh949rigonbq9kjkd` FOREIGN KEY (`category_id`) REFERENCES `category_entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_to_category_entity`
--

LOCK TABLES `product_to_category_entity` WRITE;
/*!40000 ALTER TABLE `product_to_category_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_to_category_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_to_order_entity`
--

DROP TABLE IF EXISTS `product_to_order_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_to_order_entity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cantidad` int DEFAULT NULL,
  `pedido` int DEFAULT NULL,
  `producto` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKstmwawpotevb1oe239748sv1g` (`pedido`),
  KEY `FK72b77y5u4a76nw9u64arg262n` (`producto`),
  CONSTRAINT `FK72b77y5u4a76nw9u64arg262n` FOREIGN KEY (`producto`) REFERENCES `product_entity` (`id`),
  CONSTRAINT `FKstmwawpotevb1oe239748sv1g` FOREIGN KEY (`pedido`) REFERENCES `order_entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_to_order_entity`
--

LOCK TABLES `product_to_order_entity` WRITE;
/*!40000 ALTER TABLE `product_to_order_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_to_order_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_entity`
--

DROP TABLE IF EXISTS `rol_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol_entity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_entity`
--

LOCK TABLES `rol_entity` WRITE;
/*!40000 ALTER TABLE `rol_entity` DISABLE KEYS */;
INSERT INTO `rol_entity` VALUES (1,'Admin'),(2,'User'),(3,'Employer');
/*!40000 ALTER TABLE `rol_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_entity`
--

DROP TABLE IF EXISTS `stock_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_entity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_entity`
--

LOCK TABLES `stock_entity` WRITE;
/*!40000 ALTER TABLE `stock_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_entity`
--

DROP TABLE IF EXISTS `user_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_entity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rol` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbcr54llgtv8e6f6ei9tvjvyh2` (`rol`),
  CONSTRAINT `FKbcr54llgtv8e6f6ei9tvjvyh2` FOREIGN KEY (`rol`) REFERENCES `rol_entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_entity`
--

LOCK TABLES `user_entity` WRITE;
/*!40000 ALTER TABLE `user_entity` DISABLE KEYS */;
INSERT INTO `user_entity` VALUES (1,'Antonio','123',1),(2,'Pedro','123',2);
/*!40000 ALTER TABLE `user_entity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-09 13:59:31
