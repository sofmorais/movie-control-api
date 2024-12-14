# API de controle de filmes

## Descrição
Essa aplicação é uma API RESTFul desenvolvida para controle de filmes seguindo a arquitetura de microsserviços e o padrão MVC para facilitar a manutenção.
A API permite realizar operações como: cadastrar e consultar filmes e espectadores, registrar filmes assistidos por um espectador, consultar a quantidade de filmes assistidos por um espectador e total de visualizações de um filme, deletar filmes e espectadores.
Foram utilizados os métodos HTTP GET, POST e DELETE para implementar as operações.

O banco de dados utilizado foi o [PostgreSQL](https://www.postgresql.org/).
A documentação dos endpoints foi feita utilizando o Swagger que permite visualizar e testar os endpoints através de uma interface web descrevendo os parâmetros necessários para cada endpoint e as possíveis respostas da API.

Além disso, foram implementados testes unitários para garantir a funcionalidade e a integridade da aplicação. Os testes de integração também serão implementados para .

Como melhoria futura, serão implementados os testes de integração e o Docker para containerização.

## Como testar

Para testar a API, você pode utilizar ferramentas como o Postman ou o Swagger UI, pois aplicação ainda não conta com uma interface visual para realizar as requisições.
Utilizei o [Postman](https://www.postman.com/downloads/), uma ferramenta que facilita a criação, teste e documentação de APis, pode ser feito o download ou usar a versão [online](https://web.postman.co/).

- Faça o download do [Java 17];
- Clone o repositório;
- Inicie o terminal na pasta do projeto;
- Utilizando o Maven, digite o comando `mvn spring-boot:run` para rodar a aplicação;
- 
- Acesse a documentação Swagger da API para visualizar todos os endpoints disponíveis; 
- Abra a [documentação](https://documenter.getpostman.com/view/21857150/2sAYHxp4yF) e selecione `Run in Postman`.
- Se preferir, você pode usar o botão `Run in Postman` diretamente da documentação do Swagger para importar os endpoints para o Postman.


## Tipos de requisição

A URL base é http://localhost:8080/api/v1 e o final é modificado de acordo com a requisição que será feita.

- ##### Endpoints filme: 
- `Cadastrar um filme`: /movies, método POST
- `Consultar filme por ID`: /movies/{movieId}, método GET
- `Consultar todos os filmes cadastrados`: /movies, método GET
- `Remover um filme` : /movies/{movieId}, método DELETE

- ##### Endpoints espectador
- `Cadastrar um espectador`: /viewer, método POST
- `Consultar espectador por ID`: /viewer/{viewerId}, método GET
- `Consultar espectadores cadastrados`: /viewer, método GET
- `Remover um espectador` : /viewer/{viewerId}, método DELETE

- ##### Endpoints registro de visualizações
- `Registrar filme assistido por um espectador`: /movie-view-record, método POST
- `Consultar quantos filmes um espectador assistiu`: /movie-view-record/viewer/{viewerId}/total-movies-watched, método GET
- `Consultar total de visualizações de um filme`: /movie-view-record/movie/{movieId}/total-views, método GET
- `Consultar quantos espectadores um filme tem`: /movie-view-record/{movieId}/viewers, método GET

## Documentação

![doc0](https://user-images.githubusercontent.com/87936806/178283116-6fcb1887-2432-437f-8aa9-9d41b4a2b774.jpg)

## Organização dos arquivos

| Pasta/arquivo                     | Descrição                                                                                                                                                         |
|-----------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| /controllers                      | Pasta com os controladores que lidam com as requisições HTTP, chamam os serviços necessários e retornam as respostas.                                             |
| /dtos                             | Pasta com os objetos de transferência de dados. Os DTOs são utilizados para transferir dados entre a camada de controle e a camada de serviço.                    |
| /models                           | Pasta com as classes que representam as entidades do banco de dados. Essas classes são mapeadas para as tabelas do banco.                                         |
| /repositories                     | Pasta com as classes responsáveis pela interação com o banco de dados, usando repositórios para encapsular a lógica de acesso a dados.                            |
| /resources/db/init                | Pasta com arquivo pra inicialização do banco de dados.                                                                                                            |
| /resources/db/migration           | Pasta com os scripts de migração do banco de dados, utilizados para versionar o banco e alterar o schema do banco (criar novas tabelas, adicionar colunas, etc.). |
| /resources/application.properties | Arquivo de propriedades de configuração da aplicação, contendo configurações sobre o banco de dados, portas, Swagger, etc.                                        |
| /test/unit                        | Pasta contendo os testes unitários da aplicação.                                                                                                                  |
 

## Tecnologias utilizadas

#### Desenvolvimento
Java 17
Spring Boot 3.4
Lombok

#### Banco de dados
PostgreSQL
Flyway 

#### Teste de endpoints e documentação
Springdoc/Swagger
Postman

#### Testes unitários
jUnit 5
Mockito

#### Gerenciamento de dependências e build
Maven: Ferramenta de build e gerenciamento de dependências utilizada no projeto
