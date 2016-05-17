INSERT INTO `deliveryservice`.`city` (`idCity`, `name`) VALUES (1, 'Минск');
INSERT INTO `deliveryservice`.`city` (`idCity`, `name`) VALUES (2, 'Гродно');
INSERT INTO `deliveryservice`.`city` (`idCity`, `name`) VALUES (3, 'Могилев');

INSERT INTO `deliveryservice`.`street` (`idStreet`, `name`, `idCity`) VALUES (1, 'ул. Куйбышева' , 1);
INSERT INTO `deliveryservice`.`street` (`idStreet`, `name`, `idCity`) VALUES (2, 'ул. Гикало' , 1);
INSERT INTO `deliveryservice`.`street` (`idStreet`, `name`, `idCity`) VALUES (3, 'ул. П.Бровки' , 1);

INSERT INTO `deliveryservice`.`street` (`idStreet`, `name`, `idCity`) VALUES (4, 'пр. Победителей' , 2);
INSERT INTO `deliveryservice`.`street` (`idStreet`, `name`, `idCity`) VALUES (5, 'ул. Славянская' , 2);
INSERT INTO `deliveryservice`.`street` (`idStreet`, `name`, `idCity`) VALUES (6, 'ул. Лисневского' , 3);

