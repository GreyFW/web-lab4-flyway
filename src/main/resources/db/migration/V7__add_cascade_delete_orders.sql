ALTER TABLE orders
    DROP CONSTRAINT fk_orders_user;

ALTER TABLE orders
    ADD CONSTRAINT fk_orders_user
        FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE;