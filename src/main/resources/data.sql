INSERT INTO tb_roles (Id_Role, Name_Role) VALUES (1, 'admin') ON CONFLICT (Id_Role) DO NOTHING;
INSERT INTO tb_roles (Id_Role, Name_Role) VALUES (2, 'basic') ON CONFLICT (Id_Role) DO NOTHING;