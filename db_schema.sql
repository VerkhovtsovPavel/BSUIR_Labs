-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bank_users
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bank_users
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bank_users` DEFAULT CHARACTER SET utf8 ;
USE `bank_users` ;

-- -----------------------------------------------------
-- Table `bank_users`.`city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank_users`.`city` ;

CREATE TABLE IF NOT EXISTS `bank_users`.`city` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `RealCity` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank_users`.`currency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank_users`.`currency` ;

CREATE TABLE IF NOT EXISTS `bank_users`.`currency` (
  `id` INT(11) NOT NULL,
  `description` VARCHAR(4) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `bank_users`.`deposittype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank_users`.`deposittype` ;

CREATE TABLE IF NOT EXISTS `bank_users`.`deposittype` (
  `id` INT(11) NOT NULL,
  `type` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `bank_users`.`passportinfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank_users`.`passportinfo` ;

CREATE TABLE IF NOT EXISTS `bank_users`.`passportinfo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Serios` CHAR(2) NOT NULL,
  `Number` VARCHAR(15) NOT NULL,
  `WhoGives` VARCHAR(255) NOT NULL,
  `DateGives` DATE NOT NULL,
  `IndifyNumber` VARCHAR(255) NOT NULL,
  `BornPlace` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank_users`.`familystatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank_users`.`familystatus` ;

CREATE TABLE IF NOT EXISTS `bank_users`.`familystatus` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Status` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank_users`.`nationality`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank_users`.`nationality` ;

CREATE TABLE IF NOT EXISTS `bank_users`.`nationality` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Nationality` VARCHAR(255) NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank_users`.`disability`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank_users`.`disability` ;

CREATE TABLE IF NOT EXISTS `bank_users`.`disability` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Disability` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank_users`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank_users`.`user` ;

CREATE TABLE IF NOT EXISTS `bank_users`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(255) NOT NULL,
  `LastName` VARCHAR(255) NOT NULL,
  `MidleName` VARCHAR(255) NOT NULL,
  `Birthday` DATE NOT NULL,
  `Sex` TINYINT(1) NOT NULL,
  `Address_id` INT(11) NOT NULL,
  `MobilePhone` VARCHAR(255) NULL DEFAULT NULL,
  `HomePhone` VARCHAR(255) NULL DEFAULT NULL,
  `E-mail` VARCHAR(255) NULL DEFAULT NULL,
  `FamilyStatus` INT(11) NOT NULL,
  `Nationality_id` INT(11) NOT NULL,
  `Disability_id` INT(11) NOT NULL,
  `Pensioner` TINYINT(1) NOT NULL,
  `MonthProfit` INT(11) NULL DEFAULT NULL,
  `Official Street` VARCHAR(255) NOT NULL,
  `Real Street` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `Address_id` (`Address_id` ASC),
  INDEX `FamilyStatus` (`FamilyStatus` ASC),
  INDEX `Nationality_id` (`Nationality_id` ASC),
  INDEX `Disability_id` (`Disability_id` ASC),
  CONSTRAINT `user_ibfk_1`
    FOREIGN KEY (`id`)
    REFERENCES `bank_users`.`passportinfo` (`id`),
  CONSTRAINT `user_ibfk_2`
    FOREIGN KEY (`Address_id`)
    REFERENCES `bank_users`.`city` (`id`),
  CONSTRAINT `user_ibfk_3`
    FOREIGN KEY (`FamilyStatus`)
    REFERENCES `bank_users`.`familystatus` (`id`),
  CONSTRAINT `user_ibfk_4`
    FOREIGN KEY (`Nationality_id`)
    REFERENCES `bank_users`.`nationality` (`id`),
  CONSTRAINT `user_ibfk_5`
    FOREIGN KEY (`Disability_id`)
    REFERENCES `bank_users`.`disability` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank_users`.`deposit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank_users`.`deposit` ;

CREATE TABLE IF NOT EXISTS `bank_users`.`deposit` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `deposittype` INT(11) NOT NULL,
  `currency` INT(11) NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NULL DEFAULT NULL,
  `sum` DOUBLE NOT NULL,
  `persent` DOUBLE NOT NULL,
  `depositNumber` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_deposit_deposittype1` (`deposittype` ASC),
  INDEX `fk_deposit_currency1` (`currency` ASC),
  INDEX `fk_deposit_user1` (`user_id` ASC),
  CONSTRAINT `fk_deposit_currency1`
    FOREIGN KEY (`currency`)
    REFERENCES `bank_users`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_deposit_deposittype1`
    FOREIGN KEY (`deposittype`)
    REFERENCES `bank_users`.`deposittype` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_deposit_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `bank_users`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `bank_users`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank_users`.`account` ;

CREATE TABLE IF NOT EXISTS `bank_users`.`account` (
  `id` INT NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `deposit_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_account_deposit1_idx` (`deposit_id` ASC),
  CONSTRAINT `fk_account_deposit1`
    FOREIGN KEY (`deposit_id`)
    REFERENCES `bank_users`.`deposit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank_users`.`transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank_users`.`transaction` ;

CREATE TABLE IF NOT EXISTS `bank_users`.`transaction` (
  `id` INT NOT NULL,
  `account_id` INT NOT NULL,
  `sum` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transaction_account1_idx` (`account_id` ASC),
  CONSTRAINT `fk_transaction_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `bank_users`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
