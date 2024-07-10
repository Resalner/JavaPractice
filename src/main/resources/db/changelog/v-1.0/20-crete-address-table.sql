CREATE TABLE address (
    id BIGINT PRIMARY KEY,
    city VARCHAR(25) NOT NULL,
    street VARCHAR(50) NOT NULL,
    house_number VARCHAR(10) NOT NULL,
    apartment_number VARCHAR(10)
);