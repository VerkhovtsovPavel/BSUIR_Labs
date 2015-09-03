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

