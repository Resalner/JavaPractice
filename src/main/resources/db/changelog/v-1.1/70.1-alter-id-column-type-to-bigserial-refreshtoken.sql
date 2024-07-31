CREATE SEQUENCE refresh_tokens_id_seq;
ALTER TABLE refresh_tokens
ALTER COLUMN id SET DEFAULT nextval('refresh_tokens_id_seq');

