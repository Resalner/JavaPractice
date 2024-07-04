ALTER TABLE orders
DROP CONSTRAINT fk_order_user;

ALTER TABLE orders
DROP CONSTRAINT fk_order_address;

ALTER TABLE order_item
DROP CONSTRAINT fk_order_item_order;

DROP TABLE orders;

