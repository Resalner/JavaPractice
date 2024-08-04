ALTER TABLE user_tokens RENAME TO refresh_tokens;

ALTER TABLE refresh_tokens ADD COLUMN token VARCHAR(255) UNIQUE NOT NULL;
ALTER TABLE refresh_tokens ADD COLUMN expiration TIMESTAMP NOT NULL;

ALTER TABLE refresh_tokens DROP COLUMN access_token;
ALTER TABLE refresh_tokens DROP COLUMN access_token_expiration;
ALTER TABLE refresh_tokens DROP COLUMN refresh_token;
ALTER TABLE refresh_tokens DROP COLUMN refresh_token_expiration;

ALTER TABLE refresh_tokens ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

CREATE SEQUENCE refresh_tokens_id_seq;
ALTER TABLE refresh_tokens ALTER COLUMN id SET DEFAULT nextval('refresh_tokens_id_seq');