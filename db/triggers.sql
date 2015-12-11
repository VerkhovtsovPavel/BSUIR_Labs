CREATE TRIGGER `check_car_overflow` BEFORE INSERT ON `rates`
FOR EACH ROW
BEGIN
    DECLARE msg VARCHAR(255);
    DECLARE corCarId INT;
	
    
    SET corCarID = SELECT `idCorporateCar` FROM `devivery` WHERE `idDevivery` = NEW.idDevivery;
    
    

    IF(NEW.cost<=(SELECT `cost` FROM `rates` WHERE `lot_id`=NEW.lot_id LIMIT 1)) THEN
        set msg = "INSERT ABORT: New lot cost lower then old lot cost";
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;    
    END IF;
    
    IF((SELECT `user_role` FROM `users` WHERE `user_id`=NEW.user_id)=3) THEN
        set msg = "INSERT ABORT: Organizations can not bet";
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;    
    END IF;
END;$$