CREATE SEQUENCE products_id_seq;
ALTER TABLE products
ALTER COLUMN id SET DEFAULT nextval('products_id_seq');

