-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'User'
-- 
-- ---

DROP TABLE IF EXISTS `User`;
		
CREATE TABLE `User` (
  `id` INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  `FirstName` VARCHAR(255) NOT NULL,
  `LastName` VARCHAR(255) NOT NULL,
  `MidleName` VARCHAR(255) NOT NULL,
  `Birthday` DATE NOT NULL DEFAULT 'NULL',
  `Sex_id` TINYINT NOT NULL DEFAULT NULL,
  `Passport_id` INTEGER NOT NULL,
  `Address_id` INTEGER NOT NULL,
  `MobilePhone` VARCHAR(255) NULL,
  `HomePhone` VARCHAR NULL,
  `E-mail` VARCHAR(255) NULL,
  `FamilyStatus` INTEGER NOT NULL,
  `Nationality_id` INTEGER NOT NULL,
  `Disability_id` INTEGER NOT NULL,
  `Pensioner` TINYINT NOT NULL,
  `MonthProfit` INTEGER NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'Sexs'
-- 
-- ---

DROP TABLE IF EXISTS `Sexs`;
		
CREATE TABLE `Sexs` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `Sex` VARCHAR(10) NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'PassportInfo'
-- 
-- ---

DROP TABLE IF EXISTS `PassportInfo`;
		
CREATE TABLE `PassportInfo` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `Serios` CHAR(2) NOT NULL,
  `Number` VARCHAR(15) NOT NULL,
  `WhoGives` VARCHAR(255) NOT NULL,
  `DateGives` DATE NOT NULL,
  `IndifyNumber` VARCHAR(255) NOT NULL,
  `BornPlace` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'Address'
-- 
-- ---

DROP TABLE IF EXISTS `Address`;
		
CREATE TABLE `Address` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `RealCity` VARCHAR NOT NULL,
  `RealStreet` VARCHAR(255) NOT NULL,
  `OfficialStreet` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'FamilyStatuses'
-- 
-- ---

DROP TABLE IF EXISTS `FamilyStatuses`;
		
CREATE TABLE `FamilyStatuses` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `Status` VARCHAR NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'Nationalitys'
-- 
-- ---

DROP TABLE IF EXISTS `Nationalitys`;
		
CREATE TABLE `Nationalitys` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `Nationality` VARCHAR(255) NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'Disabilitys'
-- 
-- ---

DROP TABLE IF EXISTS `Disabilitys`;
		
CREATE TABLE `Disabilitys` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `Disability` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Foreign Keys 
-- ---

ALTER TABLE `User` ADD FOREIGN KEY (Sex_id) REFERENCES `Sexs` (`id`);
ALTER TABLE `User` ADD FOREIGN KEY (Passport_id) REFERENCES `PassportInfo` (`id`);
ALTER TABLE `User` ADD FOREIGN KEY (Address_id) REFERENCES `Address` (`id`);
ALTER TABLE `User` ADD FOREIGN KEY (FamilyStatus) REFERENCES `FamilyStatuses` (`id`);
ALTER TABLE `User` ADD FOREIGN KEY (Nationality_id) REFERENCES `Nationalitys` (`id`);
ALTER TABLE `User` ADD FOREIGN KEY (Disability_id) REFERENCES `Disabilitys` (`id`);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE `User` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `Sexs` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `PassportInfo` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `Address` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `FamilyStatuses` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `Nationalitys` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `Disabilitys` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `User` (`id`,`FirstName`,`LastName`,`MidleName`,`Birthday`,`Sex_id`,`Passport_id`,`Address_id`,`MobilePhone`,`HomePhone`,`E-mail`,`FamilyStatus`,`Nationality_id`,`Disability_id`,`Pensioner`,`MonthProfit`) VALUES
-- ('','','','','','','','','','','','','','','','');
-- INSERT INTO `Sexs` (`id`,`Sex`) VALUES
-- ('','');
-- INSERT INTO `PassportInfo` (`id`,`Serios`,`Number`,`WhoGives`,`DateGives`,`IndifyNumber`,`BornPlace`) VALUES
-- ('','','','','','','');
-- INSERT INTO `Address` (`id`,`RealCity`,`RealStreet`,`OfficialStreet`) VALUES
-- ('','','','');
-- INSERT INTO `FamilyStatuses` (`id`,`Status`) VALUES
-- ('','');
-- INSERT INTO `Nationalitys` (`id`,`Nationality`) VALUES
-- ('','');
-- INSERT INTO `Disabilitys` (`id`,`Disability`) VALUES
-- ('','');
