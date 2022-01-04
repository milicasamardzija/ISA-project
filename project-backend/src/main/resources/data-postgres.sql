INSERT INTO role(id, name) VALUES (nextval('role_id_seq'), 'ROLE_CLIENT');
INSERT INTO role(id, name) VALUES (nextval('role_id_seq'), 'ROLE_ADMIN');
INSERT INTO role(id, name) VALUES (nextval('role_id_seq'), 'ROLE_COTTAGE_OWNER');
INSERT INTO role(id, name) VALUES (nextval('role_id_seq'), 'ROLE_SHIP_OWNER');
INSERT INTO role(id, name) VALUES (nextval('role_id_seq'), 'ROLE_INSTRUCTOR');

INSERT INTO address(id, country, city, street, number) VALUES (nextval('address_id_seq'), 'Srbija', 'Sabac', 'Vojvode Misica', 5);
INSERT INTO address(id, country, city, street, number) VALUES (nextval('address_id_seq'), 'Srbija', 'Novi Sad', 'Lilike Bem', 15);
INSERT INTO address(id, country, city, street, number) VALUES (nextval('address_id_seq'), 'Srbija', 'Sabac', 'Masarikova', 3);