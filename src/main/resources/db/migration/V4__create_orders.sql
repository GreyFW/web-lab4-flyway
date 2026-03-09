CREATE TABLE orders (
    id         BIGSERIAL PRIMARY KEY,
    status     VARCHAR(50)  NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id    BIGINT       NOT NULL,
    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users (id)
);
