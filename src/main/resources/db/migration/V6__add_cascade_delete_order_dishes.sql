ALTER TABLE order_dishes
    DROP CONSTRAINT fk_order_dishes_dish;

ALTER TABLE order_dishes
    ADD CONSTRAINT fk_order_dishes_dish
        FOREIGN KEY (dish_id) REFERENCES dishes (id)
        ON DELETE CASCADE;