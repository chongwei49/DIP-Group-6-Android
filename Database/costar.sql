/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- database creation with name `Co-Star`
create database if not exists costar;
use costar;

--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `dob` date NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`name`, `email`, `username`,`password`, `dob`) 
					VALUES ('Mary Jones','mary.jones@gmail.com','maryJ','maryJones', '1999-09-24'),
						   ('Kenneth Wong', 'kenneth@yahoo.com', 'Kenneth', 'kennethW', '2003-01-17');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `priId` int(11) NOT NULL,
  `qnId` int(11) NOT NULL,
  `qnCategory` varchar(128) NOT NULL,
  `qns` varchar(255) NOT NULL,
  `answer` BOOLEAN NOT NULL,
  `traits` varchar(128) NOT NULL,
  PRIMARY KEY (`priId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qnId`
--

-- LOCK TABLES `questions` WRITE;
-- /*!40000 ALTER TABLE `qnId` DISABLE KEYS */;
-- INSERT INTO `questions` (`qnId`, `qnCategory`, `qns`, `answer`, `traits`) 
-- 					VALUES (1, 'love', 'hi', 'yes', 'intro');
-- /*!40000 ALTER TABLE `questions` ENABLE KEYS */;
-- UNLOCK TABLES;
-- /*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Table structure for table `compatibility`
--

DROP TABLE IF EXISTS `compatibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compatibility` (
  `priId` int(11) NOT NULL AUTO_INCREMENT,
  `compatibilityId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `qnCategory` varchar(128) NOT NULL,
  `qnId` int(11) NOT NULL,
  `qns` varchar(255) NOT NULL,
  `answer` BOOLEAN NOT NULL,
  `responded` BOOLEAN DEFAULT 0 NOT NULL,
  PRIMARY KEY (`priId`),
  KEY `UserId_idx` (`userId`),
  CONSTRAINT `UserIdCompatibility` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compatibility`
--

LOCK TABLES `compatibility` WRITE;
/*!40000 ALTER TABLE `compatibility` DISABLE KEYS */;
INSERT INTO `compatibility` (`compatibilityId`, `userId`, `qnCategory`, `qnId`, `qns`, `answer`, `responded`) 
					VALUES (1,1,'16Personalities', '11', 'Are you a traditional person?', '1', '1'),
						   (1,2,'16Personalities', '11', 'Are you a traditional person?', '0', '1');
/*!40000 ALTER TABLE `compatibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personality`
--

DROP TABLE IF EXISTS `personality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personality` (
  `priId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `qnCategory` varchar(128) NOT NULL,
  `personalityType` varchar(128) NOT NULL,
  PRIMARY KEY (`priId`),
  KEY `UserId_` (`userId`),
  CONSTRAINT `UserIdpersonaility` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personality`
--

LOCK TABLES `personality` WRITE;
/*!40000 ALTER TABLE `personality` DISABLE KEYS */;
INSERT INTO `personality` (`userId`, `qnCategory`, `personalityType`) 
					VALUES (1,'16Personality','INFJ'),
                           (2,'16Personality','ENTP');
/*!40000 ALTER TABLE `personality` ENABLE KEYS */;
UNLOCK TABLES;
