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


-- database creation with name `Soma`
create database if not exists soma;
use soma;

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
  `profilePic` BLOB NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`name`, `email`, `username`,`password`, `dob`, `profilePic`) 
					VALUES ('Mary Jones','mary.jones@gmail.com','maryJ','maryJones', '1999-09-24', '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'),
						   ('Kenneth Wong', 'kenneth@yahoo.com', 'Kenneth', 'kennethW', '2003-01-17', '');
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

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO questions (`priId`, `qnId`, `qnCategory`, `qns`, `answer`, `traits`) VALUES
	(1, 1, '16Personalities', 'I dislike small talk, but I enjoy talking in-depth about topics that matter to me.', 1, 'Introvert'),
	(2, 1, '16Personalities', 'I dislike small talk, but I enjoy talking in-depth about topics that matter to me.', 0, 'Extrovert'),
	(3, 2, '16Personalities', 'You regularly make new friends.', 1, 'Extrovert'),
	(4, 2, '16Personalities', 'You regularly make new friends.', 0, 'Introvert'),
	(5, 3, '16Personalities', 'At social events, you rarely try to introduce yourself to new people and mostly talk to the ones you already know.', 1, 'Introvert'),
	(6, 3, '16Personalities', 'At social events, you rarely try to introduce yourself to new people and mostly talk to the ones you already know.', 0, 'Extrovert'),
	(7, 4, '16Personalities', 'I prefer to celebrate my birthday with a big party and invite lots of friends', 1, 'Extrovert'),
	(8, 4, '16Personalities', 'I prefer to celebrate my birthday with a big party and invite lots of friends', 0, 'Introvert'),
	(9, 5, '16Personalities', 'I prefer one-on-one conversations to group activities.', 1, 'Introvert'),
	(10, 5, '16Personalities', 'I prefer one-on-one conversations to group activities.', 0, 'Extrovert'),
	(11, 6, '16Personalities', 'Are you more spontaneous than certain?', 1, 'Prospecting'),
	(12, 6, '16Personalities', 'Are you more spontaneous than certain?', 0, 'Judging'),
	(13, 7, '16Personalities', 'Are you the type to always keep their options open?', 1, 'Prospecting'),
	(14, 7, '16Personalities', 'Are you the type to always keep their options open?', 0, 'Judging'),
	(15, 8, '16Personalities', 'Are you comfortable facing unexpected challenges?', 1, 'Prospecting'),
	(16, 8, '16Personalities', 'Are you comfortable facing unexpected challenges?', 0, 'Judging'),
	(17, 9, '16Personalities', 'When having a conversation, do you prefer multiple topics to just a few?', 1, 'Prospecting'),
	(18, 9, '16Personalities', 'When having a conversation, do you prefer multiple topics to just a few?', 0, 'Judging'),
	(19, 10, '16Personalities', 'Are you more of a "stick with the plan" person than "go with the flow" person?', 1, 'Prospecting'),
	(20, 10, '16Personalities', 'Are you more of a "stick with the plan" person than "go with the flow" person?', 0, 'Judging'),
	(21, 11, '16Personalities', 'Are you a traditional person?', 1, 'Observant'),
	(22, 11, '16Personalities', 'Are you a traditional person?', 0, 'Intuitive'),
	(23, 12, '16Personalities', 'In your head, are you MOSTLY living in the moment or in the future?', 1, 'Observant'),
	(24, 12, '16Personalities', 'In your head, are you MOSTLY living in the moment or in the future?', 0, 'Intuitive'),
	(25, 13, '16Personalities', 'When you think of a solution to any complex problem, would you rather be practical or creative?', 1, 'Observant'),
	(26, 13, '16Personalities', 'When you think of a solution to any complex problem, would you rather be practical or creative?', 0, 'Intuitive'),
	(27, 14, '16Personalities', 'Are you a literal person or do you consider yourself “metaphorical”?', 1, 'Observant'),
	(28, 14, '16Personalities', 'Are you a literal person or do you consider yourself “metaphorical”?', 0, 'Intuitive'),
	(29, 15, '16Personalities', 'You store the most random, non-essential facts among your friends?', 1, 'Observant'),
	(30, 15, '16Personalities', 'You store the most random, non-essential facts among your friends?', 0, 'Intuitive'),
	(31, 16, '16Personalities', 'I deem myself as vulnerable(eg. easily anxious, impulsive)?', 1, 'Turbulent'),
	(32, 16, '16Personalities', 'I deem myself as vulnerable(eg. easily anxious, impulsive)?', 0, 'Assertive'),
	(33, 17, '16Personalities', 'I am resistant to stress and even-tempered?', 1, 'Assertive'),
	(34, 17, '16Personalities', 'I am resistant to stress and even-tempered?', 0, 'Turbulent'),
	(35, 18, '16Personalities', 'I am decisive.', 1, 'Assertive'),
	(36, 18, '16Personalities', 'I am decisive.', 0, 'Turbulent'),
	(37, 19, '16Personalities', 'I do not care what other people think of me.', 1, 'Assertive'),
	(38, 19, '16Personalities', 'I do not care what other people think of me.', 0, 'Turbulent'),
	(39, 20, '16Personalities', 'I find myself needing to withdraw during busy days.', 1, 'Turbulent'),
	(40, 20, '16Personalities', 'I find myself needing to withdraw during busy days.', 0, 'Assertive'),
	(41, 21, '16Personalities', 'Seeing other people cry can easily make you feel like you want to cry too.', 1, 'Feeling'),
	(42, 21, '16Personalities', 'Seeing other people cry can easily make you feel like you want to cry too.', 0, 'Thinking'),
	(43, 22, '16Personalities', 'You are very sentimental', 1, 'Feeling'),
	(44, 22, '16Personalities', 'You are very sentimental', 0, 'Thinking'),
	(45, 23, '16Personalities', 'You will try to find solutions when your friends tell you their story about relationship', 1, 'Thinking'),
	(46, 23, '16Personalities', 'You will try to find solutions when your friends tell you their story about relationship', 0, 'Feeling'),
	(47, 24, '16Personalities', 'You are more inclined to follow your head than your heart.', 1, 'Thinking'),
	(48, 24, '16Personalities', 'You are more inclined to follow your head than your heart.', 0, 'Feeling'),
	(49, 25, '16Personalities', 'Your mood can change very quickly.', 1, 'Feeling'),
	(50, 25, '16Personalities', 'Your mood can change very quickly.', 0, 'Thinking'),
	(51, 1, 'LoveTest', 'I appreciate hugs or cuddles more than long and sweet messages', 1, 'Physical Touch'),
	(52, 1, 'LoveTest', 'I appreciate hugs or cuddles more than long and sweet messages', 0, 'Words of Affirmation'),
	(53, 2, 'LoveTest', 'I appreciate holding hands than being complimented by the person I like', 1, 'Physical Touch'),
	(54, 2, 'LoveTest', 'I appreciate holding hands than being complimented by the person I like', 0, 'Words of Affirmation'),
	(55, 3, 'LoveTest', 'I would rather stay at home and cuddle with my partner than sending motivational messages to my partner', 1, 'Physical Touch'),
	(56, 3, 'LoveTest', 'I would rather stay at home and cuddle with my partner than sending motivational messages to my partner', 0, 'Words of Affirmation'),
	(57, 4, 'LoveTest', 'I like putting my arms around my partner more than encouraging her verbally', 1, 'Physical Touch'),
	(58, 4, 'LoveTest', 'I like putting my arms around my partner more than encouraging her verbally', 0, 'Words of Affirmation'),
	(59, 5, 'LoveTest', 'I would rather go on a hike with my partner than helping him/her with his/her project', 1, 'Quality Time'),
	(60, 5, 'LoveTest', 'I would rather go on a hike with my partner than helping him/her with his/her project', 0, 'Act of Service'),
	(61, 6, 'LoveTest', 'I prefer having heart-to-heart talks with my partner to helping him/her for his/her exams', 1, 'Quality Time'),
	(62, 6, 'LoveTest', 'I prefer having heart-to-heart talks with my partner to helping him/her for his/her exams', 0, 'Act of Service'),
	(63, 7, 'LoveTest', 'I prefer being alone with my partner to helping him/her do the dishes', 1, 'Quality Time'),
	(64, 7, 'LoveTest', 'I prefer being alone with my partner to helping him/her do the dishes', 0, 'Act of Service'),
	(65, 8, 'LoveTest', 'I appreciate having dinner alone with my partner more than fetching him/her from work/school', 1, 'Quality Time'),
	(66, 8, 'LoveTest', 'I appreciate having dinner alone with my partner more than fetching him/her from work/school', 0, 'Act of Service'),
	(67, 9, 'LoveTest', 'I would rather hug my partner than spend alone time with my partner', 1, 'Physical Touch'),
	(68, 9, 'LoveTest', 'I would rather hug my partner than spend alone time with my partner', 0, 'Quality Time'),
	(69, 10, 'LoveTest', 'I would rather encourage my partner when my partner is stressed than help her out', 1, 'Words of Affirmation'),
	(70, 10, 'LoveTest', 'I would rather encourage my partner when my partner is stressed than help her out', 0, 'Act of Service'),
	(71, 1, 'Job', 'I prefer working alone than in groups.', 1, 'Outgoing'),
	(72, 1, 'Job', 'I prefer working alone than in groups.', 0, 'Asocial'),
	(73, 2, 'Job', 'I enjoy meeting clients.', 1, 'Outgoing'),
	(74, 2, 'Job', 'I enjoy meeting clients.', 0, 'Asocial'),
	(75, 3, 'Job', 'I prefer my supervisors to be honest regarding my performances.', 1, 'Idealistic'),
	(76, 3, 'Job', 'I prefer my supervisors to be honest regarding my performances.', 0, 'Realistic'),
	(77, 4, 'Job', 'I prefer to work in a traditional company than a start up.', 1, 'Idealistic'),
	(78, 4, 'Job', 'I prefer to work in a traditional company than a start up.', 0, 'Realistic'),
	(79, 5, 'Job', 'I enjoy attaining physical meetings and conference rather than virtual ones.', 1, 'Outgoing'),
	(80, 5, 'Job', 'I enjoy attaining physical meetings and conference rather than virtual ones.', 0, 'Asocial'),
	(81, 6, 'Job', 'I prefer my colleagues to not interfere with my personal life.', 1, 'Outgoing'),
	(82, 6, 'Job', 'I prefer my colleagues to not interfere with my personal life.', 0, 'Asocial'),
	(83, 7, 'Job', 'I prefer companies that allows me to share personal opinions and gives room for progression.', 1, 'Idealistic'),
	(84, 7, 'Job', 'I prefer companies that allows me to share personal opinions and gives room for progression.', 0, 'Realistic'),
	(85, 8, 'Job', 'I prefer working in a community rather than a hierarchical environment.', 1, 'Idealistic'),
	(86, 8, 'Job', 'I prefer working in a community rather than a hierarchical environment.', 0, 'Realistic'),
	(87, 9, 'Job', 'I think everyone is equal without having my boss to tell me so.', 1, 'Idealistic'),
	(88, 9, 'Job', 'I think everyone is equal without having my boss to tell me so.', 0, 'Outgoing'),
	(89, 10, 'Job', 'I do not like my boss to point out my flaws infront of everyone.', 1, 'Asocial'),
	(90, 10, 'Job', 'I do not like my boss to point out my flaws infront of everyone.', 0, 'Realistic');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

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
  `dateTime` DATETIME NOT NULL,
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
INSERT INTO `personality` (`userId`, `qnCategory`, `personalityType`, `dateTime`) 
					VALUES (1,'16Personality','INFJ', NOW()),
                           (2,'16Personality','ENTP', NOW());
/*!40000 ALTER TABLE `personality` ENABLE KEYS */;
UNLOCK TABLES;
