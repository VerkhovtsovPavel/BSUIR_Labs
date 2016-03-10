SELECT * FROM `rates` WHERE `cost` < 500;

SELECT * FROM `clientaddress` WHERE `city` = 'Minsk';

SELECT * FROM `rates` WHERE `rate_hour` < 12 AND `rate_minute` > 50;

SELECT * FROM `users` WHERE `user_id` = (SELECT `user_id` FROM `rates` WHERE `cost` = (SELECT MAX(`cost`) FROM `rates`))


