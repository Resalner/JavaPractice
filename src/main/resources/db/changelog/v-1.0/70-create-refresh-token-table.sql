CREATE TABLE refresh_tokens (
    id BIGINT PRIMARY KEY,
    token VARCHAR(255) UNIQUE NOT NULL,
    expiration TIMESTAMP NOT NULL,
    user_id BIGINT
);

ALTER TABLE refresh_tokens
ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id);