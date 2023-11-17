create database if not exists products;

use products;

drop table if exists product;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(64) DEFAULT NULL,
  `product_price` int(11) DEFAULT NULL,
 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `product` (`id`,`product_name`,`product_price`) VALUES (1,'pc_1999',700);
INSERT INTO `product` (`id`,`product_name`,`product_price`) VALUES (2,'phone',120);
INSERT INTO `product` (`id`,`product_name`,`product_price`) VALUES (3,'iphone10',700);

INSERT INTO `product` (`id`,`product_name`,`product_price`) VALUES (4,'charger',20);
INSERT INTO `product` (`id`,`product_name`,`product_price`) VALUES (5,'charger_typeC',25);
INSERT INTO `product` (`id`,`product_name`,`product_price`) VALUES (6,'fast_charger',30);

INSERT INTO `product` (`id`,`product_name`,`product_price`) VALUES (7,'case',7);
INSERT INTO `product` (`id`,`product_name`,`product_price`) VALUES (8,'case_full',10);
INSERT INTO `product` (`id`,`product_name`,`product_price`) VALUES (9,'case_red',9);

INSERT INTO `product` (`id`,`product_name`,`product_price`) VALUES (10,'headphones',50);
INSERT INTO `product` (`id`,`product_name`,`product_price`) VALUES (11,'wire_typeC',6);

