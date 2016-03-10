INSERT INTO `menu`.`dish` (`idDish`, `dishName`, `cost`, `dishClass`) VALUES (1, "Borsch" , 100, "First course");
INSERT INTO `menu`.`dish` (`idDish`, `dishName`, `cost`, `dishClass`) VALUES (2, "Steak" , 400, "Main course");
INSERT INTO `menu`.`dish` (`idDish`, `dishName`, `cost`, `dishClass`) VALUES (3, "Caesar salad" , 120, "First course");
INSERT INTO `menu`.`dish` (`idDish`, `dishName`, `cost`, `dishClass`) VALUES (4, "Vanilla ice cream" , 80, "Dessert");


INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (1, "Salt");
INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (2, "Potato");
INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (3, "Beef"); 
INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (4, "Beet");
INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (5, "Salad");
INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (6, "Feta cheese");
INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (7, "Milk");
INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (8, "Sugar");
INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (9, "Vanilla");
INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (10, "Olives");
INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (11, "Olive oil");
INSERT INTO `menu`.`products` (`idProducts`, `Name`) VALUES (12, "Cabbage");


INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (1, 4);
INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (1, 12);
INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (1, 2);
INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (1, 1);

INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (2, 3);
INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (2, 1);
INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (2, 11);

 
INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (3, 10);
INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (3, 11);
INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (3, 6);
INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (3, 1);

INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (4, 7);
INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (4, 9);
INSERT INTO `menu`.`dish_has_products` (`Dish_idDish`, `Products_idProducts`) VALUES (4, 8);



