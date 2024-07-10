CREATE TABLE orders (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(25) NOT NULL,
    address_id BIGINT NOT NULL,
    comments VARCHAR(50)
);

ALTER TABLE orders
ADD CONSTRAINT fk_order_user
FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE orders
ADD CONSTRAINT fk_order_address
FOREIGN KEY (address_id) REFERENCES address(id);