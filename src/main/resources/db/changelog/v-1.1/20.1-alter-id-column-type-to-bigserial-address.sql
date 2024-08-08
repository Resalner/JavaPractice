CREATE SEQUENCE address_id_seq;
ALTER TABLE address
ALTER COLUMN id SET DEFAULT nextval('address_id_seq');

