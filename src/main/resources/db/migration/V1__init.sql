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
    img_path                varchar(255)    NOT NULL,
    created_at              dateTime        NOT NULL    DEFAULT now(),
    updated_at              dateTime        NOT NULL    DEFAULT now()
);

CREATE TABLE IF NOT EXISTS `payments`
(
    id              bigint      NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
    order_id        bigint      NOT NULL,
    user_id         bigint      NOT NULL,
    isSuccess       boolean     NOT NULL    DEFAULT false,
    created_at      dateTime    NOT NULL    DEFAULT now(),
    updated_at      dateTime    NOT NULL    DEFAULT now(),

    CONSTRAINT payments_order_id_fk
    FOREIGN KEY (order_id)
    REFERENCES orders (id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS `orders`
(
    id              bigint      NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
    food_id         bigint      NOT NULL,
    user_id         bigint      NOT NULL,
    count           int         NOT NULL,
    created_at      dateTime    NOT NULL    DEFAULT now(),
    updated_at      dateTime    NOT NULL    DEFAULT now(),

    CONSTRAINT orders_food_id_fk
    FOREIGN KEY (food_id)
    REFERENCES foods (id) ON UPDATE CASCADE ON DELETE RESTRICT
);
