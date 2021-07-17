CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(100) NOT NULL,
  `datime` date DEFAULT NULL,
  `useradd` varchar(20) DEFAULT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_image_product_idx` (`product_id`),
  CONSTRAINT `fk_image_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
