SELECT `first_name`, `last_name`, `lot_id` FROM `users` JOIN `lots` WHERE `lots`.`user_id` = `users`.`user_id`