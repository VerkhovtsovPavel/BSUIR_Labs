DELIMITER $$
CREATE TRIGGER `sha1_password` BEFORE INSERT ON `authentication`
FOR EACH ROW
BEGIN
   set new.password = SHA1(new.password);
END;$$

CREATE TRIGGER `check_car_and_driver_licence` BEFORE INSERT ON `delivery`
FOR EACH ROW
BEGIN
    DECLARE msg VARCHAR(255);
    DECLARE driverLId INT;
    DECLARE carLId INT;
	
    
    SET driverLId = (SELECT `idDriverLicenceCategory` FROM `Driverlicense` WHERE `idDriverLicense` = (SELECT `idDriverLicense` FROM `Driver` WHERE `idDriver` =NEW.idDriver));
    SET carLId = (SELECT `requiredDriverLicenceCategory` FROM `corporatecar` WHERE `idCorporateCar` = NEW.idCorporateCar);
    

    IF(driverLId < carLId) THEN
        set msg = "INSERT ABORT: Invalid category driver";
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;    
    END IF;
    
END;$$


DELIMITER $$
CREATE TRIGGER `check_car_overflow` BEFORE INSERT ON `parcel_m2m_delivery`
FOR EACH ROW
BEGIN
    DECLARE msg VARCHAR(255);
    
    DECLARE corCarId INT;
    
    DECLARE corCarMaxH INT;
    DECLARE corCarMaxD INT;
    DECLARE corCarMaxWei INT;
    DECLARE corCarMaxWid INT;
    
    DECLARE currentH INT;
    DECLARE currentD INT;
    DECLARE currentWei INT;
    DECLARE currentWid INT;
    
    DECLARE newParcelH INT;
    DECLARE newParcelD INT;
    DECLARE newParcelWei INT;
    DECLARE newParcelWid INT;
    
    SET corCarID = (SELECT `idCorporateCar` FROM `delivery` WHERE `idDelivery` = NEW.idDelivery);

    SET corCarMaxH = (SELECT `maxHeight` FROM `corporatecar` WHERE `idCorporateCar` = corCarID);
    SET corCarMaxD = (SELECT `maxDepth` FROM `corporatecar` WHERE `idCorporateCar` = corCarID);
    SET corCarMaxWei = (SELECT `maxWeigth` FROM `corporatecar` WHERE `idCorporateCar` = corCarID);
    SET corCarMaxWid =(SELECT `maxWidth` FROM `corporatecar` WHERE `idCorporateCar` = corCarID);

    SET currentH = (SELECT SUM(`height`) FROM Parcel WHERE `idParcel` IN (SELECT `idParcel` FROM parcel_m2m_delivery WHERE `idDelivery` = NEW.idDelivery));
    SET currentD = (SELECT SUM(`depth`) FROM Parcel WHERE `idParcel` IN (SELECT `idParcel` FROM parcel_m2m_delivery WHERE `idDelivery` = NEW.idDelivery));
    SET currentWei = (SELECT SUM(`weight`) FROM Parcel WHERE `idParcel` IN (SELECT `idParcel` FROM parcel_m2m_delivery WHERE `idDelivery` = NEW.idDelivery));
    SET currentWid  = (SELECT SUM(`width`) FROM Parcel WHERE `idParcel` IN (SELECT `idParcel` FROM parcel_m2m_delivery WHERE `idDelivery` = NEW.idDelivery));

    SET newParcelH = (SELECT `height` FROM Parcel WHERE `idParcel` = NEW.idParcel);
    SET newParcelD = (SELECT `depth` FROM Parcel WHERE `idParcel` = NEW.idParcel);
    SET newParcelWei = (SELECT `weight` FROM Parcel WHERE `idParcel` = NEW.idParcel);
    SET newParcelWid  = (SELECT `width` FROM Parcel WHERE `idParcel` = NEW.idParcel);

    IF(currentH+newParcelH>corCarMaxH) THEN
        set msg = "INSERT ABORT: Overflow by height";
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;    
    END IF;
    
    IF(currentD+newParcelD>corCarMaxD) THEN
        set msg = "INSERT ABORT: Overflow by depth";
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;    
    END IF;

    IF(currentWei+newParcelWei>corCarMaxWei) THEN
        set msg = "INSERT ABORT: Overflow by weight";
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;    
    END IF;

    IF(currentWid+newParcelWid>corCarMaxWid) THEN
        set msg = "INSERT ABORT: Overflow by width";
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;    
    END IF;
END;$$

DELIMITER ;