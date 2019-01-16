CREATE TABLE `pre_user_course` (
  `user_id` bigint(20) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  KEY `FK21vbdq47kryvf1xupy933jym9` (`course_id`),
  KEY `FKn76vtodi0hbxct0go78dyq4nu` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `prs_author` (
  `id` bigint(20) NOT NULL,
  `create_datetime` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `prs_author` VALUES (1,'','1@gmail.com','8195566645','1','Joshua',NULL),(2,NULL,'2@gmail.com','8476521456','1','James Gosling',NULL),(3,NULL,'3@gmail.com','8456931236','1','Kathy',NULL),(4,NULL,'4@gmail.com','8456931236','2','Jon',NULL);

CREATE TABLE `prs_category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `prs_category` VALUES (12,'Java'),(11,'JavaScript'),(13,'PHP'),(14,'C'),(21,'ORACLE'),(22,'MYSQL');

CREATE TABLE `prs_course` (
  `id` bigint(20) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `length` bigint(20) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp9retp6hybmgnt2xjb04meyx4` (`author_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `prs_metric` (
  `id` bigint(20) NOT NULL,
  `metric_name` varchar(255) DEFAULT NULL,
  `metric_weight` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `prs_recommend` (
  `course_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `score` bigint(20) NOT NULL,
  `selected` char(1) NOT NULL,
  `sort` bigint(20) NOT NULL,
  PRIMARY KEY (`course_id`,`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



CREATE TABLE `prs_user` (
  `id` bigint(20) NOT NULL,
  `create_datetime` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `prs_user_course` (
  `course_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `select_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;