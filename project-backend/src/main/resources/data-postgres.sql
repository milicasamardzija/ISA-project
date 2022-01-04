-- roles
INSERT INTO role(id, name) VALUES (nextval('role_id_seq'), 'ROLE_CLIENT');
INSERT INTO role(id, name) VALUES (nextval('role_id_seq'), 'ROLE_ADMIN');
INSERT INTO role(id, name) VALUES (nextval('role_id_seq'), 'ROLE_COTTAGE_OWNER');
INSERT INTO role(id, name) VALUES (nextval('role_id_seq'), 'ROLE_SHIP_OWNER');
INSERT INTO role(id, name) VALUES (nextval('role_id_seq'), 'ROLE_INSTRUCTOR');