CREATE SEQUENCE orders_id_seq;
ALTER TABLE orders
ALTER COLUMN id SET DEFAULT nextval('orders_id_seq');

