INSERT INTO user(nome,login,email,senha) VALUES('Administrador Fidelize','admin','suporte@fidelize.com','$2a$10$7lDebsVos0PILk3Hp.keNO5JZjh6ePCEM4nwTCcekXFPFuf88mkpO');

INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN_MASTER');
INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');
INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN_MODULO_USER');
INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN_MODULO_USER_APP');

INSERT INTO user_roles values (1,1);