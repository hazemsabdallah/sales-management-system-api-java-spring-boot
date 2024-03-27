DROP DATABASE IF EXISTS `sales-management-system`;

CREATE DATABASE `sales-management-system`;
USE `sales-management-system`;

-- creating seller table
CREATE TABLE `seller` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `mobile_no` VARCHAR(255),
    `email` VARCHAR(255),
    `address` VARCHAR(255),
    PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- creating client table
CREATE TABLE `client` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `mobile_no` VARCHAR(255),
    `email` VARCHAR(255),
    `address` VARCHAR(255),
    PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- creating product_category table
CREATE TABLE `product_category` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `category_name` VARCHAR(255),
    PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- creating product table
CREATE TABLE `product` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    `description` VARCHAR(255),
    `available_quantity` INT,
    `price` DECIMAL(13,2),
    `creation_date` DATETIME,
    `category_id` BIGINT NOT NULL,
    `seller_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_product_category` (`category_id`),
    KEY `fk_product_seller` (`seller_id`),
    CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`),
    CONSTRAINT `fk_product_seller` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- creating sale table
CREATE TABLE `sale` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME,
    `total_price` DECIMAL(13,2),
    `total_quantity` INT,
    `product_id` BIGINT NOT NULL,
    `seller_id` BIGINT NOT NULL,
    `client_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_sale_product` (`product_id`),
    KEY `fk_sale_seller` (`seller_id`),
    KEY `fk_sale_client` (`client_id`),
    CONSTRAINT `fk_sale_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
    CONSTRAINT `fk_sale_seller` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`),
    CONSTRAINT `fk_sale_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- inserting dummy data

INSERT INTO `seller` (`first_name`, `last_name`, `mobile_no`, `email`, `address`) VALUES ('seller01', 'seller01', '0123456789', 'seller01@test.com', 'seller01');
INSERT INTO `seller` (`first_name`, `last_name`, `mobile_no`, `email`, `address`) VALUES ('seller02', 'seller02', '0123456789', 'seller02@test.com', 'seller02');

INSERT INTO `client` (`first_name`, `last_name`, `mobile_no`, `email`, `address`) VALUES ('client01', 'client01', '0123456789', 'client01@test.com', 'client01');
INSERT INTO `client` (`first_name`, `last_name`, `mobile_no`, `email`, `address`) VALUES ('client02', 'client02', '0123456789', 'client02@test.com', 'client02');

INSERT INTO `product_category` (`category_name`) VALUES ('category01');
INSERT INTO `product_category` (`category_name`) VALUES ('category02');

INSERT INTO `product` (`name`, `description`, `available_quantity`, `price`, `creation_date`, `category_id`, `seller_id`) VALUES ('product01', 'product01', 10, 25.00, NOW(), 1, 1);
INSERT INTO `product` (`name`, `description`, `available_quantity`, `price`, `creation_date`, `category_id`, `seller_id`) VALUES ('product02', 'product02', 20, 50.00, NOW(), 2, 2);

INSERT INTO `sale` (`creation_date`, `total_price`, `total_quantity`, `product_id`, `seller_id`, `client_id`) VALUES (NOW(), 50.00, 2, 1, 1, 1);
INSERT INTO `sale` (`creation_date`, `total_price`, `total_quantity`, `product_id`, `seller_id`, `client_id`) VALUES (NOW(), 100.00, 2, 2, 2, 2);