CREATE TABLE products (
  id BIGINT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(100),
  price DECIMAL(10, 2) NOT NULL,
  category_id BIGINT
);

ALTER TABLE products
ADD CONSTRAINT fk_products_category 
FOREIGN KEY (category_id) REFERENCES category(id);