INSERT INTO authority VALUES(1,'ROLE_KONYVKLUB_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_KONYVKLUB_USER');

INSERT INTO konyvklub_schema.address (city, number, street) VALUES ('Zajta', '12', 'RA');

INSERT INTO konyvklub_schema.address (city, number, street) VALUES ('Lazari', '54', 'VA');

INSERT INTO konyvklub_schema.address (city, number, street) VALUES ('Debrecen', '34', 'TO');

INSERT INTO konyvklub_schema.address (city, number, street) VALUES ('Szatmar', '12', 'VG');

INSERT INTO konyvklub_schema.user (active, email, enabled, first_name, last_name, password, username, address_id) VALUES (true, 'roszpapadavid@yahoo.com', true, 'VMI', 'VMI', '$2a$10$Rvh.Dl8.s.xJYzvP4w8JF.J3319IUb0/acl6DkXyVvgiup9Vy51j2', 'roszpapad', 1);

INSERT INTO konyvklub_schema.user (active, email, enabled, first_name, last_name, password, username, address_id) VALUES (true, 'roszpapadavid1@yahoo.com', true, 'VMI', 'VMI', '$2a$10$Rvh.Dl8.s.xJYzvP4w8JF.J3319IUb0/acl6DkXyVvgiup9Vy51j2', 'roszpapad1', 2);

INSERT INTO konyvklub_schema.user (active, email, enabled, first_name, last_name, password, username, address_id) VALUES (true, 'roszpapadavid2@yahoo.com', true, 'VMI', 'VMI', '$2a$10$Rvh.Dl8.s.xJYzvP4w8JF.J3319IUb0/acl6DkXyVvgiup9Vy51j2', 'fanniadmin', 3);

INSERT INTO konyvklub_schema.user (active, email, enabled, first_name, last_name, password, username, address_id) VALUES (true, 'roszpapadavid3@yahoo.com', true, 'VMI', 'VMI', '$2a$10$Rvh.Dl8.s.xJYzvP4w8JF.J3319IUb0/acl6DkXyVvgiup9Vy51j2', 'roszpapad2', 4);

INSERT INTO user_authorities VALUES (1,2);

INSERT INTO user_authorities VALUES (2,2);

INSERT INTO user_authorities VALUES (3,1);

INSERT INTO user_authorities VALUES (4,2);

INSERT INTO konyvklub_schema.chat_channel(username_one, username_two) VALUES ('Pisti','Jani');


