ALTER TABLE `foods`
    ADD COLUMN  created_at  dateTime    NOT NULL    DEFAULT now(),
    ADD COLUMN  updated_at  dateTime    NOT NULL    DEFAULT now();
