CREATE TABLE IF NOT EXISTS `foods`
(
    id                      bigint          NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
    name                    varchar(100)    NOT NULL,
    calorie_per_gram        double          NOT NULL,
    carbohydrate_per_gram   double          NOT NULL,
    protein_per_gram        double          NOT NULL,
    province_per_gram       double          NOT NULL,
    grams                   int             NOT NULL,
    price                   int             NOT NULL,
    img_path                varchar(255)    NOT NULL
);
