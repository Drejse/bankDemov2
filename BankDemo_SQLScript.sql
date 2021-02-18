CREATE TABLE `Customer` (
  `Customer_id` int NOT NULL AUTO_INCREMENT,
  `Customer_email` varchar(45) NOT NULL,
  `Customer_username` varchar(45) NOT NULL,
  `Customer_password` varchar(45) NOT NULL,
  `Customer_balance` int NOT NULL,
  PRIMARY KEY (`Customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Transactions` (
  `Customer_id` int NOT NULL,
  `Transactions` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
