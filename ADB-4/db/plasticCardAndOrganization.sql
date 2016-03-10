INSERT INTO `plasticcard` (`id`, `number`, `pinCode`, `credit_id`, `deposit_id`) VALUES (NULL, '1111111111111111', '1111', '1', '1');
INSERT INTO `plasticcard` (`id`, `number`, `pinCode`, `credit_id`, `deposit_id`) VALUES (NULL, '2222222222222222', '2222', '2', NULL);
INSERT INTO `plasticcard` (`id`, `number`, `pinCode`, `credit_id`, `deposit_id`) VALUES (NULL, '3333333333333333', '3333', '3', NULL);


INSERT INTO `account` (`id`, `number`, `type`, `credit_id`) VALUES (11, '3012111111111', '2', '3');
INSERT INTO `account` (`id`, `number`, `type`, `credit_id`) VALUES (12, '3012222222222', '2', '3');
INSERT INTO `account` (`id`, `number`, `type`, `credit_id`) VALUES (13, '3012333333333', '2', '3');

INSERT INTO `organization` (`id`, `description`, `account_id`) VALUES (NULL, 'MTC', '11');
INSERT INTO `organization` (`id`, `description`, `account_id`) VALUES (NULL, 'Velcome', '12');
INSERT INTO `organization` (`id`, `description`, `account_id`) VALUES (NULL, 'Life', '13');

