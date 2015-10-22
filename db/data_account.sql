INSERT INTO `account` (`id`, `number`, `type`, `deposit_id`) VALUES (NULL, '101000000001', '1', NULL);
INSERT INTO `account` (`id`, `number`, `type`, `deposit_id`) VALUES (NULL, '732700000001', '2', NULL);


INSERT INTO `transaction` (`id`, `account_id`, sum) VALUES (NULL,  '732700000001',  100000000);