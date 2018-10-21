INSERT INTO users(nome,login,email,senha, status, tipo) VALUES('Administrador Fidelize','admin','suporte@fidelize.com','$2a$10$7lDebsVos0PILk3Hp.keNO5JZjh6ePCEM4nwTCcekXFPFuf88mkpO', true, 1);

INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN_MASTER');       -- ADMIN GERAL
INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');               -- USUARIO DO ADMINISTRATIVO
INSERT IGNORE INTO roles(name) VALUES('ROLE_USER_APP');           -- USUARIO DO APP
INSERT IGNORE INTO roles(name) VALUES('ROLE_USER_SITE');          -- USUARIO DO SITE
INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN_MODULO_USER');  -- ADMIN DO MODULO USER

INSERT INTO user_roles values (1,1);