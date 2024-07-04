CREATE TABLE users_info (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    phonenumber VARCHAR(20) NOT NULL,
    birth_date DATE,
    gender VARCHAR(10),
    email VARCHAR(100)
);

ALTER TABLE users_info
ADD CONSTRAINT fk_user_id
FOREIGN KEY (id) REFERENCES users(id);