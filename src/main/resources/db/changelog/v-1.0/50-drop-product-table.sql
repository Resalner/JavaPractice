ALTER TABLE products
DROP CONSTRAINT fk_products_category;

ALTER TABLE order_item
DROP CONSTRAINT fk_order_item_product;

DROP TABLE products;

