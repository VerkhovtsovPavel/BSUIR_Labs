INSERT INTO `familystatus` (`id`,`Status`) VALUES (1,'Замужем');
INSERT INTO `familystatus` (`id`,`Status`) VALUES (2,'Холост');
INSERT INTO `familystatus` (`id`,`Status`) VALUES (3,'Разведен');

INSERT INTO `nationality` (`id`,`Nationality`) VALUES (1,'РБ');
INSERT INTO `nationality` (`id`,`Nationality`) VALUES (2,'РФ');
INSERT INTO `nationality` (`id`,`Nationality`) VALUES (3,'Польша');
INSERT INTO `nationality` (`id`,`Nationality`) VALUES (4,'Украина');
INSERT INTO `nationality` (`id`,`Nationality`) VALUES (5,'Литва');

INSERT INTO `disability` (`id`,`Disability`) VALUES (1,'1-я степень');
INSERT INTO `disability` (`id`,`Disability`) VALUES (2,'2-я степень');
INSERT INTO `disability` (`id`,`Disability`) VALUES (3,'3-я степень');
INSERT INTO `disability` (`id`,`Disability`) VALUES (4,'4-я степень');
INSERT INTO `disability` (`id`,`Disability`) VALUES (5,'Нет инвалидности');

INSERT INTO `city` (`id`, `RealCity`) VALUES (1, 'Минск');
INSERT INTO `city` (`id`, `RealCity`) VALUES (2, 'Гродно');
INSERT INTO `city` (`id`, `RealCity`) VALUES (3, 'Могилев');
INSERT INTO `city` (`id`, `RealCity`) VALUES (4, 'Витебск');
INSERT INTO `city` (`id`, `RealCity`) VALUES (5, 'Гомель');

INSERT INTO `passportinfo` (`id`, `Serios`, `Number`, `WhoGives`, `DateGives`, `IndifyNumber`, `BornPlace`) VALUES (1, 'MP', '6464645', 'Центральное РУВД г. Минска', '2015-09-08', '5454545J545HJ5', 'г. Минск' );
INSERT INTO `passportinfo` (`id`, `Serios`, `Number`, `WhoGives`, `DateGives`, `IndifyNumber`, `BornPlace`) VALUES (2, 'MP', '1234567', 'Центральное РУВД г. Минска', '2004-10-11', '7851245J534HA5', 'г. Минск' );
INSERT INTO `passportinfo` (`id`, `Serios`, `Number`, `WhoGives`, `DateGives`, `IndifyNumber`, `BornPlace`) VALUES (3, 'MP', '1283568', 'Центральное РУВД г. Минска', '2001-10-11', '7321453J534HA5', 'г. Минск' );

INSERT INTO `user`  (`id`, `FirstName`, `LastName`, `MidleName`, `Birthday`, `Sex`, `Address_id`, `MobilePhone`, `HomePhone`, `E-mail`, `FamilyStatus`, `Nationality_id`, `Disability_id`, 
`Pensioner`, `MonthProfit`, `Official Street`, `Real Street`) VALUES 
(1, 'Верховцов', 'Павел', 'Андреевич', '1993-06-08', '1', '1', '+(375)-29-505-7350', '+(337)-51-733-4961', 'verchpasha@gmail.com', '2', '1', '5', '0', '1000', 'ул. Куйбышева д.17 кв.30', 'ул. Куйбышева д.17 кв.30');
INSERT INTO `user`  (`id`, `FirstName`, `LastName`, `MidleName`, `Birthday`, `Sex`, `Address_id`, `MobilePhone`, `HomePhone`, `E-mail`, `FamilyStatus`, `Nationality_id`, `Disability_id`, 
`Pensioner`, `MonthProfit`, `Official Street`, `Real Street`) VALUES 
(2, 'Иванов', 'Иван', 'Иванович', '1943-01-10', '1', '1', '+(375)-29-345-3874', '+(375)-17-791-9842', 'iivanov@gmail.com', '2', '2', '2', '1', '0', 'ул. Кирова д.1 кв.310', 'ул. П.Бровки д.7 кв.3');
INSERT INTO `user`  (`id`, `FirstName`, `LastName`, `MidleName`, `Birthday`, `Sex`, `Address_id`, `MobilePhone`, `HomePhone`, `E-mail`, `FamilyStatus`, `Nationality_id`, `Disability_id`,
`Pensioner`, `MonthProfit`, `Official Street`, `Real Street`) VALUES 
(3, 'Петров', 'Петр', 'Петрович', '1944-01-10', '1', '1', '+(375)-29-232-9125', '+(375)-17-631-3715', 'ppetrov@gmail.com', '2', '1', '3', '1', '0', 'ул. Красная д.7 кв.10', 'ул. Красная д.7 кв.10');
