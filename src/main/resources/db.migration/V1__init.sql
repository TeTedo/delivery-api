CREATE TABLE IF NOT EXISTS `foods`
(
    id      bigint AUTO_INCREMENT
     PRIMARY KEY ,
    name                varchar(100)    NOT NULL,
    caloriePerGram      double          NOT NULL,
    carbohydratePerGram double          NOT NULL,
    proteinPerGram      double          NOT NULL,
    provincePerGram     double          NOT NULL,
    grams               int             NOT NULL,
    price               int             NOT NULL,
    imgPath             varchar(255)    NOT NULL
);
