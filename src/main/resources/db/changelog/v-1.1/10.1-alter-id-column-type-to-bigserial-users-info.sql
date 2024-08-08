CREATE SEQUENCE users_info_id_seq;
ALTER TABLE users_info
ALTER COLUMN id SET DEFAULT nextval('users_info_id_seq');

