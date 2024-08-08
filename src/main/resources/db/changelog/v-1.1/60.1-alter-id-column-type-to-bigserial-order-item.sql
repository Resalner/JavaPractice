CREATE SEQUENCE order_items_id_seq;
ALTER TABLE order_items
ALTER COLUMN id SET DEFAULT nextval('order_items_id_seq');

