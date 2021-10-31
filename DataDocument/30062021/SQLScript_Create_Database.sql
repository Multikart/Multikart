/* ============= User ============== 
User: dta5@dxc.com
Password: 1
*/
id
validflag
firstname
lastname
email
password
avatar
phone
address
role_id
datime
useradd
joindate

/* ============= Role ============== 
Role: Admin*/
id
validflag
name
description
generate
datime
useradd

/* ============= Category Product ============== */
id
validflag
name
description
datime
useradd

/* ============= Product ============== */
id
productcode
name
description
image (muilti)
category_id
price
size (config itempf)
status
validflag
datime
useradd


/* ============= Media ============== */
id
name
url
datime
useradd

/* ============= Order ============== */
- id
- transactionid
- product 
- paymentmethod_id
- paymentstatus_id
- orderstatus_id
- datime
- useradd


/* ============= Itempf ============== */
/* ============= CONFIG TABLE ============== */
- Size
- Payment Status
- Payment Method
- Order Status
- orderdate
- datime
- useradd
	
/* ============= CONFIG TABLE ============== */
id
itemtable
itemitem
description
validflag


CREATE DATABASE `ecommerce` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE ecommerce;

CREATE TABLE IF NOT EXISTS roles (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `generate` longblob,
  `validflag` char(1) DEFAULT NULL,
  `useradd` varchar(20) DEFAULT NULL,
  `datime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
);

INSERT INTO `ecommerce`.`roles` (`name`, `description`, `validflag`, `useradd`) VALUES ('admin', 'admin', '1', 'dta5');

CREATE TABLE IF NOT EXISTS users (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `role_id` int NOT NULL,
  `validflag` char(1) DEFAULT NULL,
  `datime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `useradd` varchar(20) DEFAULT NULL,
  `joindate` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_user_role` (`role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE
);

INSERT INTO `ecommerce`.`users` (`email`, `firstname`, `lastname`, `password`, `avatar`, `phone`, `role_id`, `validflag`, `useradd`, `joindate`) VALUES ('dta5@dxc.com', 'dac', 'ta', '$2a$10$/7nvIX0CcUGvt9tTpT3V7.M7lDQ9e5jMzqnE900Ch/dwHtSXmaqKG', 'images.jfif', '0916367136', '1', '1', 'dta5', '31072021');

CREATE TABLE IF NOT EXISTS productcat (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `validflag` char(1) NOT NULL,
  `datime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `useradd` varchar(20) NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
);

CREATE TABLE IF NOT EXISTS product (
  `id` int NOT NULL AUTO_INCREMENT,
  `productcode` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `price` decimal(10,0) DEFAULT '0',
  `size` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `validflag` char(1) DEFAULT NULL,
  `datime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `useradd` varchar(20) DEFAULT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category` (`category_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `productcat` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS medias (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(100) NOT NULL,
  `datime` date DEFAULT NULL,
  `useradd` varchar(20) DEFAULT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_image_product_idx` (`product_id`),
  CONSTRAINT `fk_image_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
);

CREATE TABLE IF NOT EXISTS itempf(
	id INT(11) NOT NULL AUTO_INCREMENT,
    itemtable varchar(5),
    itemitem varchar(20),
    description varchar(255),
    generate longblob,
    validflag character(1),
	CONSTRAINT pk_itempf PRIMARY KEY(id)
);