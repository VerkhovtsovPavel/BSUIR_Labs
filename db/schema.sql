-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema menu
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema menu
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `menu` DEFAULT CHARACTER SET utf8 ;
USE `menu` ;

-- -----------------------------------------------------
-- Table `menu`.`dish`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `menu`.`dish` ;

CREATE TABLE IF NOT EXISTS `menu`.`dish` (
  `idDish` INT(11) NOT NULL,
  `dishName` VARCHAR(45) NULL DEFAULT NULL,
  `cost` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idDish`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `menu`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `menu`.`products` ;

CREATE TABLE IF NOT EXISTS `menu`.`products` (
  `idProducts` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`idProducts`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `menu`.`dish_has_products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `menu`.`dish_has_products` ;

CREATE TABLE IF NOT EXISTS `menu`.`dish_has_products` (
  `Dish_idDish` INT(11) NOT NULL,
  `Products_idProducts` INT(11) NOT NULL,
  PRIMARY KEY (`Dish_idDish`, `Products_idProducts`),
  INDEX `fk_Menu_has_Products_Products1_idx` (`Products_idProducts` ASC),
  INDEX `fk_Menu_has_Products_Menu_idx` (`Dish_idDish` ASC),
  CONSTRAINT `fk_Menu_has_Products_Menu`
    FOREIGN KEY (`Dish_idDish`)
    REFERENCES `menu`.`dish` (`idDish`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Menu_has_Products_Products1`
    FOREIGN KEY (`Products_idProducts`)
    REFERENCES `menu`.`products` (`idProducts`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
