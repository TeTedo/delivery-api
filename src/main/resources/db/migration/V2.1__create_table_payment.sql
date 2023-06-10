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
