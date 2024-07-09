CREATE TABLE order_items (
    id BIGINT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    count INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

ALTER TABLE order_items
ADD CONSTRAINT fk_order_item_order 
FOREIGN KEY (order_id) REFERENCES orders(id);

ALTER TABLE order_items
ADD CONSTRAINT fk_order_item_product 
FOREIGN KEY (product_id) REFERENCES products(id);
