CREATE SCHEMA patatus_crm;
USE patatus_crm;

CREATE TABLE `sales_rep` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `leads` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `rep_lead_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc6nebvb6jpdd88cmk6lq5k7yi` (`rep_lead_id`),
  CONSTRAINT `FKc6nebvb6jpdd88cmk6lq5k7yi` FOREIGN KEY (`rep_lead_id`) REFERENCES `sales_rep` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `employee_count` int NOT NULL,
  `industry` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contact` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3ctagodg5h629t8ltnam39l5w` (`account_id`),
  CONSTRAINT `FK3ctagodg5h629t8ltnam39l5w` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `opportunity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `account_id` int DEFAULT NULL,
  `decision_maker_id` int DEFAULT NULL,
  `rep_opportunity_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9mvhqgny93la8u8k2gd531nex` (`account_id`),
  KEY `FKggsyw1oa5bk8uryvx9q0rsk8i` (`decision_maker_id`),
  KEY `FK4gvd73s3iiso9gl12k8ppot34` (`rep_opportunity_id`),
  CONSTRAINT `FK4gvd73s3iiso9gl12k8ppot34` FOREIGN KEY (`rep_opportunity_id`) REFERENCES `sales_rep` (`id`),
  CONSTRAINT `FK9mvhqgny93la8u8k2gd531nex` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKggsyw1oa5bk8uryvx9q0rsk8i` FOREIGN KEY (`decision_maker_id`) REFERENCES `contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
