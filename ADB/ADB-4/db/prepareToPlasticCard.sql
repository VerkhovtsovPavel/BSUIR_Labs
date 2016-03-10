INSERT INTO `deposit`  (`id`, `deposittype`, `currency`, `startDate`, `endDate`, `sum`, `persent`, `depositNumber`, `user_id`, `isActive`) VALUES(1, '1', '3', '2015-11-14', '2020-08-15', 10000000.000000, 0.556000, '5555555J334LL5','1', '1');
INSERT INTO `account` (`id`, `number`, `type`, `deposit_id`) VALUES (3, '3014555550750', '2', '1');
INSERT INTO `account` (`id`, `number`, `type`, `deposit_id`) VALUES (4, '3014555510791', '2', '1');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '1', 10000000.000000, '3');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '3', '10000000.000000', '3');




INSERT INTO `credit`  (`id`, `credittype`, `currency`, `startDate`, `endDate`, `sum`, `persent`, `creditNumber`, `user_id`, `isActive`) VALUES(1, '2', '3', '2015-11-14', '2016-02-18', 1000000.000000, 0.420000, '4322222J334KJ2','1', '1');
INSERT INTO `account` (`id`, `number`, `type`, `credit_id`) VALUES (5, '2400432281670', '1', '1');
INSERT INTO `account` (`id`, `number`, `type`, `credit_id`) VALUES (6, '2400432281191', '1', '1');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '2', -1000000.000000, '3');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '5', '1000000.000000', '3');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '1', -1000000.000000, '3');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '5', '-1000000.000000', '3');


INSERT INTO `credit`  (`id`, `credittype`, `currency`, `startDate`, `endDate`, `sum`, `persent`, `creditNumber`, `user_id`, `isActive`) VALUES(2, '2', '3', '2015-11-14', '2016-02-18', 1000000.000000, 0.420000, '5678222J334KJ2','2', '1');
INSERT INTO `account` (`id`, `number`, `type`, `credit_id`) VALUES (7, '2400567881670', '1', '2');
INSERT INTO `account` (`id`, `number`, `type`, `credit_id`) VALUES (8, '2400567881191', '1', '2');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '2', -1000000.000000, '3');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '7', '1000000.000000', '3');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '1', -1000000.000000, '3');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '7', '-1000000.000000', '3');


INSERT INTO `credit`  (`id`, `credittype`, `currency`, `startDate`, `endDate`, `sum`, `persent`, `creditNumber`, `user_id`, `isActive`) VALUES(3, '2', '3', '2015-11-14', '2016-02-18', 1000000.000000, 0.420000, '1234222J334KJ2','3', '1');
INSERT INTO `account` (`id`, `number`, `type`, `credit_id`) VALUES (9, '2400123481670', '1', '3');
INSERT INTO `account` (`id`, `number`, `type`, `credit_id`) VALUES (10, '2400123481191', '1', '3');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '2', -1000000.000000, '3');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '9', '1000000.000000', '3');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '1', -1000000.000000, '3');
INSERT INTO `transaction` (`id`, `account_id`, `sum`, `currency_id`) VALUES (NULL, '9', '-1000000.000000', '3');
















