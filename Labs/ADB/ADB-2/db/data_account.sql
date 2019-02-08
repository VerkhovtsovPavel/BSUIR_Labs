INSERT INTO `account` (`id`, `number`, `type`, `deposit_id`) VALUES (1, '1010000000001', '1', NULL);
INSERT INTO `account` (`id`, `number`, `type`, `deposit_id`) VALUES (2, '7327000000001', '2', NULL);


INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL,  2,  100000000, 3);