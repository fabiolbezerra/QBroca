-- restaurantes
insert into restaurante (id, nome) values (1, 'Minha Broca');

-- clientes
insert into cliente (id, nome, sobrenome, telefone, restaurante, data_hora) values (1, 'Fábio', 'Bezerra', '91981222689', 1, now());
insert into cliente (id, nome, sobrenome, telefone, restaurante, data_hora) values (2, 'Erika', 'Bezerra', '91981467319', 1, now());

-- endereços
insert into endereco (id, logradouro, numero, complemento, cep, bairro, cidade, estado, cliente)
values (1, 'Travessa Três de Maio', '1787', 'Ap 1901', '66.063-388', 'São Brás', 'Belém', 'PA', 1);
insert into endereco (id, logradouro, numero, complemento, cep, bairro, cidade, estado, cliente)
values (2, 'Avenida Alcindo Cacela', '995', 'Ap 205', '66.060-000', 'Umarizal', 'Belém', 'PA', 1);
insert into endereco (id, logradouro, numero, complemento, cep, bairro, cidade, estado, cliente)
values (3, 'Travessa Três de Maio', '1787', 'Ap 1901', '66.063-388', 'São Brás', 'Belém', 'PA', 2);
insert into endereco (id, logradouro, numero, complemento, cep, bairro, cidade, estado, cliente)
values (4, 'Rua Boaventura da Silva', '1578', 'Ap 1900', '66.060-060', 'Umarizal', 'Belém', 'PA', 2);

-- categorias
insert into categoria (id, nome, restaurante) values (1, 'Hambúrguer', 1);
insert into categoria (id, nome, restaurante) values (2, 'Refrigerante', 1);
insert into categoria (id, nome, restaurante) values (3, 'Suco', 1);
insert into categoria (id, nome, restaurante) values (4, 'Água', 1);

-- produtos
insert into produto (id, nome, preco, descricao, categoria, restaurante) values (1, 'Hambúrguer', 10.5, 'Pão de hambúrguer e uma carne de hambúrguer', 1, 1);
insert into produto (id, nome, preco, descricao, categoria, restaurante) values (2, 'X-Burguer', 12.5, 'Pão de hambúrguer, uma carne de hambúrguer e queijo', 1, 1);
insert into produto (id, nome, preco, descricao, categoria, restaurante) values (3, 'Double X-Burguer', 19.5, 'Pão de hambúrguer, duas carnes de hambúrguer e dois queijo', 1, 1);
insert into produto (id, nome, preco, descricao, categoria, restaurante) values (4, 'Coca-cola', 7, null, 2, 1);
insert into produto (id, nome, preco, descricao, categoria, restaurante) values (5, 'Coca-cola Zero', 7, null, 2, 1);
insert into produto (id, nome, preco, descricao, categoria, restaurante) values (6, 'Guaraná Antártica', 7, null, 2, 1);
insert into produto (id, nome, preco, descricao, categoria, restaurante) values (7, 'Suco de Maracujá', 6.5, 'Copo de 300 ml com suco de maracujá', 3, 1);
insert into produto (id, nome, preco, descricao, categoria, restaurante) values (8, 'Suco de Cupuaçú', 7.5, 'Copo de 300 ml com suco de cupuacú', 3, 1);
insert into produto (id, nome, preco, descricao, categoria, restaurante) values (9, 'Suco de Acerola', 7, 'Copo de 300 ml com suco de acerola', 3, 1);
insert into produto (id, nome, preco, descricao, categoria, restaurante) values (10, 'Água Mineral', 5, 'Copo de 300 ml com água mineral sem gás', 4, 1);
insert into produto (id, nome, preco, descricao, categoria, restaurante) values (11, 'Água Mineral Com Gás', 5.5, 'Copo de 300 ml com água mineral com gás', 4, 1);