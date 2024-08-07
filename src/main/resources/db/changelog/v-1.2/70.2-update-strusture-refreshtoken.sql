ALTER TABLE refresh_tokens RENAME TO user_tokens;

ALTER TABLE user_tokens DROP COLUMN token;
ALTER TABLE user_tokens DROP COLUMN expiration;

ALTER TABLE user_tokens ADD COLUMN access_token VARCHAR(255) UNIQUE NOT NULL;
ALTER TABLE user_tokens ADD COLUMN access_token_expiration TIMESTAMP NOT NULL;
ALTER TABLE user_tokens ADD COLUMN refresh_token VARCHAR(255) UNIQUE NOT NULL;
ALTER TABLE user_tokens ADD COLUMN refresh_token_expiration TIMESTAMP NOT NULL;