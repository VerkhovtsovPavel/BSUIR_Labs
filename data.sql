INSERT INTO `FamilyStatus` (`id`,`Status`) VALUES (NULL,'Замужем');
INSERT INTO `FamilyStatus` (`id`,`Status`) VALUES (NULL,'Холост');
INSERT INTO `FamilyStatus` (`id`,`Status`) VALUES (NULL,'Разведен');

INSERT INTO `Nationality` (`id`,`Nationality`) VALUES (NULL,'РБ');
INSERT INTO `Nationality` (`id`,`Nationality`) VALUES (NULL,'РФ');
INSERT INTO `Nationality` (`id`,`Nationality`) VALUES (NULL,'Польша');
INSERT INTO `Nationality` (`id`,`Nationality`) VALUES (NULL,'Украина');
INSERT INTO `Nationality` (`id`,`Nationality`) VALUES (NULL,'Литва');

INSERT INTO `Disability` (`id`,`Disability`) VALUES (NULL,'1-я степень');
INSERT INTO `Disability` (`id`,`Disability`) VALUES (NULL,'2-я степень');
INSERT INTO `Disability` (`id`,`Disability`) VALUES (NULL,'3-я степень');
INSERT INTO `Disability` (`id`,`Disability`) VALUES (NULL,'4-я степень');
INSERT INTO `Disability` (`id`,`Disability`) VALUES (NULL,'Нет инвалидности');

INSERT INTO `bank_users`.`city` (`id`, `RealCity`) VALUES (NULL, 'Минск');
INSERT INTO `bank_users`.`city` (`id`, `RealCity`) VALUES (NULL, 'Гродно');
INSERT INTO `bank_users`.`city` (`id`, `RealCity`) VALUES (NULL, 'Могилев');
INSERT INTO `bank_users`.`city` (`id`, `RealCity`) VALUES (NULL, 'Витебск');
INSERT INTO `bank_users`.`city` (`id`, `RealCity`) VALUES (NULL, 'Гомель');


SELECT `RealCity` FROM `city`;
SELECT `Disability` FROM `disability`;
SELECT `Status` FROM `familystatus`;
SELECT `Nationality` FROM `nationality`;


INSERT INTO `bank_users`.`passportinfo` (`id`, `Serios`, `Number`, `WhoGives`, `DateGives`, `IndifyNumber`, `BornPlace`) VALUES (NULL, 'MP', '6464645', 'Центральное РУВД г. Минска', '2015-09-08', '5454545J545HJ5', 'г. Минск' );
INSERT INTO `bank_users`.`user`  (`id`, `FirstName`, `LastName`, `MidleName`, `Birthday`, `Sex`, `Address_id`, `MobilePhone`, `HomePhone`, `E-mail`, `FamilyStatus`, `Nationality_id`, `Disability_id`, `Pensioner`, `MonthProfit`, `Official Street`, `Real Street`) VALUES (NULL, 'Верховцов', 'Павел', 'Андреевич', '1993-06-08', '1', '1', '+(375)-29-505-7350', '+(337)-51-733-4961', 'verchpasha@gmail.com', '2', '1', '5', '0', '1000', 'ул. Куйбышева д.17 кв.30', 'ул. Куйбышева д.17 кв.30');

INSERT INTO `bank_users`.`passportinfo` (`id`, `Serios`, `Number`, `WhoGives`, `DateGives`, `IndifyNumber`, `BornPlace`) VALUES (NULL, 'MP', '1234567', 'Центральное РУВД г. Минска', '2004-10-11', '7851245J534HA5', 'г. Минск' );
INSERT INTO `bank_users`.`user`  (`id`, `FirstName`, `LastName`, `MidleName`, `Birthday`, `Sex`, `Address_id`, `MobilePhone`, `HomePhone`, `E-mail`, `FamilyStatus`, `Nationality_id`, `Disability_id`, `Pensioner`, `MonthProfit`, `Official Street`, `Real Street`) VALUES (NULL, 'Иванов', 'Иван', 'Иванович', '1943-01-10', '1', '1', '+(375)-29-345-3874', '+(375)-17-791-9842', 'iivanov@gmail.com', '2', '2', '2', '1', '0', 'ул. Кирова д.1 кв.310', 'ул. П.Бровки д.7 кв.3');

INSERT INTO `bank_users`.`passportinfo` (`id`, `Serios`, `Number`, `WhoGives`, `DateGives`, `IndifyNumber`, `BornPlace`) VALUES (NULL, 'MP', '1283568', 'Центральное РУВД г. Минска', '2001-10-11', '7321453J534HA5', 'г. Минск' );
INSERT INTO `bank_users`.`user`  (`id`, `FirstName`, `LastName`, `MidleName`, `Birthday`, `Sex`, `Address_id`, `MobilePhone`, `HomePhone`, `E-mail`, `FamilyStatus`, `Nationality_id`, `Disability_id`, `Pensioner`, `MonthProfit`, `Official Street`, `Real Street`) VALUES (NULL, 'Петров', 'Петр', 'Петрович', '1944-01-10', '1', '1', '+(375)-29-232-9125', '+(375)-17-631-3715', 'ppetrov@gmail.com', '2', '1', '3', '1', '0', 'ул. Красная д.7 кв.10', 'ул. Красная д.7 кв.10');
