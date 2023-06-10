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
