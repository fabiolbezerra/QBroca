Ante de iniciar/executar a aplicação QBroca, considere seguir as instruções abaixo.

# Criar banco de dados

Executar o script _[criar-banco-qbroca.sql](./src/main/resources/criar-banco-qbroca.sql)_, localizado na pasta _resources_, em um servidor MySQL.

Resultado da execução do script:
* Criação do banco de dados qbroca
* Criação das tabelas 
  * restaurante
  * cliente
  * endereco
  * categoria
  * produto
  * pedido
  * item_pedido

# Carregar dados iniciais

Para popular o banco de dados com dados iniciais para testes, executar o script _[inserts.sql](./src/main/resources/inserts.sql)_, localizado na pasta _resources_, em um servidor MySQL.

Não obrigatórioa a execução desse script para que a aplicação funcione.