# Wishlist Store

É uma API REST que permite adicionar, atualizar, buscar e deletar clientes e manipular a lista de produtos favoritos por cliente.

Desenvolvida usando linguagem groovy, banco de dados postgresql e sobe num container autocontido utilizando springboot. Gradle é utilizado para gerenciamento de dependências.

### Como faço para subir a aplicação local?

Para facilitar o uso do banco de dados, utilize o docker-compose que pode ser instalado a partir da documentação oficial em https://docs.docker.com/compose/install

Para subir o banco de dados, acesse o diretorio root do projeto e execute o comando *docker-compose up* pelo terminal. 

Gradle deve ser usado para build do projeto : https://gradle.org/install

Para iniciar a aplicação, execute a classe Main.groovy. O banco de dados está configurado para criar as tabelas automaticamente para facilitar o teste.

### Operações disponíveis

As operações disponíveis estão na classe CustomerController, servidor roda na porta 8080
localhost:8080/customers

* Criar novo cliente: POST http://localhost:8080/customers
input body recebe um email e um nome. Ex:
  
{
  "email":"vanecaetano@gmail.com",
  "name": "Vanessa Caetano"
}

* Editar cliente: PUT http://localhost:8080/customers
  input body recebe email e|ou nome que deseja alterar e o id do cliente. Ex:

{
"id":"1",
"name": "Vanessa C"
}

* Buscar cliente: GET http://localhost:8080/customers/1

* Remover cliente: DELETE http://localhost:8080/customers/1

* Listar clientes: GET http://localhost:8080/customers

* Adicionar produto nos favoritos: POST http://localhost:8080/customers/1/wishlist/1bf0f365-fbdd-4e21-9786-da459d78dd1f

* Remover produto dos favoritos: DELETE http://localhost:8080/customers/1/wishlist/1bf0f365-fbdd-4e21-9786-da459d78dd1f

* Buscar favoritos: GET http://localhost:8080/customers/1/wishlist

### Próximos passos 

- Autenticação e autorização da API
- Auditoria
- Paginação
- Swagger para documentar API
- Melhorar cobertura de testes
