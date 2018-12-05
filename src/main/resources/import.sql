USE `jcala_blog`;

DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS `view_tag`;
DROP TABLE IF EXISTS `blog_view`;
DROP TABLE IF EXISTS `project`;

CREATE TABLE `admin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(64) DEFAULT '#',
  `github` varchar(64) NOT NULL DEFAULT '#',
  `twitter` varchar(64) NOT NULL DEFAULT '#',
  `md` text,
  `resume` text,
  `avatar` varchar(80) NOT NULL DEFAULT '/img/avatar.jpg',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `blog_view` (
  `vid` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `title` varchar(50) NOT NULL,
  `article` text NOT NULL,
  `tags` varchar(80) NOT NULL,
  `md` text NOT NULL,
  PRIMARY KEY (`vid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;


CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `url` varchar(60) NOT NULL DEFAULT '#',
  `tech` varchar(250) NOT NULL,
  `desp` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE `view_tag` (
  `name` varchar(40) NOT NULL,
  `vid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`,`vid`),
  KEY `fk_vid` (`vid`),
  CONSTRAINT `fk_vid` FOREIGN KEY (`vid`) REFERENCES `blog_view` (`vid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
