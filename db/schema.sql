SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `deliveryService` ;
CREATE SCHEMA IF NOT EXISTS `deliveryService` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `deliveryService` ;

-- -----------------------------------------------------
-- Table `deliveryService`.`Position`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Position` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Position` (
  `idPosition` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idPosition`) ,
  UNIQUE INDEX `description_UNIQUE` (`description` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `deliveryService`.`Permissions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Permissions` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Permissions` (
  `idPermissions` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idPermissions`) ,
  UNIQUE INDEX `description_UNIQUE` (`description` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `deliveryService`.`MarkParcel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`MarkParcel` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`MarkParcel` (
  `idMarkParcel` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(45) NOT NULL ,
  `rate` FLOAT NOT NULL ,
  PRIMARY KEY (`idMarkParcel`) ,
  UNIQUE INDEX `description_UNIQUE` (`description` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `deliveryService`.`City`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`City` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`City` (
  `idCity` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`idCity`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `deliveryService`.`Street`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Street` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Street` (
  `idStreet` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `idCity` INT NOT NULL ,
  PRIMARY KEY (`idStreet`) ,
  INDEX `fk_Street_City1` (`idCity` ASC) ,
  CONSTRAINT `fk_Street_City1`
    FOREIGN KEY (`idCity` )
    REFERENCES `deliveryService`.`City` (`idCity` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `deliveryService`.`Authentication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Authentication` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Authentication` (
  `idAuthentication` INT NOT NULL AUTO_INCREMENT ,
  `userName` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(40) NOT NULL ,
  PRIMARY KEY (`idAuthentication`) ,
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `deliveryService`.`Office`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Office` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Office` (
  `idOffice` INT NOT NULL AUTO_INCREMENT ,
  `idStreet` INT NOT NULL ,
  `home` INT NOT NULL ,
  `housing` VARCHAR(2) NULL ,
  `room` INT NULL ,
  PRIMARY KEY (`idOffice`) ,
  INDEX `fk_Office_Street1` (`idStreet` ASC) ,
  CONSTRAINT `fk_Office_Street1`
    FOREIGN KEY (`idStreet` )
    REFERENCES `deliveryService`.`Street` (`idStreet` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deliveryService`.`Salary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Salary` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Salary` (
  `idSalary` INT NOT NULL AUTO_INCREMENT ,
  `baseRate` DOUBLE NOT NULL ,
  `raisingFactor` DOUBLE NOT NULL ,
  PRIMARY KEY (`idSalary`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deliveryService`.`Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Employee` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Employee` (
  `idEmployee` INT NOT NULL AUTO_INCREMENT ,
  `idAuthentication` INT NOT NULL ,
  `fullName` VARCHAR(150) NOT NULL ,
  `hireDate` DATE NOT NULL ,
  `idPosition` INT NOT NULL ,
  `idPermissions` INT NOT NULL ,
  `idOffice` INT NOT NULL ,
  `birthday` DATE NOT NULL ,
  `phoneNumber` VARCHAR(18) NOT NULL DEFAULT '\"\"' ,
  `eMail` VARCHAR(145) NOT NULL DEFAULT '\"\"' ,
  `idSalary` INT NOT NULL ,
  `isActive` TINYINT(1) NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`idEmployee`) ,
  INDEX `fk_Employee_Authentication1` (`idAuthentication` ASC) ,
  INDEX `fk_Employee_Position1` (`idPosition` ASC) ,
  INDEX `fk_Employee_Permissions1` (`idPermissions` ASC) ,
  INDEX `fk_Employee_Office1` (`idOffice` ASC) ,
  INDEX `fk_Employee_Salary1` (`idSalary` ASC) ,
  CONSTRAINT `fk_Employee_Authentication1`
    FOREIGN KEY (`idAuthentication` )
    REFERENCES `deliveryService`.`Authentication` (`idAuthentication` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_Position1`
    FOREIGN KEY (`idPosition` )
    REFERENCES `deliveryService`.`Position` (`idPosition` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_Permissions1`
    FOREIGN KEY (`idPermissions` )
    REFERENCES `deliveryService`.`Permissions` (`idPermissions` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_Office1`
    FOREIGN KEY (`idOffice` )
    REFERENCES `deliveryService`.`Office` (`idOffice` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_Salary1`
    FOREIGN KEY (`idSalary` )
    REFERENCES `deliveryService`.`Salary` (`idSalary` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `deliveryService`.`DriverLicenceCategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`DriverLicenceCategory` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`DriverLicenceCategory` (
  `idDriverLicenceCategory` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(3) NOT NULL ,
  PRIMARY KEY (`idDriverLicenceCategory`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deliveryService`.`DriverLicense`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`DriverLicense` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`DriverLicense` (
  `idDriverLicense` INT NOT NULL AUTO_INCREMENT ,
  `number` VARCHAR(9) NOT NULL ,
  `idDriverLicenceCategory` INT NOT NULL ,
  PRIMARY KEY (`idDriverLicense`) ,
  INDEX `fk_DriverLicense_DriverLicenceCategory1` (`idDriverLicenceCategory` ASC) ,
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) ,
  CONSTRAINT `fk_DriverLicense_DriverLicenceCategory1`
    FOREIGN KEY (`idDriverLicenceCategory` )
    REFERENCES `deliveryService`.`DriverLicenceCategory` (`idDriverLicenceCategory` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `deliveryService`.`Driver`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Driver` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Driver` (
  `idDriver` INT NOT NULL AUTO_INCREMENT ,
  `idEmployee` INT NOT NULL ,
  `idDriverLicense` INT NOT NULL ,
  `currentLocation` INT NULL ,
  `isActive` TINYINT(1) NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`idDriver`) ,
  INDEX `fk_Driver_Employee1` (`idEmployee` ASC) ,
  INDEX `fk_Driver_DriverLicense1` (`idDriverLicense` ASC) ,
  INDEX `fk_Driver_Office1` (`currentLocation` ASC) ,
  CONSTRAINT `fk_Driver_Employee1`
    FOREIGN KEY (`idEmployee` )
    REFERENCES `deliveryService`.`Employee` (`idEmployee` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Driver_DriverLicense1`
    FOREIGN KEY (`idDriverLicense` )
    REFERENCES `deliveryService`.`DriverLicense` (`idDriverLicense` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Driver_Office1`
    FOREIGN KEY (`currentLocation` )
    REFERENCES `deliveryService`.`Office` (`idOffice` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `deliveryService`.`CorporateCar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`CorporateCar` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`CorporateCar` (
  `idCorporateCar` INT NOT NULL AUTO_INCREMENT ,
  `mark` VARCHAR(45) NOT NULL ,
  `buyDate` DATE NOT NULL ,
  `requiredDriverLicenceCategory` INT NOT NULL ,
  `number` VARCHAR(45) NOT NULL ,
  `currentLocation` INT NULL ,
  `maxHeight` INT NOT NULL ,
  `maxWidth` INT NOT NULL ,
  `maxDepth` INT NOT NULL ,
  `maxWeigth` INT NOT NULL ,
  `isActive` TINYINT(1) NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`idCorporateCar`) ,
  INDEX `fk_CorporateCar_DriverLicenceCategory1` (`requiredDriverLicenceCategory` ASC) ,
  INDEX `fk_CorporateCar_Office1` (`currentLocation` ASC) ,
  CONSTRAINT `fk_CorporateCar_DriverLicenceCategory1`
    FOREIGN KEY (`requiredDriverLicenceCategory` )
    REFERENCES `deliveryService`.`DriverLicenceCategory` (`idDriverLicenceCategory` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CorporateCar_Office1`
    FOREIGN KEY (`currentLocation` )
    REFERENCES `deliveryService`.`Office` (`idOffice` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deliveryService`.`Client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Client` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Client` (
  `idClient` INT NOT NULL AUTO_INCREMENT ,
  `fullName` VARCHAR(255) NOT NULL ,
  `addDate` DATE NOT NULL ,
  PRIMARY KEY (`idClient`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deliveryService`.`PaymentsSystemType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`PaymentsSystemType` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`PaymentsSystemType` (
  `idPaymentsSystemType` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idPaymentsSystemType`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deliveryService`.`Payments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Payments` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Payments` (
  `idPayments` INT NOT NULL AUTO_INCREMENT ,
  `idPaymentsSystemType` INT NOT NULL ,
  `payDate` DATE NOT NULL ,
  `sum` INT NOT NULL ,
  PRIMARY KEY (`idPayments`) ,
  INDEX `fk_Payments_PaymentsSystemType1` (`idPaymentsSystemType` ASC) ,
  CONSTRAINT `fk_Payments_PaymentsSystemType1`
    FOREIGN KEY (`idPaymentsSystemType` )
    REFERENCES `deliveryService`.`PaymentsSystemType` (`idPaymentsSystemType` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deliveryService`.`Parcel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Parcel` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Parcel` (
  `idParcel` INT NOT NULL AUTO_INCREMENT ,
  `height` INT NOT NULL ,
  `width` INT NOT NULL ,
  `depth` INT NOT NULL ,
  `weight` INT NOT NULL ,
  `idMarkParcel` INT NOT NULL ,
  `idClient` INT NOT NULL ,
  `idPayments` INT NOT NULL ,
  `acceptanceDate` DATE NOT NULL ,
  `destination` INT NOT NULL ,
  PRIMARY KEY (`idParcel`) ,
  INDEX `fk_Parcel_MarkParcel1` (`idMarkParcel` ASC) ,
  INDEX `fk_Parcel_Client1` (`idClient` ASC) ,
  INDEX `fk_Parcel_Payments1` (`idPayments` ASC) ,
  INDEX `fk_Parcel_Office1` (`destination` ASC) ,
  CONSTRAINT `fk_Parcel_MarkParcel1`
    FOREIGN KEY (`idMarkParcel` )
    REFERENCES `deliveryService`.`MarkParcel` (`idMarkParcel` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Parcel_Client1`
    FOREIGN KEY (`idClient` )
    REFERENCES `deliveryService`.`Client` (`idClient` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Parcel_Payments1`
    FOREIGN KEY (`idPayments` )
    REFERENCES `deliveryService`.`Payments` (`idPayments` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Parcel_Office1`
    FOREIGN KEY (`destination` )
    REFERENCES `deliveryService`.`Office` (`idOffice` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deliveryService`.`DeliveryStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`DeliveryStatus` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`DeliveryStatus` (
  `idDeliveryStatus` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idDeliveryStatus`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deliveryService`.`Delivery`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Delivery` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Delivery` (
  `idDelivery` INT NOT NULL AUTO_INCREMENT ,
  `fromOffice` INT NOT NULL ,
  `toOffice` INT NOT NULL ,
  `startDate` DATE NOT NULL ,
  `endDate` DATE NOT NULL ,
  `idCorporateCar` INT NOT NULL ,
  `idDriver` INT NOT NULL ,
  `idDeliveryStatus` INT NOT NULL ,
  PRIMARY KEY (`idDelivery`) ,
  INDEX `fk_Delivery_Office1` (`fromOffice` ASC) ,
  INDEX `fk_Delivery_Office2` (`toOffice` ASC) ,
  INDEX `fk_Delivery_CorporateCar1` (`idCorporateCar` ASC) ,
  INDEX `fk_Delivery_DeliveryStatus1` (`idDeliveryStatus` ASC) ,
  INDEX `fk_Delivery_Driver1` (`idDriver` ASC) ,
  CONSTRAINT `fk_Delivery_Office1`
    FOREIGN KEY (`fromOffice` )
    REFERENCES `deliveryService`.`Office` (`idOffice` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Delivery_Office2`
    FOREIGN KEY (`toOffice` )
    REFERENCES `deliveryService`.`Office` (`idOffice` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Delivery_CorporateCar1`
    FOREIGN KEY (`idCorporateCar` )
    REFERENCES `deliveryService`.`CorporateCar` (`idCorporateCar` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Delivery_DeliveryStatus1`
    FOREIGN KEY (`idDeliveryStatus` )
    REFERENCES `deliveryService`.`DeliveryStatus` (`idDeliveryStatus` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Delivery_Driver1`
    FOREIGN KEY (`idDriver` )
    REFERENCES `deliveryService`.`Driver` (`idDriver` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deliveryService`.`Parcel_M2M_Delivery`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Parcel_M2M_Delivery` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Parcel_M2M_Delivery` (
  `idDelivery` INT NOT NULL ,
  `idParcel` INT NOT NULL ,
  INDEX `fk_Parcel_M2M_Delivery_Delivery1` (`idDelivery` ASC) ,
  INDEX `fk_Parcel_M2M_Delivery_Parcel1` (`idParcel` ASC) ,
  PRIMARY KEY (`idDelivery`, `idParcel`) ,
  CONSTRAINT `fk_Parcel_M2M_Delivery_Delivery1`
    FOREIGN KEY (`idDelivery` )
    REFERENCES `deliveryService`.`Delivery` (`idDelivery` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Parcel_M2M_Delivery_Parcel1`
    FOREIGN KEY (`idParcel` )
    REFERENCES `deliveryService`.`Parcel` (`idParcel` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deliveryService`.`Rate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deliveryService`.`Rate` ;

CREATE  TABLE IF NOT EXISTS `deliveryService`.`Rate` (
  `idRate` INT NOT NULL AUTO_INCREMENT ,
  `heigth` INT NOT NULL ,
  `width` INT NOT NULL ,
  `depth` INT NOT NULL ,
  `weigth` INT NOT NULL ,
  PRIMARY KEY (`idRate`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
