ALTER TABLE users_info
DROP CONSTRAINT fk_user_id;

ALTER TABLE orders
DROP CONSTRAINT fk_order_user;

DROP TABLE users;