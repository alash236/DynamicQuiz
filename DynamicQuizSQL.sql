CREATE DATABASE  IF NOT EXISTS `quiz15` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quiz15`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: quiz15
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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `question_id` varchar(45) NOT NULL,
  `quiz_id` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `answeroption` varchar(300) NOT NULL,
  PRIMARY KEY (`question_id`,`quiz_id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES ('1',1,'12345678@gmail.com','[\"看情況，如果有認識的朋友我會去\"]'),('1',1,'test123@gmail.com','[\"好期待！我喜歡與人互動\"]'),('1',1,'test1234@gmail.com','[\"還是待在家比較舒服\"]'),('1',1,'test12345@gmail.com','[\"好期待！我喜歡與人互動\"]'),('1',1,'test123456@gmail.com','[\"看情況，如果有認識的朋友我會去\"]'),('1',2,'12345678@gmail.com','[\"朋友或家人的建議\"]'),('1',2,'test123@gmail.com','[\"朋友或家人的建議\"]'),('1',2,'test1234@gmail.com','[\"朋友或家人的建議\"]'),('1',2,'test12345@gmail.com','[\"感覺與直覺\"]'),('1',2,'test123456@gmail.com','[\"資料與邏輯分析\"]'),('1',3,'12345678@gmail.com','[\"實行者與細節控\"]'),('1',3,'test123@gmail.com','[\"協調者與傾聽者\"]'),('1',3,'test1234@gmail.com','[\"領導者或發言人\"]'),('1',3,'test12345@gmail.com','[\"協調者與傾聽者\"]'),('1',3,'test123456@gmail.com','[\"實行者與細節控\"]'),('14',1,'test123@gmail.com','[\"3\"]'),('14',2,'test123@gmail.com','[\"4\"]'),('14',3,'test123@gmail.com','[\"3\"]'),('14',4,'test123@gmail.com','[\"4\"]'),('14',5,'test123@gmail.com','[\"AA\"]'),('9',1,'test1@gmail.com','[\"在家休息\"]'),('9',1,'test12@gmail.com','[\"追劇/玩遊戲\"]'),('9',1,'test1234@gmail.com','[\"在家休息\"]'),('9',2,'test1@gmail.com','[\"看心情\"]'),('9',2,'test12@gmail.com','[\"和朋友/家人\"]'),('9',2,'test1234@gmail.com','[\"和朋友/家人\"]'),('9',3,'test1@gmail.com','[\"網購/逛街\",\"戶外運動\"]'),('9',3,'test12@gmail.com','[\"聚餐/與朋友見面\",\"看書/聽音樂\"]'),('9',3,'test1234@gmail.com','[\"聚餐/與朋友見面\",\"戶外運動\"]'),('9',4,'test1@gmail.com','[\"進修/學新技能\"]'),('9',4,'test12@gmail.com','[\"出國旅遊\",\"進修/學新技能\"]'),('9',4,'test1234@gmail.com','[\"進修/學新技能\"]'),('9',5,'test1@gmail.com','[\"111\"]'),('9',5,'test12@gmail.com','[\"123\"]'),('9',5,'test1234@gmail.com','[\"出門玩\"]');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `question_id` int NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `writetime` datetime NOT NULL,
  PRIMARY KEY (`question_id`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'mike','12345678@gmail.com','2025-08-29 09:03:02'),(1,'小黑','test123@gmail.com','2025-08-28 15:07:03'),(1,'alash236','test1234@gmail.com','2025-08-29 09:51:29'),(1,'chubby','test12345@gmail.com','2025-09-03 15:03:18'),(1,'alan','test123456@gmail.com','2025-08-29 09:52:27'),(9,'user','test1@gmail.com','2025-09-05 11:36:40'),(9,'test12','test12@gmail.com','2025-09-05 09:04:45'),(9,'alash236','test1234@gmail.com','2025-09-04 14:40:29'),(14,'小黑','test123@gmail.com','2025-10-28 10:28:43');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `text` varchar(300) NOT NULL,
  `start_Time` date NOT NULL,
  `end_Time` date NOT NULL,
  `publish` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'探索自我性格','我們每個人都有獨特的性格，有些人外向，有些人內向；有些人冷靜理性，有些人感性浪漫。透過以下問題來了解你是哪一種人吧！','2025-08-28','2025-09-04',1),(5,'居家環境與生活舒適度調查1','為了提升您的居住品質，我們想了解您對居家環境的看法與需求。請您誠實填寫以下問卷，謝謝您的參與！','2025-08-29','2025-09-11',1),(9,'假日活動偏好調查','您好！為了了解大家在假日的活動習慣與偏好，我們設計了這份小問卷。\n內容包含單選、多選與文字題，填寫時間大約 2～3 分鐘。\n您的回覆將有助於我們分析不同人對假日活動的喜好，並作為未來活動設計的參考。\n本問卷僅供研究使用，請放心作答。','2025-09-04','2025-09-09',1),(14,'測試問卷','測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷','2025-10-28','2025-11-01',1),(15,'測試問卷2','測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷','2025-10-28','2025-11-04',0),(16,'測試問卷3','測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷測試問卷','2025-10-30','2025-11-04',1);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz` (
  `question_id` int NOT NULL,
  `quiz_id` int NOT NULL,
  `name` varchar(30) NOT NULL,
  `type` varchar(10) NOT NULL DEFAULT 'single',
  `is_required` tinyint NOT NULL DEFAULT '0',
  `option` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`question_id`,`quiz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (1,1,'面對一場聚會，你的第一反應是？','single',0,'[\"好期待！我喜歡與人互動\",\"看情況，如果有認識的朋友我會去\",\"還是待在家比較舒服\"]'),(1,2,'當你做決定時，通常依據的是？','single',0,'[\"感覺與直覺\",\"朋友或家人的建議\",\"資料與邏輯分析\"]'),(1,3,'你在團隊中通常扮演什麼角色？','single',0,'[\"領導者或發言人\",\"協調者與傾聽者\",\"實行者與細節控\"]'),(5,1,'您目前的居住型態是？','single',0,'[\"公寓\",\"透天厝\",\"大樓\",\"其他\"]'),(5,2,'您對目前居住環境的整潔程度滿意嗎？','single',1,'[\"非常滿意\",\"滿意\",\"普通\",\"不滿意\",\"非常不滿意\"]'),(5,3,'您最希望改善居家環境的哪一方面？','text',0,'[]'),(5,4,'您家中平均一天的室內溫度大約是多少度？','single',0,'[\"18度以下\",\"18~22度\",\"22~26度\",\"26度以上\"]'),(5,5,'您平時喜歡在家中做哪些活動？','multiple',0,'[\"閱讀\",\"看電視或影片\",\"烹飪\",\"運動\",\"其他\"]'),(5,6,'1','single',0,'[\"2\",\"3\"]'),(9,1,'假日你最常選擇的主要活動是什麼？','single',0,'[\"在家休息\",\"出門旅遊\",\"運動健身\",\"追劇/玩遊戲\"]'),(9,2,'你比較喜歡一個人還是和別人一起度過假日？','single',0,'[\"一個人\",\"和朋友/家人\",\"看心情\"]'),(9,3,'以下哪些活動是你假日常做的？（可複選）','multiple',0,'[\"看書/聽音樂\",\"聚餐/與朋友見面\",\"戶外運動\",\"網購/逛街\",\"打掃整理\"]'),(9,4,'如果有三天連假，你會考慮做哪些事情？（可複選）','multiple',0,'[\"國內旅遊\",\"出國旅遊\",\"待在家裡休息\",\"進修/學新技能\",\"安排社交聚會\"]'),(9,5,'如果可以規劃一個理想的假日，你會怎麼安排？','text',0,'[]'),(14,1,'測試問卷1','single',0,'[\"1\",\"2\",\"3\",\"4\"]'),(14,2,'測試問卷2','single',1,'[\"1\",\"2\",\"3\",\"4\"]'),(14,3,'測試問卷3','multiple',0,'[\"1\",\"2\",\"3\",\"4\"]'),(14,4,'測試問卷4','multiple',1,'[\"1\",\"2\",\"3\",\"4\"]'),(14,5,'測試問卷5','text',0,'[]'),(15,1,'測試問卷1','single',0,'[\"1\",\"2\",\"3\",\"4\"]'),(15,2,'測試問卷2','multiple',0,'[\"1\",\"2\",\"3\",\"4\",\"5\"]'),(15,3,'測試問卷3','text',0,'[]'),(16,1,'測試問卷1','text',0,'[]'),(16,2,'測試問卷2','single',0,'[\"1\",\"2\",\"3\",\"4\"]');
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `name` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `age` int DEFAULT '0',
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('aa','0912345678','123@gmail.com',17,'$2a$10$xqR5JY9yG.G9BklpR7OWlexjNsMvaAlVyNO9sNTLOe5624YoYPHi2'),('mike','0912345678','12345678@gmail.com',120,'$2a$10$qgJlcJPwFYclnXBMB32vkurj/ESkpjteZ/0336elBW1aWvv.Qx9Mu'),('user','0913257001','test1@gmail.com',18,'$2a$10$cD0SHabmC/cQIYC7o5GqeO9h3WAIC2BKWrdJjEMSRFHQTGYXIXQkK'),('test12','0917845712','test12@gmail.com',17,'$2a$10$29q8kvY2HMJ3qoQBlwIA8.9HeV7z/Iqz1vSFrHEWnjpzz/DLVhxtC'),('小黑','0972795378','test123@gmail.com',0,'$2a$10$0D.iLXAa5Kob2V2q3Pi5.uqrvi1x8vqi8W2YNNAwtCcN/3gZbz8qC'),('alash236','0913257948','test1234@gmail.com',18,'$2a$10$8V.00xaE80UER8l4fSHIO.alVvNzNUSPDt6/alL3Wfse171.5XEuO'),('chubby','0972795388','test12345@gmail.com',24,'$2a$10$WvxpVYOeLoDcpw8djLgx/eUWICSHhOlpeEQeKT.BhiBTHtC0YOZ8W'),('alan','0961459824','test123456@gmail.com',60,'$2a$10$Cg7qdjFoNzrO8Tpxpu1vuOj8s0uPAflzJ2Pc15Pqmu2lezl73iW5y'),('小明','0913257001','user1234@gmail.com',17,'$2a$10$HmqSeUZe3Bn17fWyp3JxAOYKvilLG9z0zMOElcYXXaFf4m4mrkO/S');
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

-- Dump completed on 2025-10-28 10:30:12
