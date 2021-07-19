# Wishlist Store

É uma API REST que permite adicionar, atualizar, buscar e deletar clientes e manipular a lista de produtos favoritos por cliente.

Desenvolvida usando linguagem groovy, banco de dados postgresql e sobe num container autocontido utilizando springboot. Gradle é utilizado para gerenciamento de dependências.

### Como faço para subir a aplicação local?

Para facilitar o uso do banco de dados, utilize o docker-compose que pode ser instalado a partir da documentação oficial em https://docs.docker.com/compose/install

Para subir o banco de dados, acesse o diretorio root do projeto e execute o comando *docker-compose up* pelo terminal. 

Gradle deve ser usado para build do projeto : https://gradle.org/install

Para iniciar a aplicação, execute a classe Main.groovy. O banco de dados está configurado para criar as tabelas automaticamente para facilitar o teste.

### Próximos passos 

- Autenticação e autorização da API
- Paginação
- Auditoria
- Swagger para documentar API