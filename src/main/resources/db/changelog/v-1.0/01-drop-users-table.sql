ALTER TABLE users_info
DROP CONSTRAINT fk_user_id;

ALTER TABLE orders
DROP CONSTRAINT fk_order_user;

ALTER TABLE refresh_tokens
DROP CONSTRAINT fk_user;

DROP TABLE users;
