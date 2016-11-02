/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE IF NOT EXISTS `jcala_blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jcala_blog`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(64) DEFAULT '#',
  `github` varchar(64) NOT NULL DEFAULT '#',
  `twitter` varchar(64) NOT NULL DEFAULT '#',
  `md` text,
  `resume` text,
  `avatar` varchar(80) NOT NULL DEFAULT '../img/avatar.jpg',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `blog_view` */

DROP TABLE IF EXISTS `blog_view`;

CREATE TABLE `blog_view` (
  `vid` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `title` varchar(50) NOT NULL,
  `article` text NOT NULL,
  `tags` varchar(80) NOT NULL,
  `md` text NOT NULL,
  PRIMARY KEY (`vid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `url` varchar(60) NOT NULL DEFAULT '#',
  `tech` varchar(250) NOT NULL,
  `desp` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


/*Table structure for table `view_tag` */

DROP TABLE IF EXISTS `view_tag`;

CREATE TABLE `view_tag` (
  `name` varchar(40) NOT NULL,
  `vid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`,`vid`),
  KEY `fk_vid` (`vid`),
  CONSTRAINT `fk_vid` FOREIGN KEY (`vid`) REFERENCES `blog_view` (`vid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
